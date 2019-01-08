package tu.utils

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.mllib.linalg.Matrix
import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{DataFrame, SQLContext}
import scala.collection.JavaConverters._ // I prefer this to JavaConversions
import org.json4s._
import org.json4s.native.Serialization._



class DataLoader(val hdfsDdir: String) {

  var hdfsBase: String = hdfsDdir

  val schema1Path: String = hdfsBase + "schema1/"
  var schema2Path: String = hdfsBase + "schema2/"
  var schema3Path: String = hdfsBase + "schema3/"

  def readParquet(sqlContext: SQLContext, path: String, schema: StructType): DataFrame = {
    sqlContext.read
      .option("header", "true")
      .schema(schema)
      .parquet(path + "*.parquet")
  }


  def write(content: String, path: String): Unit = {
    val conf = new Configuration()
    conf.set("fs.defaultFS", hdfsBase)
    val fs = FileSystem.get(conf)
    val os = fs.create(new Path(path))
    os.write(content.getBytes)
  }
}
