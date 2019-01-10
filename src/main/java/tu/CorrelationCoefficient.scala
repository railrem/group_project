package tu

import java.io.PrintWriter

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.mllib.linalg.Matrix
import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.{col, udf}
import tu.utils.{DataLoader, EcoUtil}


object CorrelationCoefficient {

  def calculate(hdfsBase: String, sparkSession: SparkSession, df: DataFrame) = {
    var dfWithAverageAndCities = df
    val ecoUdf = udf((city: String) => EcoUtil.getGreenArea(city))
    val dfWithGreenSpace = dfWithAverageAndCities
      .withColumn("green_space", ecoUdf(col("city")))
      .filter(col("green_space").isNotNull)

    val assembler = new VectorAssembler()
      .setInputCols(Array("avg_P1", "avg_P2", "green_space"))
      .setOutputCol("features")

    val output = assembler.transform(dfWithGreenSpace)
      .rdd.map(r => {
      val denseVector = r.getAs[org.apache.spark.ml.linalg.SparseVector]("features").toDense
      org.apache.spark.mllib.linalg.Vectors.fromML(denseVector)
    })
    val dataLoader: DataLoader = new DataLoader(hdfsBase)

    // calculate the correlation matrix using Pearson's method. Use "spearman" for Spearman's method
    // If a method is not specified, Pearson's method will be used by default.
    val correlMatrix: Matrix = Statistics.corr(output, "pearson")
    helpers.print("Pearson correlation matrix:\n" + correlMatrix.toString)
    var t = correlMatrix.toArray
    var pearsonstr = "{\"avg_P1-avg_P2\": " + t(1) + " ,\"avg_P1-green_space\": " + t(2) + " ,\"avg_P2-green_space\": " + t(5) + "}"
    helpers.print(pearsonstr)
    dataLoader.write(pearsonstr, "/pearson.json")
    val correlMatrix2: Matrix = Statistics.corr(output, "spearman")
    t = correlMatrix2.toArray
    helpers.print("Spearman correlation matrix:\n" + correlMatrix2.toString)
    var spearmanstr = "{\"avg_P1-avg_P2\": " + t(1) + " ,\"avg_P1-green_space\": " + t(2) + " ,\"avg_P2-green_space\": " + t(5) + "}"
    helpers.print(spearmanstr)
    dataLoader.write(spearmanstr, "/spearman.json")


  }
}