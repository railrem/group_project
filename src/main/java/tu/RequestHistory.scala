package tu

import java.util
import java.util.HashMap

import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.sql.types._

class RequestHistory(val sqlContext: SQLContext) {

  var schema = StructType(Array(
    StructField("lat", DoubleType),
    StructField("lon", DoubleType),
    StructField("city", StringType),
    StructField("region", StringType)
  ))

  var df: DataFrame = sqlContext.read
    .schema(schema)
    .parquet(DataLoader.HDFS_BASE + "history.parquet")

  var history: HashMap[String, List[String]] = new util.HashMap[String, List[String]]()

  def getCity(lat: Double, lon: Double): String = {
    var r = history.get(getHitoryKey(lat, lon))
    if (r != null) {
      return r.head
    }
    null
  }

  def add(lat: Double, lon: Double, city: String, region: String): Unit = {
    history.put(getHitoryKey(lat, lon), List(city, region))
  }

  def getHitoryKey(lat: Double, lon: Double): String = {
    lat.toString + "|" + lon.toString
  }

}
