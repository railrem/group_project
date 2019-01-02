package tu

import java.util.logging.{Level, Logger}

import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.mllib.clustering.{KMeans}
object Clustering {

  def execute(): Unit ={

    var dfWithAverageAndCities = DataProcessor.getAverageByCity

    val assembler = new VectorAssembler()
      .setInputCols(Array("avg_P1", "avg_P2"))
      .setOutputCol("features")

    val output = assembler.transform(dfWithAverageAndCities)
      .rdd.map(r => {
      val denseVector = r.getAs[org.apache.spark.ml.linalg.SparseVector]("features").toDense
      org.apache.spark.mllib.linalg.Vectors.fromML(denseVector)
    })

    val numClusters = 2
    val numIterations = 20
    val clusters = KMeans.train(output, numClusters, numIterations)

    // Evaluate clustering by computing Within Set Sum of Squared Errors
    val WSSSE = clusters.computeCost(output)
    println("Within Set Sum of Squared Errors = " + WSSSE)

  }

}
