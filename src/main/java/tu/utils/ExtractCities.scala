package tu.utils

import java.io._

import org.apache.spark.sql.{Row, SQLContext, SparkSession}
import org.apache.spark.sql.functions.{col, udf}
import tu.DataProcessor.requestHistory
import tu.nominatim

import scala.collection.mutable.ListBuffer
import scala.io.Source


object ExtractCities {

  def main(args: Array[String]): Unit = {
    var p = "geo.json"

    val lines = scala.io.Source.fromFile("data/prepare/output/" + p).mkString
    val sparkSession: SparkSession = SparkSession.builder
      .appName("luftdaten").master("local").getOrCreate()

    val rdd = sparkSession.sparkContext.parallelize(Seq(lines))
    val df = sparkSession.sqlContext.read.json(rdd)

    val dfWithCity = df.filter(r => {
      val t: String = r.getAs("city")
      try {
        !t.isEmpty
      }
      catch {
        case _: NullPointerException => false
      }
    }).select("city").dropDuplicates()

    var cityBuffer = new ListBuffer[String]()

    var exist_cities = EcoUtil.data.keys.toArray
    dfWithCity.collect().foreach(r => {
      var el = r.getAs[String]("city")
      if (!exist_cities.contains(el)) {
        cityBuffer += el
      }
    })
    var result = cityBuffer.toArray.mkString(",\n")
    println(result)
    println(result.size)
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
