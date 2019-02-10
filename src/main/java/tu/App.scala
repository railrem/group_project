package tu

import org.apache.log4j.Logger
import org.apache.log4j.Level
import java.util

import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.sql.{Row, SparkSession}

import scala.collection.mutable.ListBuffer

object App {
  //@ToDo добавить в аргументы для кластеризации
  def main(args: Array[String]): Unit = {

    //    var hdfsBase = "hdfs://localhost:9000/"
    //    var sparkMaster = "local"
    //    val numCluster = 3
    //    val numIter = 20


    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)
    var hdfsBase = args(0)
    var sparkMaster = args(1)
    val numIter = args(2).toInt

    // Two threads local[2]
    val sparkSession: SparkSession = SparkSession.builder
      .appName("luftdaten").master(sparkMaster).getOrCreate()
    var df = DataProcessor.getAverageByCity(hdfsBase, sparkSession)

    CorrelationCoefficient.calculate(hdfsBase, sparkSession, df)
    Clustering.execute(numIter, df)

  }
}
