package tu.utils

import java.util
import java.util.HashMap

class RequestHistory() {

  var history: HashMap[String, List[String]] = new util.HashMap[String, List[String]]()

  def getCity(lat: Double, lon: Double): List[String] = {
    var r = history.get(getHitoryKey(lat, lon))
    if (r != null) {
      return r
    }
    null
  }

  def add(lat: Double, lon: Double, city: String, region: String,country:String): Unit = {
    history.put(getHitoryKey(lat, lon), List(city, region,country))
  }

  def getHitoryKey(lat: Double, lon: Double): String = {
    lat.toString + "|" + lon.toString
  }

}
