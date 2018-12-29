package tui

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.types._
import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

object ParquetTest {


  var baseSchema = StructType(Array(
    StructField("sensor_id", IntegerType),
    StructField("sensor_type", StringType),
    StructField("location", IntegerType),
    StructField("lat", DoubleType),
    StructField("lon", DoubleType),
    StructField("timestamp", StringType),
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

  def main(args: Array[String]) = {

    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    // Two threads local[2]
    val conf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("ParquetTest")
    val sc: SparkContext = new SparkContext(conf)
    val sqlContext: SQLContext = new SQLContext(sc)
    //    writeParquet(sc, sqlContext)
    readParquet(sqlContext)
  }

  def writeParquet(sc: SparkContext, sqlContext: SQLContext) = {
    // Read file as RDD
    val rdd = sqlContext.read.format("com.databricks.spark.csv")
      .option("delimiter", ";").option("header", "true").load("hdfs://0.0.0.0:19000/Sales.csv")
    // Convert rdd to data frame using toDF; the following import is required to use toDF function.
    val df: DataFrame = rdd.toDF()
    // Write file to parquet
    df.write.parquet("hdfs://0.0.0.0:19000/Sales.parquet")
  }

  def readParquet(sqlContext: SQLContext) = {
    // read back parquet to DF
    val HDFS = "hdfs://localhost:9000/luftdaten/2018-11/";
    val newDataDF = sqlContext.read
      .option("header", "true")
      .schema(this.schema2)
      .parquet(HDFS + "*.parquet",HDFS + "/test/*.parquet")
    // show contents
    print(newDataDF.count())
    //    newDataDF.show(10)
  }
}