package tu.utils

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, udf}
import tu.DataProcessor.requestHistory
import tu.nominatim
import java.io._

import org.json.{JSONException, JSONObject}

import scala.collection.mutable.ListBuffer
import scala.io.Source


object PrepareCity {

  def main(args: Array[String]): Unit = {
    val lines = Source.fromFile("data/prepare/outpu.txt").getLines.toArray
    var hamburg = new ListBuffer[String]
    lines.foreach(s => {
      if (s.contains("Hamburg")) {
        var el = s.split(" ")
        hamburg += el(3) + "|" + el(6)
      }
    })
    var p = "geo.json"
    val json = scala.io.Source.fromFile("data/prepare/output/" + p).mkString
    val coords = json.split(";")
    var counter = 0
    var tBUFFER = new ListBuffer[String]
    coords.map(geoItem => {
      try {
        val jObject = new JSONObject(geoItem)
        var lat = jObject.getDouble("lat")
        var lon = jObject.getDouble("lon")
        var key = lat + "|" + lon
        if (hamburg.contains(key)) {
          jObject.put("city", "Hamburg")
          counter += 1
          println("change value " + counter + " lat : " + lat + " lon: " + lon)
        }
        tBUFFER+=jObject.toString
        jObject.toString
      } catch {
        case _: JSONException => {
          geoItem
        }
      }
    })
    var result = tBUFFER.toArray.mkString("\n")
    val pw = new PrintWriter(new File("data/prepare/output/test/" + p))
    pw.write(result)
    pw.close


    /*

    var p = "geo.json"


    val lines = scala.io.Source.fromFile("data/prepare/" + p).mkString
    val sparkSession: SparkSession = SparkSession.builder
      .appName("luftdaten").master("local").getOrCreate()

    val rdd = sparkSession.sparkContext.parallelize(Seq(lines))
    val df = sparkSession.sqlContext.read.json(rdd)
    var total = df.count()
    var c = 0
    val cityUdf = udf((lat: Double, lon: Double) => {
      c += 1
      println(c + "/" + total)
      getCity(lat, lon)
    })

    //adding city column
    val dfWithCity = df.withColumn("city", cityUdf(col("lat"), col("lon")))

    var result = dfWithCity.toJSON.collect().mkString(",")
    val pw = new PrintWriter(new File("data/prepare/output/" + p))
    pw.write("[" + result + "]")
    pw.close*/
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
        if (address.getState == "Hamburg") {
          city = address.getState
        }
        requestHistory.add(lat, lon, city, address.getState)
      }
      city
    } catch {
      case _: NullPointerException =>
        null
    }
  }
}
