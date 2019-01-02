package tu

import org.apache.log4j.Logger
import org.apache.log4j.Level

object App {
  //@ToDo добавить в аргументы для кластеризации
  def main(args: Array[String]): Unit = {

    CorrelationCoefficient.calculate()
    Clustering.execute()

  }
}
