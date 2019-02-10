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

    var dfWithGreenSpace = DataProcessor.addInfoByCity(dfWithAverageAndCities, "green_space", EcoUtil.getGreenAreaPercentage)

    var result = dfWithGreenSpace.toJSON.collect().mkString(",")
    result = "{\n  \"items\": [" + result + "]}"
    helpers.print(result)

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

  }
}