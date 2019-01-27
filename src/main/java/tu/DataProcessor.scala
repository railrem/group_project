package tu

import java.lang.NullPointerException

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.{avg, col, udf}
import org.apache.spark.sql.types._
import tu.utils.{DataLoader, RequestHistory}
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

  val requestHistory: RequestHistory = new RequestHistory()

  def getAverageByCity(hdfsBase: String, sparkSession: SparkSession): DataFrame = {

    // Union all data with different scheme
    val dataLoader: DataLoader = new DataLoader(hdfsBase)

    var df1 = dataLoader.readParquet(sparkSession.sqlContext, dataLoader.schema1Path, schema1).
      select("lat", "lon", "P1", "P2")
    var df2 = dataLoader.readParquet(sparkSession.sqlContext, dataLoader.schema2Path, schema2)
      .select("lat", "lon", "P1", "P2")
    var df3 = dataLoader.readParquet(sparkSession.sqlContext, dataLoader.schema3Path, schema3)
      .select("lat", "lon", "P1", "P2")
    val df = df1
      .union(df2)
      .union(df3)
    df.printSchema()
    //    df.show(100)

    var citiesDF = sparkSession.read.json(hdfsBase + "geo.json")
    /*
        val cityUdf = udf((lat: Double, lon: Double) => getCity(lat, lon))

        //adding city column
        val dfWithCity = df.withColumn("city", cityUdf(col("lat"), col("lon")))
       */

    val df_asCity = citiesDF.as("dfcities")
    val df_asData = df.as("dfdata")

    var dfWithCity = df_asData.join(df_asCity, Seq("lat", "lon"))

    val dfFileteredWithCity = dfWithCity.filter(r => {
      val t: String = r.getAs("city")
      try {
        !t.isEmpty
      }
      catch {
        case _: NullPointerException => false
      }
    })

    //  calculate average P1 P2 by city
    dfFileteredWithCity.createOrReplaceTempView("data")
    var dfWithAverageAndCities = sparkSession.sqlContext
      .sql("select city, avg(P1) as avg_P1,avg(P2) as avg_P2 from data group by city")

    /* val dfWithAverageAndCities = dfFileteredWithCity.groupBy(col("city"))
       .agg(
         mean(col("P1")),
         mean(col("P2"))
       )
       */


    val dfFileteredAverage = dfWithAverageAndCities.filter(r => {
      val avgP1: Double = r.getAs("avg_P1")
      val avgP2: Double = r.getAs("avg_P2")
      try {
        !(avgP1.isNaN || avgP2.isNaN)
      }
      catch {
        case _: NullPointerException => false
      }
    })

    val pUdf = udf((p: Double) => normalizeP(p))

    var dfFinish = dfFileteredAverage
      .filter(col("avg_P1") < 100)
      .filter(col("avg_P2") < 100)

    dfFinish
  }

  def normalizeP(p: Double): Double = {
    if (p > 100.0) {
      return 100.0
    }
    p
  }

  /**
    * return city by geo data if data was asked get from history
    * otherwise return from Nominatm API
    *
    * @param lat latitude
    * @param lon longtude
    * @return
    */
  def getCity(lat: Double, lon: Double): String = {
    try {
      var city = requestHistory.getCity(lat, lon)
      if (city == null) {
        Thread.sleep(1000)
        var nominatim1 = new nominatim.NominatimAPI(); //create instance with default zoom level (18)
        val address = nominatim1.getAdress(lat, lon); //returns Address object for the given position
        city = address.getCity
        println("REQUEST lat : " + lat + " lon : " + lon + " city : " + city + " state : " + address.getState)
        requestHistory.add(lat, lon, city, address.getState)
      }
      city
    } catch {
      case _: NullPointerException =>
        null
    }
  }

}
