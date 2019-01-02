package tu

import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{DataFrame, SQLContext}

import sys.process._

object DataLoader {

  var HDFS_BASE = "hdfs://localhost:9000/"

  val schema1Path: String = HDFS_BASE + "schema1/"
  var schema2Path: String = HDFS_BASE + "schema2/"
  var schema3Path: String = HDFS_BASE + "schema3/"

  var schema1Links = Array(
    "http://archive.luftdaten.info/parquet/2015-10/ppd42ns/part-00000-77c393f3-34ff-4e92-ad94-2c9839d70cd0-c000.snappy.parquet"
  )
  var schema2Links = Array(
    "http://archive.luftdaten.info/parquet/2017-11/hpm/part-00000-2b7a5538-7217-49a8-9350-608a40ec471b-c000.snappy.parquet"
  )
  var schema3Links = Array(
    "http://archive.luftdaten.info/parquet/2017-11/pms7003/part-00000-3c4b4471-d19a-4a9f-975e-8cac00fdc536-c000.snappy.parquet"
  )

  def load(): Unit = {
    loadAndSave(schema1Path, schema1Links)
    loadAndSave(schema2Path, schema2Links)
    loadAndSave(schema3Path, schema3Links)
  }

  private def loadAndSave(path: String, links: Array[String]): Unit = {
    links.foreach(link => {
      val command = "curl -Ss " + link + " | hdfs dfs -put - " + path + link.split("/").last
      //      command.!!
      println(command)
    })
  }

  def main(args: Array[String]): Unit = {
    load()
  }

  def readParquet(sqlContext: SQLContext, path: String, schema: StructType): DataFrame = {
    sqlContext.read
      .option("header", "true")
      .schema(schema)
      .parquet(path + "*.parquet")
  }
}
