package scala.parsing

import java.util

import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.xml.parsing.NoBindingFactoryAdapter

class ParserScalaImpl extends NoBindingFactoryAdapter
  with ParserScala {
  final val browser = JsoupBrowser()

  override def getDocFromWeb(): List[Array[String]] = {
    val url = "https://archive.luftdaten.info/parquet/"
    var arrayList: mutable.Buffer[String] = mutable.Buffer("", "", "")
    var firstLinkArray = new ListBuffer[String]()
    var secondLinkArray = new ListBuffer[String]()
    var thirdLinkArray = new ListBuffer[String]()
    val doc = browser.get(url)
    doc >> text("td")
    doc >> elementList("td")
    val list: List[String] = (doc >> elementList("td").map(_ >> allText("a"))).drop(2).filter(_ > " ")
    list.foreach(x => {
      val web = browser.get(url + x.trim)
      web >> text("tr")
      web >> elementList("td")
      val weblist = web >> elementList("a").map(_ >> allText("a"))
      firstLinkArray += this.innerMethod(url, "ppd42ns/", x, weblist) += this.innerMethod(url, "sds011/", x, weblist)
      secondLinkArray += this.innerMethod(url, "hpm/", x, weblist)
      thirdLinkArray += this.innerMethod(url, "pms3003/", x, weblist) += this.innerMethod(url, "pms5003/", x, weblist) += this.innerMethod(url, "pms7003/", x, weblist)
    })
    val finalList: List[Array[String]] = List(firstLinkArray.toArray, secondLinkArray.toArray, thirdLinkArray.toArray)
    finalList
  }

  private def innerMethod(url: String, partOfUrl: String, yearUrl: String, firstPartOfUrl: List[String]): String = {
    var linkString = ""
    if (firstPartOfUrl.contains(partOfUrl)) {
      val link = browser.get(url + yearUrl.trim + partOfUrl)
      link >> elementList("td")
      val weblink = link >> elementList("a").map(_ >> allText("a"))
      weblink.foreach(y => {
        if (y.contains("part")) {
          linkString = linkString + url + yearUrl.trim + partOfUrl + y + ","
        }
      })
    }
    linkString
  }
}
