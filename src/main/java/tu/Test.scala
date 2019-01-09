package tu

import org.apache.log4j.{Level, LogManager}
import org.apache.spark.sql.SparkSession
import tu.DataProcessor.{schema1, schema2, schema3}
import tu.utils.DataLoader

object Test {
  def main(args: Array[String]): Unit = {

    //    var hdfsBase = "hdfs://localhost:9000/"
    //    var sparkMaster = "local"
    //    val numCluster = 3
    //    val numIter = 20

    var hdfsBase = args(0)
    var sparkMaster = args(1)
    val numCluster = args(2).toInt
    val numIter = args(3).toInt

    // Two threads local[2]
    val sparkSession: SparkSession = SparkSession.builder
      .appName("luftdaten").master(sparkMaster).getOrCreate()
    val dataLoader: DataLoader = new DataLoader(hdfsBase)

    var df1 = dataLoader.readParquet(sparkSession.sqlContext, dataLoader.schema1Path, schema1).
      select("lat", "lon", "P1", "P2")
    var df2 = dataLoader.readParquet(sparkSession.sqlContext, dataLoader.schema2Path, schema2)
      .select("lat", "lon", "P1", "P2")
    var df3 = dataLoader.readParquet(sparkSession.sqlContext, dataLoader.schema3Path, schema3)
      .select("lat", "lon", "P1", "P2")
    var df = df1
      .union(df2)
      .union(df3)
    df = df.select("lat", "lon").dropDuplicates()
    val log = LogManager.getRootLogger
    log.setLevel(Level.WARN)
    log.warn("count : " + df.count())
    println("count : " + df.count())
    var result = df.toJSON.collect().mkString(",")
    result = "{\n  \"items\": [" + result + "]}"
    log.warn(result)
    dataLoader.write(result, "/test.json")
  }
}
