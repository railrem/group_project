package tu

import org.apache.log4j.{Level, LogManager}

package object helpers {

  val log = LogManager.getRootLogger


  def print(s: String): Unit = {
    println("==============================================")
    println(s)
    println("==============================================")

  }

}
