package tu.loader

import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._

import scala.collection.mutable._
import scala.io.Source

class ArchiveLinksExtracter {

  val DATASET_HOST = "https://archive.luftdaten.info/parquet/"


  def getFromFile(): Unit ={
    val filename = "src\\main\\resources\\links"
    var sch1 = new ListBuffer[String]()
    var sch2 = new ListBuffer[String]()
    var sch3 = new ListBuffer[String]()

    for (line <- Source.fromFile(filename).getLines) {
      if (line.contains("ppd42ns") || line.contains("sds011")){
        sch1 += line
      }
      if (line.contains("hpm")){
        sch2 += line
      }
      if (line.contains("pms3003") || line.contains("pms5003")||line.contains("pms7003")){
        sch3 += line
      }
    }
    printArray(sch1.toArray)
    printArray(sch2.toArray)
    printArray(sch3.toArray)

  }
  private def printArray(array: Array[String]): Unit ={
    println("Array(")
    var s = ""
    array.foreach(element => s = s+"\""+element.dropRight(1)+"\",\n")
    s.dropRight(2)
    println(s)
    println(")")
  }


  def getLinks(): List[Array[String]] = {
    val browser = new JsoupBrowser()

    var result = new ListBuffer[Array[String]]()
    val doc = browser.get(DATASET_HOST)
    doc >> text("td")
    doc >> elementList("td")
    val list: List[String] = (doc >> elementList("td").map(_ >> allText("a"))).drop(2).filter(_ > " ")
    list.foreach(x => {
      val web = browser.get(DATASET_HOST + x.trim)
      web >> text("tr")
      web >> elementList("td")
      val weblist = web >> elementList("a").map(_ >> allText("a"))
      result += innerMethod("ppd42ns/", x, weblist) ++ innerMethod("sds011/", x, weblist)
      result += innerMethod("hpm/", x, weblist)
      result += innerMethod("pms3003/", x, weblist) ++ innerMethod("pms5003/", x, weblist) ++ innerMethod("pms7003/", x, weblist)
    })
    result.toList
  }

  private def innerMethod(partOfUrl: String, yearUrl: String, firstPartOfUrl: List[String]): Array[String] = {
    val browser = new JsoupBrowser()

    var result = new ListBuffer[String]()
    if (firstPartOfUrl.contains(partOfUrl)) {
      val link = browser.get(DATASET_HOST + yearUrl.trim + partOfUrl)
      link >> elementList("td")
      val weblink = link >> elementList("a").map(_ >> allText("a"))
      weblink.foreach(y => {
        if (y.contains("part")) {
          result += DATASET_HOST + yearUrl.trim + partOfUrl + y
        }
      })
    }
    result.toArray
  }
}
