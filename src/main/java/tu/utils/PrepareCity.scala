package tu.utils

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, udf}
import tu.DataProcessor.requestHistory
import tu.nominatim
import java.io._


object PrepareCity {

  def main(args: Array[String]): Unit = {
    var p = "rail.json"


    val lines = scala.io.Source.fromFile("data/prepare/" + p).mkString
    val sparkSession: SparkSession = SparkSession.builder
      .appName("luftdaten").master("local").getOrCreate()

    val rdd = sparkSession.sparkContext.parallelize(Seq(lines))
    val df = sparkSession.sqlContext.read.json(rdd)

    val cityUdf = udf((lat: Double, lon: Double) => getCity(lat, lon))

    //adding city column
    val dfWithCity = df.withColumn("city", cityUdf(col("lat"), col("lon")))

    var result = dfWithCity.toJSON.collect().mkString(",")
    val pw = new PrintWriter(new File("data/prepare/output/" + p))
    pw.write(result)
    pw.close
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
