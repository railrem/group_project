package scala.parsing
import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._

import scala.collection.mutable._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
import net.ruippeixotog.scalascraper.model._
import org.xml.sax.InputSource

import parsing._
import scala.collection.mutable
import scala.xml.parsing.NoBindingFactoryAdapter

class ParserScalaImpl extends NoBindingFactoryAdapter
  with ParserScala {
  final val browser = JsoupBrowser()
  override def getDocFromWeb(url: String) : List[Array[String]] = {
    var arrayList : mutable.Buffer[String] = mutable.Buffer("", "", "")
    val doc = browser.get(url)
    doc >> text("td")
    doc >> elementList("td")
    val list : List[String] = (doc >> elementList("td").map(_ >> allText("a"))).drop(2).filter(_ > " ")
    list.foreach(x =>{
      val web = browser.get("https://archive.luftdaten.info/parquet/"+x.trim)
      web >> text("tr")
      web >> elementList("td")
      val weblist = web >> elementList("a").map(_ >> allText("a"))
      if(weblist.contains("ppd42ns/")) {
        val link = browser.get("https://archive.luftdaten.info/parquet/"+x.trim+"ppd42ns/")
        link >> elementList("td")
        val weblink = link >> elementList("a").map(_ >> allText("a"))
        weblink.foreach(y => {
          if(y.contains("part")){
            arrayList(0) = arrayList(0)+ "https://archive.luftdaten.info/parquet/"+x.trim+"ppd42ns/"+y+","
            println(arrayList(0))
          }
        })
      }

      if(weblist.contains("sds011/")) {
        val link = browser.get("https://archive.luftdaten.info/parquet/"+x.trim+"sds011/")
        link >> elementList("td")
        val weblink = link >> elementList("a").map(_ >> allText("a"))
        weblink.foreach(y => {
          if(y.contains("part")){
            arrayList(0) = arrayList(0)+ "https://archive.luftdaten.info/parquet/"+x.trim+"sds011/"+y+","
            println(arrayList(0))
          }
        })
      }
      if(weblist.contains("hpm/")) {
        val link = browser.get("https://archive.luftdaten.info/parquet/"+x.trim+"hpm/")
        link >> elementList("td")
        val weblink = link >> elementList("a").map(_ >> allText("a"))
        weblink.foreach(y => {
          if(y.contains("part")){
            arrayList(1) = arrayList(1)+ "https://archive.luftdaten.info/parquet/"+x.trim+"hpm/"+y+","
            println(arrayList(1))
          }
        })
      }
      if(weblist.contains("pms3003/")) {
        val link = browser.get("https://archive.luftdaten.info/parquet/" + x.trim + "pms3003/")
        link >> elementList("td")
        val weblink = link >> elementList("a").map(_ >> allText("a"))
        weblink.foreach(y => {
          if (y.contains("part")) {
            arrayList(2) = arrayList(2) + "https://archive.luftdaten.info/parquet/" + x.trim + "pms3003/" + y + ","
          }
        })
      }
        if(weblist.contains("pms5003/")) {
          val link = browser.get("https://archive.luftdaten.info/parquet/" + x.trim + "pms5003/")
          link >> elementList("td")
          val weblink = link >> elementList("a").map(_ >> allText("a"))
          weblink.foreach(y => {
            if (y.contains("part")) {
              arrayList(2) = arrayList(2) + "https://archive.luftdaten.info/parquet/" + x.trim + "pms5003/" + y + ","
            }
          })
        }
          if(weblist.contains("pms7003/")) {
            val link = browser.get("https://archive.luftdaten.info/parquet/"+x.trim+"pms7003/")
            link >> elementList("td")
            val weblink = link >> elementList("a").map(_ >> allText("a"))
            weblink.foreach(y => {
              if(y.contains("part")){
                arrayList(2) = arrayList(2)+ "https://archive.luftdaten.info/parquet/"+x.trim+"pms7003/"+y+","
              }
            })
      }
    })
    val finalList : List[Array[String]] = List(arrayList(0).split(","),arrayList(1).split(","),arrayList(2).split(","))
    //println(arrayList)
    //println(list)
    finalList
  }
}
