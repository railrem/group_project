package tu.utils

import scala.collection.mutable.HashMap

object CiteDetails {
  var data: HashMap[String, Array[Double]] =
    HashMap(
      ("Mainz", Array(19056, 184997, 89, 49.98419, 8.2791)),
      ("Oberkramer", null),
      ("Palermo", Array(130475, 1243585, 0, 37.81667, 13.58333)),
      ("Magdeburg", Array(19112, 229826, 0, 52.12773, 11.62916)),
      ("Bangalore", null),
      ("Donaustadt", Array(3656, 187007, 0, 48.2333, 16.46002)),
      ("Linz", Array(2617, 204846, 266, 48.30639, 14.28611)),
      ("Lodz", null),
      ("Gottingen", null),
      ("The Nation", null),
      ("Frankenberg", null),
      ("Kaiserslautern", Array(19950, 98732, 0, 49.443, 7.77161)),
      ("Ludwigshafen am Rhein", Array(18310, 163196, 0, 49.48121, 8.44641)),
      ("Falkenburg an der Gohl", null),
      ("Puerto Plata", Array(23804, 146000, 0, 19.79344, -70.6884)),
      ("Abuja", Array(74890, 590400, 840, 9.05785, 7.49508)),
      ("Silistra", Array(6422, 39715, 6, 44.11667, 27.26667)),
      ("Peel en Maas", null),
      ("Schulp bei Rendsburg", null),
      ("Velsen", null),
      ("Salzburg", Array(1991, 153377, 424, 47.79941, 13.04399)),
      ("Szegedin", null),
      ("Gemeinde Kirchberg an der Pielach", null),
      ("Malmo", null),
      ("Hatten", null),
      ("Brighton", Array(4684, 21257, 0, -37.90561, 145.0027942777, 139001, 22, 50.82838, -0.13947110794, 36609, 136, 43.14756, -77.55055115427, 37585, 1519, 39.98526, -104.82053)),
      ("Stara Sagora", null),
      ("Mulheim an der Ruhr", null),
      ("Westland", Array(110062, 82000, 203, 42.3242, -83.40021)),
      ("Neuhausen/Spree", null),
      ("Swisttal", null),
      ("Palo Alto", Array(115651, 66853, 9, 37.44188, -122.14302)),
      ("Nurnberg", null),
      ("Munster", Array(109507, 22984, 186, 41.56448, -87.51254)),
      ("Klagenfurt", null),
      ("Miguel Hidalgo", Array(69254, 372889, 2269, 19.43411, -99.20024)),
      ("Madrid", Array(126375, 62425, 2554, 4.75564, -74.2770828824, 3255944, 0, 40.4165, -3.70256)),
      ("Ingersheim", null),
      ("Rajon Serdika", null),
      ("Surrey Heath", null),
      ("Goteborg", null),
      ("Reutlingen", Array(17992, 112627, 382, 48.49144, 9.20427)),
      ("Lehre", null),
      ("Leinefelde-Worbis", null),
      ("Ulm", Array(17159, 120451, 478, 48.39841, 9.99155)),
      ("Spelthorne", null),
      ("Rzesz?w", null),
      ("Lohne", Array(18694, 25111, 0, 52.66625, 8.2375)),
      ("PGH", null),
      ("Potsdam", Array(17881, 145292, 35, 52.39886, 13.06566)),
      ("Amersfoort", Array(76615, 139914, 0, 52.155, 5.3875)),
      ("Legden", null),
      ("Illingen", null),
      ("????", null),
      ("Lubbenau/Spreewald", null),
      ("St. Georgen im Schwarzwald", null),
      ("Bernau", null),
      ("Dallas", Array(106894, 1300092, 128, 32.78306, -96.80667)),
      ("Krimpenerwaard", Array(133825, 55644, 0, 51.98171, 4.77828)),
      ("Dimitrowgrad", null),
      ("Burg (Spreewald)", null),
      ("Berwang", null),
      ("Stadt Brussel",Array(76046, 179277, 0, 52.00667, 4.35556)),
      ("Manchester", Array(41142, 395515, 38, 53.48095, -2.23743108119, 30577, 83, 41.77593, -72.52148110719, 110229, 80, 42.99564, -71.45479)),
      ("?????????", null),
      ("Pasardschik", null),
      ("Nordharz", null),
      ("Smallingerland", null),
      ("Dobritsch", null),
      ("Furth", null),
      ("Delft", Array(76046, 95060, 0, 52.00667, 4.35556)),
      ("Bassetlaw", null),
      ("Heringsdorf", null),
      ("Kradrob", null),
      ("Gemeinde Hinterbruhl", null),
      ("Ancona", Array(59829, 89994, 16, 43.5942, 13.50337)),
      ("Toulon", Array(32032, 168701, 1, 43.12442, 5.92836)),
      ("Wei?enburg i. Bay.", null),
      ("Sliwen", null),
      ("Schouwen-Duiveland", Array(133786, 33687, 0, 51.69294, 3.88676)),
      ("Heilbronn", Array(20919, 120733, 157, 49.13995, 9.22054)),
      ("Grenoble", Array(37155, 158552, 218, 45.17155, 5.72239)),
      ("Bern", Array(11470, 121631, 0, 46.94809, 7.44744)),
      ("Kardschali", null),
      ("Tschenstochau", null),
      ("Rajon Krasno selo", null),
      ("Siegen", Array(17094, 107242, 267, 50.87481, 8.02431)),
      ("Coventry", Array(42423, 359262, 0, 52.40656, -1.51217112699, 35525, 129, 41.7001, -71.68284)),
      ("Offenbach am Main", null),
      ("Pudahuel", null),
      ("Stockholm", Array(96263, 1515017, 0, 59.33258, 18.0649)),
      ("??????", null),
      ("Eastbourne", Array(42537, 118219, 22, 50.76871, 0.28453)),
      ("Pforzheim", Array(17790, 119313, 273, 48.88436, 8.69892)),
      ("Ho-Chi-Minh-Stadt", null),
      ("Drebkau", null),
      ("Santa Maria Capua Vetere", Array(54286, 31743, 36, 41.08156, 14.25342)),
      ("Ciudad de Mendoza", null),
      ("Turku", Array(31031, 175945, 0, 60.45148, 22.26869)),
      ("Rajon Ljulin", null),
      ("????????", null),
      ("George Town", Array(73907, 300000, 4, 5.41123, 100.33543)),
      ("Hagen", Array(20172, 198972, 106, 51.36081, 7.47168134340, 86951, 0, -5.83333, 144.28333)),
      ("Elbing", null),
      ("Valsamoggia", null),
      ("???", null),
      ("Fullerton", Array(115585, 140847, 50, 33.87029, -117.92534)),
      ("Myslowitz", null),
      ("???", null),
      ("Weil am Rhein", Array(16496, 29548, 281, 47.59331, 7.62082)),
      ("??????? ?????? ?????", null),
      ("Horst aan de Maas", null),
      ("Glasgow", Array(41702, 591620, 0, 55.86515, -4.25763)),
      ("Modena", Array(56134, 158886, 34, 44.64783, 10.92539)),
      ("Wuppertal", Array(15224, 360797, 157, 51.25627, 7.14816)),
      ("Erfurt", Array(20802, 203254, 194, 50.9787, 11.03283)),
      ("Bologna", Array(57934, 366133, 54, 44.49381, 11.33875)),
      ("Chapinero", null),
      ("Bickenbach", null),
      ("Gemeinde Tulln an der Donau", null),
      ("Konigsfeld im Schwarzwald", null),
      ("Lule?", null),
      ("Schonwolkau", null),
      ("Gemeinde Ruprechtshofen", null),
      ("Kunzell", null),
      ("Oslo", Array(76361, 580000, 23, 59.91273, 10.74609)),
      ("Taby kommun", null),
      ("Skonsmons distrikt", null),
      ("Chemnitz", Array(21861, 247220, 0, 50.8357, 12.92922)),
      ("Val Terbi", null),
      ("Bochum", Array(21573, 385729, 94, 51.48165, 7.21648)),
      ("Hohenstein", null),
      ("Recklinghausen", Array(17359, 122438, 85, 51.61379, 7.19738)),
      ("Bad Teinach-Zavelstein", null),
      ("Aspach", null),
      ("Stettin", null),
      ("Zabrze", Array(84336, 192177, 0, 50.32492, 18.78576)),
      ("Darmstadt", Array(21669, 140385, 0, 49.87167, 8.65027)),
      ("Widin", null),
      ("Waldenburg", null),
      ("Lelystad", Array(75837, 70741, 0, 52.50833, 5.475)),
      ("Remscheid", Array(17294, 117118, 365, 51.17983, 7.1925)),
      ("Mossul", null),
      ("Gemeinde Strasshof an der Nordbahn", null),
      ("San Diego", Array(114923, 1394928, 20, 32.71571, -117.16472)),
      ("??? ??????", null),
      ("Verden (Aller)", null),
      ("Brugge", Array(6475, 116709, 2, 51.20892, 3.22424)),
      ("Warschau", null),
      ("Eskilstuna", Array(96950, 67359, 26, 59.36661, 16.5077)),
      ("Ankara", Array(98585, 3517182, 850, 39.91987, 32.85427)),
      ("Lwiw", null),
      ("Kothen (Anhalt)", null),
      ("???????", null),
      ("Spremberg", null),
      ("Stra?burg", null),
      ("Kasanlak", null),
      ("Gemeinde Modling", null),
      ("Portland", Array(110017, 66881, 19, 43.66147, -70.25533115879, 632309, 12, 45.52345, -122.67621)),
      ("Municipio Santa Cruz de la Sierra", null),
      ("Homberg", null),
      ("Rotterdam", Array(75412, 598199, 0, 51.9225, 4.47917111940, 20652, 103, 42.78702, -73.97096)),
      ("Neuss", Array(18857, 152457, 40, 51.19807, 6.68504)),
      ("Forshaga kommun", null),
      ("London", Array(9410, 346765, 251, 42.98339, -81.2330441447, 7556900, 35, 51.50853, -0.12574)),
      ("Bjartr? distrikt", null),
      ("Santiago de Quer?taro", null),
      ("Bromberg", null),
      ("Mumbai", Array(50930, 12691836, 14, 19.07283, 72.88261)),
      ("Venedig", null),
      ("Tingsryds kommun", null),
      ("Kernen im Remstal", null),
      ("Neusohl", null),
      ("Grafschaft", null),
      ("Den Haag", null),
      ("Merkezefendi", Array(99643, 280341, 354, 37.80544, 29.04236)),
      ("Valjevo", Array(91496, 61035, 0, 44.27513, 19.89821)),
      ("Kiew", null),
      ("Vaster?s", null),
      ("Sydney", Array(4028, 4627345, 58, -33.86785, 151.207329088, 105968, 0, 46.1351, -60.1831)),
      ("Bischkek", null),
      ("Hunstetten", null),
      ("La Reina", null),
      ("Dresden", Array(21220, 486854, 112, 51.05089, 13.73832)),
      ("Rajon Sredez", null),
      ("Langenfeld (Rheinland)", null),
      ("Pristina", null),
      ("Graz", Array(2322, 222326, 0, 47.06667, 15.45)),
      ("Melipilla", Array(11527, 63100, 175, -33.68909, -71.21528)),
      ("Perth", Array(3364, 1896548, 2, -31.95224, 115.861440738, 47180, 0, 56.39522, -3.43139)),
      ("Lingewaard", null),
      ("Geestland", null),
      ("South and West Kerry", null),
      ("Wurzburg", null),
      ("Jambol", null),
      ("Eslohe", null),
      ("Minato", Array(62728, 375339, 0, 34.2152, 135.1501)),
      ("Florenz", null),
      ("Lomma kommun", null),
      ("Werther", null),
      ("Prag", null),
      ("Leverkusen", Array(19077, 162738, 61, 51.0303, 6.98432)),
      ("Rotenburg", Array(17174, 22139, 0, 53.11125, 9.41082)),
      ("Nimwegen", null),
      ("Vaasa", Array(31251, 57014, 6, 63.096, 21.61577)),
      ("Mulhausen", null),
      ("Bronckhorst", null),
      ("Lowen", null),
      ("Tampere", Array(31405, 202687, 0, 61.49911, 23.78712)),
      ("Reinfeld", null),
      ("Gemeinde Pottendorf", null),
      ("De Wolden", null),
      ("Commune Thiotte", null),
      ("Bielefeld", Array(22075, 331906, 118, 52.03333, 8.53333)),
      ("?????????", null),
      ("Cottbus", Array(21916, 84754, 0, 51.75769, 14.32888)),
      ("Swischtow", null),
      ("Erlangen", Array(20500, 102675, 279, 49.59099, 11.00783)),
      ("Wachtberg", Array(16972, 20032, 222, 50.63333, 7.1)),
      ("Ruckersdorf", null),
      ("Cuernavaca", Array(133247, 349102, 1698, 18.95532, -99.24002)),
      ("Freiburg", Array(20317, 215966, 278, 47.9959, 7.85222)),
      ("Gemeinde Poggstall", null),
      ("Funfkirchen", null),
      ("Hasselroth", null),
      ("Mendip", Array(44034, 110000, 0, 51.2372, -2.6266)),
      ("Kotz", null),
      ("Reinhardshagen", null),
      ("Paris", Array(127610, 2257981, 28, 48.8534, 2.3486107186, 24782, 183, 33.66094, -95.55551)),
      ("Athen", null),
      ("Trevignano", null),
      ("Huechuraba", null),
      ("Duncanville", Array(107202, 39826, 221, 32.6518, -96.90834)),
      ("Beemster", null),
      ("Rodenbach", null),
      ("Monza", Array(56759, 119618, 162, 45.58005, 9.27246)),
      ("Cork", Array(48173, 190384, 0, 51.89797, -8.47061)),
      ("Valle Gran Rey", null),
      ("Chaskowo", null),
      ("Midden-Drenthe", null),
      ("Ostfildern", Array(23114, 33598, 0, 48.72704, 9.24954)),
      ("Linsengericht", null),
      ("Oldenburg", Array(17362, 159218, 4, 53.14118, 8.21467)),
      ("Gooise Meren", Array(133823, 57337, 0, 52.29735, 5.13371)),
      ("Kirkland", Array(9098, 20491, 0, 45.45008, -73.86586117609, 87281, 47, 47.68149, -122.20874)),
      ("Breda", Array(76894, 167673, 3, 51.58656, 4.77596)),
      ("Hamburg", Array(20934, 1739117, 6, 53.57532, 10.01534)),
      ("Bodegraven-Reeuwijk", Array(133820, 33948, 0, 52.06541, 4.76634)),
      ("????", null),
      ("Schorfheide", null),
      ("Hannover", Array(20844, 515140, 55, 52.37052, 9.73322)),
      ("Freiburg im Breisgau", null),
      ("Burgas", Array(6991, 195966, 30, 42.50606, 27.46781)),
      ("Rostock", Array(17913, 198293, 0, 54.0887, 12.14049)),
      ("Het Hogeland", null),
      ("Vitacura", null),
      ("Utrechtse Heuvelrug", null),
      ("Sohrewald", null),
      ("Hurghada", Array(23917, 95622, 11, 27.25738, 33.81291)),
      ("Nisch", null),
      ("Gemeinde Wollersdorf-Steinabruckl", null),
      ("Lyon", Array(35070, 472317, 173, 45.74846, 4.84671)),
      ("Herzogenbusch", null),
      ("Dusseldorf",Array(76046, 617280, 0, 52.00667, 4.35556)),
      ("Ljubljana", Array(97115, 272220, 295, 46.05108, 14.50513)),
      ("Kopenhagen", null),
      ("Leidschendam-Voorburg", null),
      ("Rajon Triadiza", null),
      ("Bremen", Array(21648, 546501, 11, 53.07516, 8.80777)),
      ("Zurich", null),
      ("Gdingen", null),
      ("Krakau", null),
      ("Bozen", null),
      ("????? ?????", null),
      ("Riga", Array(64681, 742572, 6, 56.946, 24.10589)),
      ("?u?oa", null),
      ("Tytsjerksteradiel", null),
      ("Duisburg", Array(22086, 504358, 33, 51.43247, 6.76516)),
      ("Plateau-des-Petites-Roches", null),
      ("Sib", null),
      ("Kumla kommun", null),
      ("Wiesbaden", Array(15879, 272432, 117, 50.08258, 8.24932)),
      ("Hollands Kroon", null),
      ("Toronto", Array(9130, 2600000, 76, 43.70011, -79.4163)),
      ("Fier", Array(230, 56297, 20, 40.72389, 19.55611118826, 199442, 0, 40.71667, 19.58333)),
      ("Kassel", Array(20034, 194501, 175, 51.31667, 9.5)),
      ("H? T?nh", null),
      ("Paderborn", Array(17374, 142161, 94, 51.71905, 8.75439)),
      ("Raaba-Grambach", null),
      ("Arnheim", null),
      ("Gemeinde Semlin", null),
      ("Wyhl", null),
      ("Barcelona", Array(29913, 1621537, 15, 41.38879, 2.15899117855, 424795, 8, 10.13625, -64.68618)),
      ("Brandenburg an der Havel", Array(21269, 59826, 32, 52.41667, 12.55)),
      ("Munchen", null),
      ("Filderstadt", Array(23124, 43550, 371, 48.65698, 9.22049)),
      ("Gent", Array(6608, 231493, 4, 51.05, 3.71667)),
      ("Solingen", Array(17027, 164359, 53, 51.17343, 7.0845)),
      ("Feistritztal", null),
      ("Solna kommun", null),
      ("Ternopil", Array(99811, 235676, 320, 49.55342, 25.58918)),
      ("Dupniza", null),
      ("Kapstadt", null),
      ("Kathmandu", Array(77191, 1442271, 1317, 27.70169, 85.3206)),
      ("????", null),
      ("Biebesheim", null),
      ("Halle (Saale)", Array(20287, 234107, 87, 51.48158, 11.97947)),
      ("Rasgrad", null),
      ("Bezirk Bratislava II", null),
      ("Geldrop-Mierlo", null),
      ("Panketal", null),
      ("Russe", null),
      ("Gemeinde Petzenkirchen", null),
      ("Titisee-Neustadt", null),
      ("Haarlemmermeer", null),
      ("Annecy", Array(39972, 49232, 448, 45.90621, 6.1267)),
      ("Krefeld", Array(19606, 237984, 38, 51.33645, 6.55381)),
      ("Helsingborg", Array(135304, 129633, 0, 56.05648, 12.78718)),
      ("Saint-?tienne", null),
      ("Gleiwitz", null),
      ("Zoetermeer", Array(75734, 115845, 0, 52.0575, 4.49306)),
      ("Rom", null),
      ("Gemeinde Biedermannsdorf", null),
      ("Stockholms kommun", null),
      ("Leiden", Array(75070, 119713, 0, 52.15833, 4.49306)),
      ("Municipio Nuestra Se?ora de La Paz", null),
      ("S?o Mamede de Infesta e Senhora da Hora", null),
      ("Rajon Ilinden", null),
      ("George Local Municipality", null),
      ("Amsterdam", Array(76690, 741636, -2, 52.37403, 4.88969)),
      ("Moers", Array(18351, 107816, 30, 51.45342, 6.6326)),
      ("Dordrecht", Array(76733, 119260, 0, 51.81, 4.67361)),
      ("Appeldorn", null),
      ("Burnaby", Array(9573, 202799, 370, 49.26636, -122.95263)),
      ("Nieste", null),
      ("Buch", null),
      ("Nuthetal", null),
      ("Wizebsk", null),
      ("Venlo", Array(75900, 92403, 0, 51.37, 6.16806)),
      ("Bottrop", Array(22065, 119909, 55, 51.52392, 6.9285)),
      ("Aberdeen", Array(43410, 196670, 0, 57.14369, -2.09814114010, 28102, 397, 45.4647, -98.48648)),
      ("Fuldatal", null),
      ("Furtwangen im Schwarzwald", null),
      ("Osnabruck", null),
      ("Sonnenstein", null),
      ("Rheinau", null),
      ("Schontal", null),
      ("Wolfsburg", Array(15934, 123064, 63, 52.42452, 10.7815)),
      ("Pfaffikon", null),
      ("Braunschweig", Array(21508, 244715, 75, 52.26594, 10.52673)),
      ("Endingen", null),
      ("Rajon Witoscha", null),
      ("Ilsede", null),
      ("Rajon Nadeschda", null),
      ("Avegno Gordevio", null),
      ("Mannheim", Array(18647, 307960, 97, 49.4891, 8.46694)),
      ("Amtsberg", null),
      ("Assen", Array(76261, 62237, 0, 52.99667, 6.5625)),
      ("Assenowgrad", null),
      ("Langen", Array(19810, 35416, 143, 49.98955, 8.66852)),
      ("Gemeinde Neumarkt an der Ybbs", null),
      ("Danzig", null),
      ("Lievegem", Array(171810, 26007, 0, 51.12809, 3.59116)),
      ("Bobritzsch-Hilbersdorf", null),
      ("Almere", null),
      ("Sonnenbuhl", null),
      ("Elmenhorst/Lichtenhagen", null),
      ("Triest", null),
      ("Copiap?", null),
      ("Agra", Array(51722, 1430055, 171, 27.18333, 78.01667)),
      ("Aachen", Array(22349, 265208, 266, 50.77664, 6.08342)),
      ("Erftstadt", Array(21091, 51207, 98, 50.81481, 6.79387)),
      ("Westerkwartier", null),
      ("Bristol", Array(42648, 617280, 11, 51.45523, -2.59665106099, 26666, 511, 36.59511, -82.18874112776, 22795, 35, 41.67705, -71.26616114244, 60452, 93, 41.67176, -72.94927)),
      ("Ingolstadt", Array(19711, 120658, 376, 48.76508, 11.42372)),
      ("Montana", Array(6718, 47445, 135, 43.4125, 23.225)),
      ("Tirana", Array(312, 374801, 110, 41.3275, 19.81889)),
      ("Monchengladbach", null),
      ("Gemeinde Neu-Belgrad", null),
      ("Tainan", Array(99283, 771235, 0, 22.99083, 120.21333)),
      ("???????????", null),
      ("Korntal-Munchingen", null),
      ("Maastricht", Array(75218, 122378, 0, 50.84833, 5.68889)),
      ("Mook en Middelaar", null),
      ("Drechterland", null),
      ("Wernau (Neckar)", null),
      ("S?o Paulo", null),
      ("Karlsruhe", Array(19334, 283799, 118, 49.00937, 8.40444)),
      ("Frankfurt (Oder)", Array(20196, 51691, 40, 52.34714, 14.55062)),
      ("Luxemburg", null),
      ("Liederbach", null),
      ("Essunga kommun", null),
      ("Ume?", null),
      ("Leer", Array(20112, 33886, 7, 53.23157, 7.461)),
      ("Grobming", null),
      (" Stoderstra?e 801", null),
      ("Gemeinde Wiener Neustadt", null),
      ("Konigstein", null),
      ("Koblenz", Array(19931, 107319, 73, 50.35357, 7.57883)),
      ("Wust-Fischbeck", null),
      ("Rajon Kremikowzi", null),
      ("Chavannes-pr?s-Renens", null),
      ("Stuttgart", Array(16744, 589793, 247, 48.78232, 9.17702)),
      ("Trier", Array(17028, 100129, 135, 49.75565, 6.63935)),
      ("Toulouse", Array(32010, 433055, 141, 43.60426, 1.44367)),
      ("San Isidro", Array(1198, 45190, 15, -34.4721, -58.5270814118, 34877, 0, 9.3674, -83.69713127080, 20633, 0, 10.03333, -84.0333371869, 33737, 0, 19.33972, -98.9505677907, 68309, 109, -12.11667, -77.05)),
      ("Gemeinde Guntramsdorf", null),
      ("Utrecht", Array(75603, 290529, 0, 52.09083, 5.12222)),
      ("Hofheim am Taunus", Array(20082, 37750, 145, 50.09019, 8.4493)),
      ("Reading", Array(40574, 318014, 61, 51.45625, -0.97113109126, 24747, 29, 42.52565, -71.09533113040, 87879, 93, 40.33565, -75.92687)),
      ("Che?m", null),
      ("Cuautla", Array(66771, 154358, 1318, 18.8106, -98.93525)),
      ("Dobin am See", null),
      ("Breuna", null),
      ("Weststellingwerf", null),
      ("Padua", null),
      ("Posen", null),
      ("Heidelberg", Array(20840, 143345, 114, 49.40768, 8.69079118469, 64199, 1525, -26.50476, 28.35921)),
      ("Helsinki", Array(31528, 558457, 17, 60.16952, 24.93545)),
      ("Talca", Array(11293, 197479, 102, -35.4264, -71.65542)),
      ("Rajon Wrabniza", null),
      ("Rennes", Array(34137, 209375, 74, 48.11198, -1.67429)),
      ("Raipur", Array(48278, 679995, 291, 21.23333, 81.63333)),
      ("Gloucester", Array(41477, 150053, 0, 51.86568, -2.2431109057, 29781, 16, 42.61593, -70.66199)),
      ("Oberhausen", Array(18658, 219176, 25, 51.47805, 6.8625)),
      ("Changchun", Array(13236, 4193073, 222, 43.88, 125.32278)),
      ("Antwerpen", Array(6728, 459805, 7, 51.21989, 4.40346)),
      ("Herne", Array(19948, 172108, 0, 51.5388, 7.22572)),
      ("??????", null),
      ("Hanoi", Array(118303, 1431270, 16, 21.0245, 105.84117)),
      ("???????", null),
      ("Swale", null),
      ("Orakei", null),
      ("Lewisville", Array(106875, 104039, 160, 33.04623, -96.99417)),
      ("Dachsberg (Sudschwarzwald)", null),
      ("Rajon Owtscha kupel", null),
      ("Parma", Array(55598, 146299, 57, 44.79935, 10.32618113530, 79937, 264, 41.40477, -81.72291)),
      ("Neunkirchen", Array(18350, 49843, 252, 49.34449, 7.18045)),
      ("Almer?a", null),
      ("Hohe Borde", null),
      ("Rother", null),
      ("Steinburg", null),
      ("Eindhoven", Array(76238, 209620, 0, 51.44083, 5.47778)),
      ("Voerde (Niederrhein)", null),
      ("Kuhlungsborn", null),
      ("Breslau", null),
      ("Nissewaard", Array(133821, 84588, 0, 51.83716, 4.2754)),
      ("Gronau", Array(20385, 46161, 27, 52.21099, 7.02238)),
      ("Rouen", Array(33167, 112787, 10, 49.44313, 1.09932)),
      ("The Borough District of Sligo", null),
      ("Las Condes", null),
      ("Bratislava", Array(97041, 423737, 134, 48.14816, 17.10674)),
      ("Hochheim", null),
      ("Erlenbach im Simmental", null),
      ("Dobl-Zwaring", null),
      ("Essen", Array(21014, 593085, 116, 51.45657, 7.01228)),
      ("Gorna Orjachowiza", null),
      ("Emmen", Array(11208, 26889, 448, 47.07819, 8.2733176048, 57010, 0, 52.77917, 6.90694)),
      ("Kattowitz", null),
      ("Mailand", null),
      ("Kiel", Array(19635, 232758, 5, 54.32133, 10.13489)),
      ("Allerheiligen bei Wildon", null),
      ("Overbetuwe", null),
      ("Zahna-Elster", null),
      ("Rio de Janeiro", Array(123738, 6323037, 0, -22.92008, -43.33069)),
      ("Schwerin", Array(16544, 96641, 0, 53.62937, 11.41316)),
      ("Zuidplas", null),
      ("Schumen", null),
      ("North Devon", null),
      ("Muntinlupa", null),
      ("Jena", Array(19808, 104712, 0, 50.92878, 11.5899)),
      ("Hitzacker", null),
      ("Glucksburg", null),
      ("Smoljan", null),
      ("Haarlem", Array(75332, 147590, 2, 52.38084, 4.63683)),
      ("Bergisch Gladbach", Array(21827, 106184, 0, 50.9856, 7.13298)),
      ("Rajon Isgrew", null),
      ("Nairobi", Array(62892, 2750547, 1661, -1.28333, 36.81667)),
      ("Zwolle", Array(75091, 111805, 0, 52.5125, 6.09444)),
      ("Biebergemund", null),
      ("Seoul", Array(63402, 10349312, 0, 37.566, 126.9784)),
      ("Manching", null),
      ("Rajon Podujana", null),
      ("Denton", Array(42810, 27464, 0, 53.45678, -2.11822107241, 131044, 201, 33.21484, -97.13307)),
      ("Schwaig bei Nurnberg", null),
      ("Groningen", Array(76305, 181194, 7, 53.21917, 6.56667)),
      ("Bornichen/Erzgebirge", null),
      ("Rajon Slatina", null),
      ("Florsheim", null),
      ("Kimitoon", null),
      ("Helpsen", null),
      ("Blankenburg", null),
      ("Kjustendil", null),
      ("Shenzhen", Array(125927, 10000000, 0, 22.55653, 113.9859)),
      ("Aliso Viejo", Array(114472, 50195, 143, 33.56504, -117.72712)),
      ("Gemeinde Korneuburg", null),
      ("Sollentuna kommun", null),
      ("Leinfelden-Echterdingen", Array(19786, 36672, 432, 48.69406, 9.16809)),
      ("Bangkok", Array(97833, 5104476, 2, 13.75398, 100.50144)),
      ("Salzatal", null),
      ("Hamm", Array(21077, 185327, 63, 51.68033, 7.82089)),
      ("Kiischpelt", null),
      ("Alkmaar", Array(76329, 94853, 0, 52.63167, 4.74861)),
      ("Bezirk Bratislava III", null),
      ("Jade", null),
      ("Frankfurt am Main", Array(21056, 650000, 112, 50.11552, 8.68417)),
      ("Augsburg", Array(22533, 259196, 498, 48.37154, 10.89851)),
      ("Uppsala", Array(95670, 149245, 15, 59.85882, 17.63889)),
      ("Basel", Array(11328, 164488, 0, 47.55839, 7.57327)),
      ("Gemeinde Ebreichsdorf", null),
      ("Bonn", Array(21751, 313125, 60, 50.73438, 7.09549)),
      ("Treviso", Array(53738, 77604, 15, 45.66673, 12.2416)),
      ("Iksan", Array(63571, 283501, 0, 35.94389, 126.95444)),
      ("Aichtal", null),
      ("Remshalden", null),
      ("Rajon Losenez", null),
      ("Tscheljabinsk", null),
      ("Am Gro?en Bruch", null),
      ("Saarbrucken", null),
      ("Gemeinde Michelhausen", null),
      ("Vaxjo", null),
      ("Gemeinde Sierndorf", null),
      ("Bad Soden", null),
      ("Bielitz-Biala", null),
      ("Sofia", Array(6275, 1152556, 595, 42.69751, 23.32415)),
      ("Staffanstorps kommun", null),
      ("Gemeinde Waidhofen an der Ybbs", null),
      ("Rajon Studentski", null),
      ("Koln", null),
      ("Moosburg", null),
      ("Blagoewgrad", null),
      ("Reigate and Banstead", null),
      ("Naucalpan", null),
      ("Caloocan", null),
      ("Enscheid", null),
      ("Innsbruck", Array(2773, 132493, 570, 47.26266, 11.39454)),
      ("Mladost", null),
      ("Regensburg", Array(17316, 129151, 343, 49.01513, 12.10161)),
      ("Salzgitter", Array(17589, 101079, 90, 52.15705, 10.4154)),
      ("Vogtei", null),
      ("Gemeinde Himberg", null),
      ("Budapest", Array(47225, 1741041, 102, 47.49801, 19.03991)),
      ("Washington D.C.", null),
      ("Daressalam", null),
      ("Surbo", null),
      ("Rajon Krasna poljana", null),
      ("Zaanstad", Array(75241, 140085, 0, 52.45313, 4.81356)),
      ("Sittard-Geleen", null),
      ("Hildesheim", Array(19249, 103052, 93, 52.15077, 9.95112)),
      ("Aichwald", null),
      ("Waldshut-Tiengen", Array(17016, 22404, 341, 47.62323, 8.21717)),
      ("Wealden", null),
      ("Neder-Betuwe", null),
      ("Haina", null),
      ("Bremerhaven", Array(21198, 117446, 2, 53.55021, 8.57673)),
      ("Leudal", null),
      ("Santiago", Array(121152, 49082, 409, -29.11368, -54.7335911369, 4837295, 521, -33.45694, -70.64827)),
      ("Engelhartszell an der Donau", null),
      ("Altdorf (UR)", null),
      ("?rebro", null),
      ("Guatemala-Stadt", null),
      ("Herzberg am Harz", null),
      ("Studen (BE)", null),
      ("Jettingen", null),
      ("Pistoia", Array(55722, 73832, 67, 43.93064, 10.92365)),
      ("Uhldingen-Muhlhofen", null),
      ("Lubeck", null),
      ("Heidesee", null),
      ("Gelsenkirchen", Array(20845, 270028, 48, 51.50508, 7.09654)),
      ("Bad Homburg vor der Hohe", null),
      ("Jonkoping", null),
      ("Piacenza", Array(54897, 93228, 61, 45.05242, 9.69342)),
      ("Tarnowo", null),
      ("Ravensburg", Array(18019, 48825, 450, 47.78198, 9.61062)),
      ("Leipzig", Array(19317, 504971, 113, 51.33962, 12.37129)),
      ("Rajon Wasraschdana", null),
      ("Seiersberg-Pirka", null),
      ("Moriken-Wildegg", null),
      ("Hitzendorf", null),
      ("Dortmund", Array(21334, 588462, 0, 51.51494, 7.466)),
      ("Plewen", null),
      ("???????", null),
      ("Remseck am Neckar", null),
      ("Gemeinde Mariasdorf", null),
      ("Biesenthal", null),
      ("?????", null),
      ("Rajon Oborischte", null),
      ("St Albans", Array(41158, 84561, 0, 51.75, -0.33333)),
      ("Pe?alol?n", null),
      ("Ammerbuch", null),
      ("Denver", Array(114786, 682545, 1609, 39.73915, -104.9847)),
      ("Linkoping", null),
      ("Oregon City", Array(116138, 35831, 38, 45.35734, -122.60676)),
      ("Providencia", null),
      ("Hoeksche Waard", null),
      ("Turin", Array(53868, 870456, 239, 45.07049, 7.68682)),
      ("????? ??????? ???????", null),
      ("Borsele", null),
      ("Yaound? VI", null),
      ("West Betuwe", null)
    )

  def getCityId(city: String): Integer = {
    get(city, 0)
  }

  def getPopulation(city: String): Integer = {
    get(city, 1)
  }

  def getElevationMeters(city: String): Integer = {
    get(city, 2)
  }

  private def get(city: String, index: Int): Integer = {
    if (data.get(city).isEmpty) {
      return null
    }
    try {
      val t = data.get(city).orNull
      t(index).toInt
    } catch {
      case _: Exception => null
    }
  }
}
