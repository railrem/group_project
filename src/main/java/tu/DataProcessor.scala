package tu

import java.lang.NullPointerException

import org.apache.spark.sql.{AccessShowString, DataFrame, SparkSession}
import org.apache.spark.sql.functions.{avg, col, udf}
import org.apache.spark.sql.types._
import tu.utils.{DataLoader, EcoUtil, RequestHistory}
import org.apache.spark.sql.functions._

object DataProcessor {

  var baseSchema = StructType(Array(
    StructField("sensor_id", IntegerType),
    StructField("sensor_type", StringType),
    StructField("location", IntegerType),
    StructField("lat", DoubleType),
    StructField("lon", DoubleType),
    StructField("timestamp", StringType)
  ))
  /*
    schema1 for sds011,ppd42ns
    schema2 for hpm
    schmea3 for pms3003,5003,7003
   */
  var schema1 = baseSchema
    .add(StructField("P1", DoubleType))
    .add(StructField("durP1", StringType))
    .add(StructField("ratioP1", StringType))
    .add(StructField("P2", DoubleType))
    .add(StructField("durP2", StringType))
    .add(StructField("ratioP2", StringType))
  var schema2 = baseSchema
    .add(StructField("P1", DoubleType))
    .add(StructField("P2", DoubleType))
  var schema3 = schema2
    .add(StructField("P0", DoubleType))

  var requestHistory: RequestHistory = new RequestHistory()

  def getAverageByCity(hdfsBase: String, sparkSession: SparkSession): DataFrame = {

    // Union all data with different scheme
    var dataLoader: DataLoader = new DataLoader(hdfsBase)

    var df1 = dataLoader.readParquet(sparkSession.sqlContext, dataLoader.schema1Path, schema1).
      select(, "sensor_id", "lat", "lon", "P1", "P2")
    var df2 = dataLoader.readParquet(sparkSession.sqlContext, dataLoader.schema2Path, schema2)
      .select("sensor_id", "lat", "lon", "P1", "P2")
    var df3 = dataLoader.readParquet(sparkSession.sqlContext, dataLoader.schema3Path, schema3)
      .select("sensor_id", "lat", "lon", "P1", "P2")
    var df = df1
      .union(df2)
      .union(df3)
    df.printSchema()

    var citiesDF = sparkSession.read.json(hdfsBase + "geo.json")

    /*
        var cityUdf = udf((lat: Double, lon: Double) => getCity(lat, lon))

        //adding city column
        var dfWithCity = df.withColumn("city", cityUdf(col("lat"), col("lon")))
    */

    var df_asCity = citiesDF.as("dfcities")
    var df_asData = df.as("dfdata")

    var dfWithCity = df_asData.join(df_asCity, Seq("lat", "lon"))

    var dfFiletered = filterEmptys(dfWithCity, "city")
      .filter(r => {
        var avgP1: Double = r.getAs("P1")
        var avgP2: Double = r.getAs("P2")
        try {
          !(avgP1.isNaN || avgP2.isNaN)
        }
        catch {
          case _: NullPointerException => false
        }
      })
    
    //    var umlautUdf = udf((p: String) => procUmlauts(p))
    //    var dfWithCityWithoutUmlauts = dfFileteredWithCity.withColumn("new_city", umlautUdf(col("city"))).drop("city")

    //  calculate average P1 P2 by city
    //    dfFileteredWithCity.createOrReplaceTempView("data")
    //    var dfWithAverageAndCities = sparkSession.sqlContext
    //      .sql("select city, avg(P1) as avg_P1,avg(P2) as avg_P2 from data group by city")
    //    
    var dfGroupedBySensorId = dfFiletered.groupBy(col("sensor_type"), col("sensor_id"), col("dfcities.city"))
      .agg(
        mean(col("P1")).as("avg_P1"),
        mean(col("P2")).as("avg_P2")
      )
    //    dfGroupedBySensorId.orderBy(col("sensor_type"), desc("avg_P1")).show(10000, 10000)

    //    dfGroupedBySensorId.show(1000)
    var dfWithAverageAndCities = dfGroupedBySensorId.groupBy(col("city"))
      .agg(
        mean(col("avg_P1")).as("avg_P1"),
        mean(col("avg_P2")).as("avg_P2")
      )
    //    dfWithAverageAndCities.orderBy(desc("avg_P1")).show(10000, 100000)
    
    var cityInfoDf = sparkSession.read.json(hdfsBase + "city_info.json")

    var df_asInfo = cityInfoDf.as("dfinfocities")
    var df_asMainData = dfWithAverageAndCities.as("dfmaindata")

    var dfFinish = df_asMainData.join(df_asInfo, col("city") === col("src_name"), "leftouter")
    dfFinish
  }
  

  def procUmlauts(s: String): String = {
    var deUm: Map[Char, String] =
      Map('ö' -> "o", 'ü' -> "u", 'ä' -> "a").withDefault(_.toString)

    s.flatMap(deUm(_))
  }


  def filterEmptys(dataFrame: DataFrame, column: String): DataFrame = {
    dataFrame.filter(r => {
      var t: String = r.getAs(column)
      try {
        !t.isEmpty
      }
      catch {
        case _: NullPointerException => false
      }
    })
  }


  def addInfoByCity(dataFrame: DataFrame, colName: String, callback: String => Integer): DataFrame = {
    var udfCity = udf((city: String) => callback(city))
    dataFrame
      .withColumn(colName, udfCity(col("city")))
      .filter(col(colName).isNotNull)
  }
}
