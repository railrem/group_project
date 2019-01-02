package tu

import java.util.logging.{Level, Logger}

import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.mllib.linalg.Matrix
import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.{avg, col, udf}
import org.apache.spark.sql.types._

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

  // Two threads local[2]
  val sparkSession: SparkSession = SparkSession.builder.master("local[2]")
    .appName("CorrelationCoefficient").getOrCreate()
  val requestHistory: RequestHistory = new RequestHistory(sparkSession.sqlContext)

  def getAverageByCity: DataFrame ={
    // Union all data with different scheme
    var df1 = DataLoader.readParquet(sparkSession.sqlContext, DataLoader.schema1Path, schema1).
      select("lat", "lon", "P1", "P2")
    var df2 = DataLoader.readParquet(sparkSession.sqlContext, DataLoader.schema2Path, schema2)
      .select("lat", "lon", "P1", "P2")
    var df3 = DataLoader.readParquet(sparkSession.sqlContext, DataLoader.schema3Path, schema3)
      .select("lat", "lon", "P1", "P2")
    val df = df1
      .union(df2)
      .union(df3)
    df.printSchema()

    val cityUdf = udf((lat: Double, lon: Double) => getCity(lat, lon))

    //adding city column
    val dfWithCity = df.withColumn("city", cityUdf(col("lat"), col("lon")))
      .filter(col("city").isNotNull)
    dfWithCity.show(10)

    // calculate average P1 P2 by city
    val dfWithAverageAndCities = dfWithCity.groupBy(col("city"))
      .agg(
        avg(col("P1")).as("avg_P1"),
        avg(col("P2")).as("avg_P2")
      )
    dfWithAverageAndCities.show(10, false)
    dfWithAverageAndCities
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
  }

}
