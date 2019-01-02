package tu

import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.mllib.linalg.Matrix
import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.sql.functions.{col, udf}


object CorrelationCoefficient {

  def calculate() = {
    var dfWithAverageAndCities = DataProcessor.getAverageByCity
    val ecoUdf = udf((city: String) => EcoUtil.getGreenArea(city))
    val dfWithGreenSpace = dfWithAverageAndCities
      .withColumn("green_space", ecoUdf(col("city")))
      .filter(col("green_space").isNotNull)
    dfWithGreenSpace.show(10, false)

    val assembler = new VectorAssembler()
      .setInputCols(Array("avg_P1", "avg_P2", "green_space"))
      .setOutputCol("features")

    val output = assembler.transform(dfWithGreenSpace)
      .rdd.map(r => {
      val denseVector = r.getAs[org.apache.spark.ml.linalg.SparseVector]("features").toDense
      org.apache.spark.mllib.linalg.Vectors.fromML(denseVector)
    })

    // calculate the correlation matrix using Pearson's method. Use "spearman" for Spearman's method
    // If a method is not specified, Pearson's method will be used by default.
    val correlMatrix: Matrix = Statistics.corr(output, "pearson")
    println("Pearson correlation matrix:\n" + correlMatrix.toString)

    val correlMatrix2: Matrix = Statistics.corr(output, "spearman")
    println("Spearman correlation matrix:\n" + correlMatrix2.toString)

  }
}