package tu.nominatim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Java library for reverse geocoding using Nominatim
 *
 * @version 0.2
 *
 */
public class NominatimAPI {
    private final String NominatimInstance = "https://nominatim.openstreetmap.org";

    private int zoomLevel = 18;

    public static void main(String[] args){
        if(args.length < 1){
            System.out.println("use -help for instructions");
        }
        else if(args.length < 2){
            if(args[0].equals("-help")){
                System.out.println("Mandatory parameters:");
                System.out.println("   -lat [latitude]");
                System.out.println("   -lon [longitude]");
                System.out.println ("\nOptional parameters:");
                System.out.println("   -zoom [0-18] | from 0 (country) to 18 (street address), default 18");
                System.out.println("   -osmid       | show also osm id and osm type of the address");
                System.out.println("\nThis page:");
                System.out.println("   -help");
            }
            else
                System.err.println("invalid parameters, use -help for instructions");
        }
        else{
            boolean latSet = false;
            boolean lonSet = false;
            boolean osm = false;

            double lat = -200;
            double lon = -200;
            int zoom = 18;

            for (int i = 0; i < args.length; i++) {
                if(args[i].equals("-lat")){
                    try{
                        lat = Double.parseDouble(args[i+1]);
                    }
                    catch(NumberFormatException nfe){
                        System.out.println("Invalid latitude");
                        return;
                    }

                    latSet = true;
                    i++;
                    continue;
                }
                else if(args[i].equals("-lon")){
                    try{
                        lon = Double.parseDouble(args[i+1]);
                    }
                    catch(NumberFormatException nfe){
                        System.out.println("Invalid longitude");
                        return;
                    }

                    lonSet = true;
                    i++;
                    continue;
                }
                else if(args[i].equals("-zoom")){
                    try{
                        zoom = Integer.parseInt(args[i+1]);
                    }
                    catch(NumberFormatException nfe){
                        System.out.println("Invalid zoom");
                        return;
                    }

                    i++;
                    continue;
                }
                else if(args[i].equals("-osm")){
                    osm = true;
                }
                else{
                    System.err.println("invalid parameters, use -help for instructions");
                    return;
                }
            }

            if(latSet && lonSet){
                NominatimAPI nominatim = new NominatimAPI(zoom);
                Address address = nominatim.getAdress(lat, lon);
                System.out.println(address);
                if(osm){
                    System.out.print("OSM type: " + address.getOsmType()+", OSM id: " + address.getOsmId());
                }
            }
            else{
                System.err.println("please specifiy -lat and -lon, use -help for instructions");
            }
        }
    }

    public NominatimAPI(){}

    public NominatimAPI(int zoomLevel){
        if(zoomLevel < 0 || zoomLevel > 18){
            System.err.println("invalid zoom level, using default value");
            zoomLevel = 18;
        }

        this.zoomLevel = zoomLevel;
    }

    public Address getAdress(double lat, double lon){
        Address result = null;
        String urlString = NominatimInstance + "/reverse?format=json&addressdetails=1&lat=" + String.valueOf(lat) + "&lon=" + String.valueOf(lon) + "&zoom=" + zoomLevel + "&accept-language=de" ;
        try {
            result =new Address(getJSON(urlString), zoomLevel);
        } catch (IOException e) {
            System.err.println("Can't connect to server.");
            e.printStackTrace();
        }
        return result;
    }

    private String getJSON(String urlString) throws IOException{
        URL url = new URL(urlString);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.addRequestProperty("User-Agent", "Mozilla/4.76");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String text;
        StringBuilder result = new StringBuilder();
        while ((text = in.readLine()) != null)
            result.append(text);

        in.close();
        return result.toString();
    }
}
