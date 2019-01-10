package tu

import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.{col, udf}
import tu.DataProcessor.getCity
import tu.utils.DataLoader

import scala.collection.mutable.ListBuffer

object Clustering {

  def execute(hdfsBase: String, sparkSession: SparkSession, numCluster: Integer, numIter: Integer, df: DataFrame): Unit = {

    var dfWithAverageAndCities = df

    val assembler = new VectorAssembler()
      .setInputCols(Array("avg_P1", "avg_P2"))
      .setOutputCol("features")

    val output = assembler.transform(dfWithAverageAndCities)
      .rdd.map(r => {
      val denseVector = r.getAs[org.apache.spark.ml.linalg.SparseVector]("features").toDense
      val res = Vectors.fromML(denseVector)
      res
    })

    val numClusters = numCluster
    val numIterations = numIter
    val clusters = KMeans.train(output, numClusters, numIterations)

    // Evaluate clustering by computing Within Set Sum of Squared Errors
    val WSSSE = clusters.computeCost(output)
    helpers.print("Within Set Sum of Squared Errors = " + WSSSE)

  // clusters.save(sparkSession.sparkContext, hdfsBase + "KMeansModel")

    helpers.print("Kmeans centroids")
    helpers.print(clusters.clusterCenters.mkString(","))
    val labelUdf = udf((p1: Double, p2: Double) => clusters.predict(Vectors.dense(p1, p2)))

    //adding city column
    val dfWithCluster = dfWithAverageAndCities.withColumn("cluster", labelUdf(col("avg_P1"), col("avg_P2")))

    var result = dfWithCluster.toJSON.collect().mkString(",")
    val dataLoader: DataLoader = new DataLoader(hdfsBase)
    result = "{\n  \"items\": [" + result + "]}"
    helpers.print(result)
    dataLoader.write(result, "/cluster.json")

    //   val sameModel = KMeansModel.load(sparkSession.sparkContext, hdfsBase + "KMeansModel")
  }

}
