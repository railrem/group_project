package tu.utils

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, udf}
import tu.{DataProcessor, nominatim}
import java.io._
import java.lang.NullPointerException

import tu.DataProcessor.requestHistory

import scala.collection.mutable.ListBuffer


object ExtractCities {


  def main(args: Array[String]): Unit = {
    var p = "geo.json"


    //    val lines = scala.io.Source.fromFile("data/prepare/" + p).mkString
    val sparkSession: SparkSession = SparkSession.builder
      .appName("luftdaten").master("local").getOrCreate()
    //
    //    val rdd = sparkSession.sparkContext.parallelize(Seq(lines))
    val df = sparkSession.sqlContext.read.json("data/prepare/output/" + p)
    var total = df.count()
    var c = 0
    var t = new ListBuffer[String]
    var dfNnew = DataProcessor.filterEmptys(df.select("city").dropDuplicates(),"city")
    println(dfNnew.count())
    var arr = dfNnew.collect().foreach(r =>{
      t += r.getAs[String]("city")
    })
    println(t.toArray.mkString(","))



   /* val cityUdf = udf((lat: Double, lon: Double) => {
      c += 1
      println(c + "/" + total)
      getCity(lat, lon)
    })

    def extract(index: Integer, elem: Object): String = {
      try {
        var value = elem.toString.split(";")(index)
        if (value != "&") {
          return value
        }
        "null"
      }catch {
        case _: NullPointerException =>
          "null"
      }
    }

    val cityExtract = udf((elem: String) => {
      extract(0, elem)
    })
    val countryExtract = udf((elem: String) => {
      extract(2, elem)
    })
    val stateExtract = udf((elem: String) => {
      extract(1, elem)
    })

    //adding city column
    val dfWithCity = df
//      .withColumn("info", cityUdf(col("lat"), col("lon")))
      .withColumn("city", cityExtract(col("info")))
      .withColumn("state", stateExtract(col("info")))
      .withColumn("country", countryExtract(col("info")))
      .drop("info")
    var result = dfWithCity.toJSON.collect().mkString(",")
    val pw = new PrintWriter(new File("data/prepare/output/" + p))
    pw.write(result)
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

        var city = "&"
        var country = "&"
        var state = "&"
        Thread.sleep(1000)
        var nominatim1 = new nominatim.NominatimAPI(); //create instance with default zoom level (18)
        val address = nominatim1.getAdress(lat, lon); //returns Address object for the given position
        if (address.getCity != null)
          city = address.getCity
        if (address.getCountry != null)
          country = address.getCountry
        if (address.getState != null)
          state = address.getState
        println("REQUEST lat : " + lat + " lon : " + lon + " city : " + city + " state : " + address.getState)
        //        requestHistory.add(lat, lon, city, address.getState)
        city + ";" + state + ";" + country
    } catch {
      case _: NullPointerException =>
        null
    }
  }
}
