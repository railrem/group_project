package tu

import scala.collection.mutable.HashMap

object EcoUtil {
  val dataFromWeb: Array[String] = "Siegen, 115, 99, 86, 2, Göttingen, 117, 99, 85, 3, Bergisch Gladbach, 83, 70, 84, 4, Salzgitter, 224, 188, 84, 5, Pforzheim, 98, 82, 83, 6, Aachen, 161, 133, 82, 7, Hildesheim, 93, 76, 82, 8, Freiburg, 153, 125, 82, 9, Hagen, 160, 130, 81, 10, Saarbrücken, 167, 136, 81, 11, Bielefeld, 258, 209, 81, 12, Münster, 303, 245, 81, 13, Paderborn, 179, 143, 80, 14, Hamm, 226, 181, 80, 15, Jena, 114, 91, 80, 16, Remscheid, 75, 60, 80, 17, Reutlingen, 87, 69, 80, 18, Trier, 117, 92, 79, 19, Wolfsburg, 204, 161, 79, 20, Rostock, 181, 143, 79, 21, Chemnitz, 221, 174, 79, 22, Solingen, 90, 71, 79, 23, Lübeck, 214, 167, 78, 24, Wiesbaden, 204, 159, 78, 25, Wuppertal, 168, 130, 78, 26, Ulm, 119, 92, 77, 27, Kassel, 107, 81, 75, 28, Braunschweig, 192, 144, 75, 29, Mönchengladbach, 170, 125, 74, 30, Oldenburg, 103, 76, 74, 31, Bottrop, 101, 74, 73, 32, Koblenz, 105, 76, 73, 33, Mülheim/Ruhr, 91, 66, 73, 34, Bonn, 141, 102, 72, 35, Darmstadt, 122, 88, 72, 36, Heidelberg, 109, 79, 72, 37, Ingolstadt, 133, 95, 72, 38, Augsburg, 147, 105, 72, 39, Hamburg, 755, 539, 71, 40, Kiel, 119, 85, 71, 41, Heilbronn, 100, 71, 71, 42, Reckinghausen, 66, 47, 71, 43, Dortmund, 281, 199, 71, 44, Osnabrück, 120, 85, 71, 45, Stuttgart, 207, 145, 70, 46, Bochum, 146, 101, 69, 47, Dresden, 328, 228, 69, 48, Würzburg, 88, 61, 69, 49, Potsdam, 188, 130, 69, 50, Krefeld, 138, 95, 69, 51, Bremen, 325, 222, 68, 52, Essen, 210, 143, 68, 53, Moers, 68, 45, 66, 54, Hannover, 204, 133, 65, 55, Offenbach, 45, 29, 65, 56, Karlsruhe, 173, 111, 64, 57, Gelsenkirchen, 105, 67, 64, 58, Mainz, 98, 62, 64, 59, Leverkusen, 79, 50, 64, 60, Bremerhaven, 94, 58, 62, 61, Neuss, 100, 61, 61, 62, Erlangen, 77, 46, 60, 63, Berlin, 892, 526, 59, 64, Oberhausen, 77, 45, 59, 65, Cologne, 405, 237, 58, 66, Herne, 51, 30, 58, 67, Frankfurt/Main, 248, 144, 58, 68, Duisburg, 233, 132, 57, 69, Düsseldorf, 217, 123, 57, 70, Magdeburg, 201, 109, 54, 71, Erfurt, 269, 143, 53, 72, Regensburg, 81, 43, 53, 73, Fürth, 63, 33, 52, 74, Munich, 310, 155, 50, 75, Halle, 135, 66, 49, 76, Nürnberg, 186, 89, 48, 77, Mannheim, 145, 64, 44, 78, Leipzig, 297, 126, 42, 79, Ludwigshafen, 78, 27, 35".split(",")

  var data: HashMap[String, Array[Int]] = HashMap(
    ("Siegen", Array(115, 99, 86)),
    ("Göttingen", Array(117, 99, 85)),
    ("Bergisch Gladbach", Array(83, 70, 84)),
    ("Salzgitter", Array(224, 188, 84)),
    ("Pforzheim", Array(98, 82, 83)),
    ("Aachen", Array(161, 133, 82)),
    ("Hildesheim", Array(93, 76, 82)),
    ("Freiburg", Array(153, 125, 82)),
    ("Hagen", Array(160, 130, 81)),
    ("Saarbrücken", Array(167, 136, 81)),
    ("Bielefeld", Array(258, 209, 81)),
    ("Münster", Array(303, 245, 81)),
    ("Paderborn", Array(179, 143, 80)),
    ("Hamm", Array(226, 181, 80)),
    ("Jena", Array(114, 91, 80)),
    ("Remscheid", Array(75, 60, 80)),
    ("Reutlingen", Array(87, 69, 80)),
    ("Trier", Array(117, 92, 79)),
    ("Wolfsburg", Array(204, 161, 79)),
    ("Rostock", Array(181, 143, 79)),
    ("Chemnitz", Array(221, 174, 79)),
    ("Solingen", Array(90, 71, 79)),
    ("Lübeck", Array(214, 167, 78)),
    ("Wiesbaden", Array(204, 159, 78)),
    ("Wuppertal", Array(168, 130, 78)),
    ("Ulm", Array(119, 92, 77)),
    ("Kassel", Array(107, 81, 75)),
    ("Braunschweig", Array(192, 144, 75)),
    ("Mönchengladbach", Array(170, 125, 74)),
    ("Oldenburg", Array(103, 76, 74)),
    ("Bottrop", Array(101, 74, 73)),
    ("Koblenz", Array(105, 76, 73)),
    ("Mülheim/Ruhr", Array(91, 66, 73)),
    ("Bonn", Array(141, 102, 72)),
    ("Darmstadt", Array(122, 88, 72)),
    ("Heidelberg", Array(109, 79, 72)),
    ("Ingolstadt", Array(133, 95, 72)),
    ("Augsburg", Array(147, 105, 72)),
    ("Hamburg", Array(755, 539, 71)),
    ("Kiel", Array(119, 85, 71)),
    ("Heilbronn", Array(100, 71, 71)),
    ("Reckinghausen", Array(66, 47, 71)),
    ("Dortmund", Array(281, 199, 71)),
    ("Osnabrück", Array(120, 85, 71)),
    ("Stuttgart", Array(207, 145, 70)),
    ("Bochum", Array(146, 101, 69)),
    ("Dresden", Array(328, 228, 69)),
    ("Würzburg", Array(88, 61, 69)),
    ("Potsdam", Array(188, 130, 69)),
    ("Krefeld", Array(138, 95, 69)),
    ("Bremen", Array(325, 222, 68)),
    ("Essen", Array(210, 143, 68)),
    ("Moers", Array(68, 45, 66)),
    ("Hannover", Array(204, 133, 65)),
    ("Offenbach", Array(45, 29, 65)),
    ("Karlsruhe", Array(173, 111, 64)),
    ("Gelsenkirchen", Array(105, 67, 64)),
    ("Mainz", Array(98, 62, 64)),
    ("Leverkusen", Array(79, 50, 64)),
    ("Bremerhaven", Array(94, 58, 62)),
    ("Neuss", Array(100, 61, 61)),
    ("Erlangen", Array(77, 46, 60)),
    ("Berlin", Array(892, 526, 59)),
    ("Oberhausen", Array(77, 45, 59)),
    ("Cologne", Array(405, 237, 58)),
    ("Herne", Array(51, 30, 58)),
    ("Frankfurt/Main", Array(248, 144, 58)),
    ("Duisburg", Array(233, 132, 57)),
    ("Düsseldorf", Array(217, 123, 57)),
    ("Magdeburg", Array(201, 109, 54)),
    ("Erfurt", Array(269, 143, 53)),
    ("Regensburg", Array(81, 43, 53)),
    ("Fürth", Array(63, 33, 52)),
    ("Munich", Array(310, 155, 50)),
    ("Halle", Array(135, 66, 49)),
    ("Nürnberg", Array(186, 89, 48)),
    ("Mannheim", Array(145, 64, 44)),
    ("Leipzig", Array(297, 126, 42)),
    ("Ludwigshafen", Array(78, 27, 35))
  )

  def main(args: Array[String]): Unit = {
    var res: String = "HashMap("
    for (i <- 0 to dataFromWeb.length - 4 by 5) {
      res = res + "(\"" + dataFromWeb(i).trim() + "\",Array(" + dataFromWeb(i + 1) + "," + dataFromWeb(i + 2) + "," + dataFromWeb(i + 3) + ")),\n"
    }
    res.dropRight(1)
    res = res + ")"
    print(res)
  }

  def getTotalArea(city: String): Integer = {
    get(city, 0)
  }

  def getGreenArea(city: String): Integer = {
    get(city, 1)
  }

  def getGreenAreaPercentage(city: String): Integer = {
    get(city, 2)
  }

  private def get(city: String, index: Int): Integer = {
    if (data.get(city).isEmpty) {
      return null
    }
    val t = data.get(city).orNull
    t(index)
  }
}

