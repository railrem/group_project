package tu

import java.util

import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.{col, udf}
import tu.DataProcessor.normalizeP
import tu.utils.{CiteDetails, DataLoader}

object Clustering {

  def execute(numIter: Integer, df: DataFrame): Unit = {

    var dfWithAverageAndCities = df
    val pUdf = udf((p: Double) => normalizeP(p))

    var dfWithPopulation = df.filter(col("population").isNotNull)
      .withColumn("normpop", pUdf(col("population")))

    trainKmeans(numIter, dfWithPopulation, "avg_P1", "normpop")

    trainKmeans(numIter, dfWithPopulation, "avg_P2", "normpop")

    //    var dfWithSea = DataProcessor.addInfoByCity(dfWithAverageAndCities, "elevation_meters", CiteDetails.getElevationMeters)
    //
    //    trainKmeans(numIter,dfWithPopulation,"avg_P1","elevation_meters")
    //
    //    trainKmeans(numIter,dfWithPopulation,"avg_P2","elevation_meters")


  }

  def normalizeP(p: Double): Double = {
    p / 10000
  }

  def trainKmeans(numIter: Integer, dataFrame: DataFrame, firstMeasure: String, secondMeasure: String): Unit = {
    helpers.print("first : " + firstMeasure + " second :" + secondMeasure)
    val assembler = new VectorAssembler()
      .setInputCols(Array(firstMeasure, secondMeasure))
      .setOutputCol("features")

    val output = assembler.transform(dataFrame)
      .rdd.map(r => {
      val denseVector = r.getAs[org.apache.spark.ml.linalg.SparseVector]("features").toDense
      val res = Vectors.fromML(denseVector)
      res
    })

    var optimalNumClusters = 20
    //    var minWSSE = Double.MaxValue
    //    var numClusters = 1
    //    var errors = new util.ArrayList[Array[Double]]
    /*  var clusterCountStr = "{\n  \"items\": ["
     // for loop execution with a range


     for (numClusters <- 1 to 50) {
       val clusters = KMeans.train(output, numClusters, numIter)
       // Evaluate clustering by computing Within Set Sum of Squared Errors
       val WSSSE = clusters.computeCost(output)
       errors.add(Array(numClusters, WSSSE))
       clusterCountStr += "[" + numClusters + "," + WSSSE + "],"
       if (WSSSE < minWSSE) {
         minWSSE = WSSSE
         optimalNumClusters = numClusters
       }
       //      helpers.print("Within Set Sum of Squared Errors = " + WSSSE)
     }

    helpers.print("cluster Count Measure")

    clusterCountStr = clusterCountStr.dropRight(1) + "]}"
    helpers.print(clusterCountStr)*/

    helpers.print("cluster count calculation")
    val clusters = KMeans.train(output, optimalNumClusters, numIter)
    helpers.print("Kmeans centroids")
    var centers = clusters.clusterCenters.mkString(",")

    val labelUdf = udf((p1: Double, p2: Double) => clusters.predict(Vectors.dense(p1, p2)))

    //adding city column
    val dfWithCluster = dataFrame.withColumn("cluster", labelUdf(col(firstMeasure), col(secondMeasure)))
    var result = dfWithCluster.toJSON.collect().mkString(",")
    result = "{\n  \"items\": [" + result + "],\"centers\":[" + centers + "]}"
    helpers.print(result)
    helpers.print("endline first : " + firstMeasure + " second :" + secondMeasure)
  }


}
