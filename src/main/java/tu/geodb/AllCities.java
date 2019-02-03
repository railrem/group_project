package tu.geodb;

import com.wirefreethought.geodb.client.GeoDbApi;
import com.wirefreethought.geodb.client.model.*;
import com.wirefreethought.geodb.client.net.GeoDbApiClient;
import com.wirefreethought.geodb.client.request.FindCitiesRequest;
import com.wirefreethought.geodb.client.request.FindCityRequest;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static java.lang.Thread.*;

public class AllCities {

    public static void main(String[] args) {
//        String cities = "Mainz,Triadica,Hanover,Antwerp,Palermo,Ilinden,Linz,Frankenberg";
        String cities = "Mainz,Triadica,Hanover,Antwerp,Palermo,Ilinden,Linz,Frankenberg,Abuja,Puerto Plata,Ludwigshafen am Rhein,Silistra,Brunswick,Salzburg,Hatten,Liulin,Mülheim,Klagenfurt,Reutlingen,Ulm,Ville de Bruxelles - Stad Brussel,Lohne,Verden,Potsdam,Legden,Uhldingen-Mühlhofen,苏州工业园区直属镇,Nordharz,Manchester,Izgrev,Frankfurt,Heringsdorf,Ruse,Weißenburg i. Bay.,Heilbronn,Lübeck,Offenbach am Main,Siegen,Тракия,Eastbourne,Stockholm,Pforzheim,Vidin,Секирово,Valsamoggia,Staufenberg,Savski venac Municipality,Horst aan de Maas,Weil am Rhein,Wuppertal,Modena,Erfurt,Bologna,Bickenbach,Poduyane,Chemnitz,Bochum,Szczecin,Recklinghausen,Bad Teinach-Zavelstein,Serdika,Darmstadt,Würzburg,Berlin,Gemeinde Strasshof an der Nordbahn,San Diego,Kötz,Северен,Nuremberg,Portland,Neuss,Kladruby,Florence,Söhrewald,Oborishte,Kernen im Remstal,Grafschaft,Lozenec,Dresden,Graz,Perth,Geestland,Kyustendil,Leverkusen,Rotenburg,Flörsheim,Vaasa,Almeria,Bielefeld,Централен,Washington,Asenovgrad,Erlangen,Wachtberg,Bishkek,Zemun Municipality,Tarnovo,Gemeinde Pöggstall,Paris,Mendip,Reinhardshagen,Oberkrämer,Ostfildern,Blagoevgrad,Linsengericht,Oldenburg,Bodegraven-Reeuwijk,Kremikovci,Южен,Freiburg im Breisgau,Rostock,Utrechtse Heuvelrug,Burgas,Dusseldorf,Münster,Zurich,Bremen,Ñuñoa,Riga,Langenfeld,Duisburg,Leuven,Bad Homburg vor der Höhe,Dachsberg (Südschwarzwald),Arnhem,Wiesbaden,Toronto,Kassel,Paderborn,Barcelona,Filderstadt,Solingen,Ternopil,Warsaw,Halle (Saale),Studentski,Gemeinde Petzenkirchen,Haarlemmermeer,Krefeld,Mendoza,Dar es Salaam,São Mamede de Infesta e Senhora da Hora,Chelyabinsk,Moers,Amsterdam,Nuthetal,Bottrop,Fuldatal,The Hague,Aberdeen,Slatina,Rheinau,Vazrajdane,Wolfsburg,Svishtov,Ilsede,Mannheim,Amtsberg,Sonnenbühl,Langen,Kyiv,Bobritzsch-Hilbersdorf,Agra,Erftstadt,Aachen,Bristol,Ingolstadt,Smolyan,Wernau (Neckar),Karlsruhe,Bruges,Pazardzhik,Liederbach,Gothenburg,Osnabrück,Leer,Koblenz,Gemeinde Wöllersdorf-Steinabrückl,Wust-Fischbeck,Krasna poliana,Hofheim,Stuttgart,Trier,Utrecht,Lübbenau/Spreewald,Sredec,Heidelberg,Oberhausen,Herne,Hanoi,Западен,Mönchengladbach,Stara Zagora,Haskovo,Neunkirchen,Hünstetten,Eindhoven,Vitosha,Las Condes,Dupnitsa,Dobl-Zwaring,Essen,Poznań,Kiel,Varna,Overbetuwe,Ovcha kupel,Schwerin,Jena,Hitzacker,Haarlem,Bergisch Gladbach,Nadejda,Nairobi,Zwolle,Seoul,Wroclaw,Groningen,Rgbg,Blankenburg,Gemeinde Korneuburg,Kühlungsborn,Leinfelden-Echterdingen,Augsburg,Uppsala,Basel,Bonn,Remshalden,Aichtal,Gemeinde Michelhausen,Gemeinde Sierndorf,Am Großen Bruch,Bad Soden,Sofia,Moosburg,Fürth,Mladost,Innsbruck,Budapest,Gemeinde Himberg,Vrabnitsa,Korntal-Münchingen,Waldshut-Tiengen,Iskar,Wealden,Biebergemünd,Cottbus - Chóśebuz,Örebro,Studen (BE),Herzberg am Harz,Krasno selo,Gelsenkirchen,Cologne,Seiersberg-Pirka,Leipzig,Hitzendorf,Dortmund,Източен,null,Göttingen,Munich,Oregon City,Providencia,Szeged";
        StringBuilder res = new StringBuilder("{\"items\": [");

        int i = 0;
        int counter = 0;
        int all = (cities.split(",")).length;


        for (String city : cities.split(",")) {
            counter++;
            System.out.println(counter + "/" + all);
            i++;
            if (i == 3) {
                i = 1;
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            res.append(getCityRow(city));

        }

        String resultat = res.toString().substring(0, res.length() - 2) + "]}";


        try (PrintWriter out = new PrintWriter("geo.json")) {
            out.println(resultat);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(resultat);
    }


    private static String getCityRow(String city) {
        GeoDbApiClient apiClient = new GeoDbApiClient(GeoDbInstanceType.FREE);
        GeoDbApi geoDbApi = new GeoDbApi(apiClient);

        FindCitiesRequest fcr = FindCitiesRequest.builder().namePrefix(city).limit(5).languageCode("en").build();

        geoDbApi.findCities(fcr);

        CitiesResponse citiesResponse = geoDbApi.findCities(
                fcr
        );

        StringBuilder res = new StringBuilder("");


        String id = "none";
        String cityN = "none";
        String popN = "none";
        String meterN = "none";
        for (CitySummary cs : citiesResponse.getData()) {
            FindCityRequest cr = FindCityRequest.builder().cityId(cs.getId().toString()).build();
            CityResponse cres = geoDbApi.findCity(cr);
            CityDetails cd = cres.getData();
            id = cs.getId().toString();
            cityN = cd.getCity();
            popN = (cd.getPopulation() != null) ? cd.getPopulation().toString() : "none";
            meterN = (cd.getElevationMeters() != null) ? cd.getElevationMeters().toString() : "none";
            break;
        }

        res.append("{\"id\":")
                .append(id)
                .append(",\"src_name\":\"")
                .append(city)
                .append("\",\"name\":\"")
                .append(cityN)
                .append("\", \"population\":")
                .append(popN)
                .append(", \"above_sea_level\":")
                .append(meterN)
                .append("},\n");
        return res.toString();
    }
}
