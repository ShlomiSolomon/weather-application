import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class GetFromOpenWeatherMap {
    public static void main(String cityName, long threshold) {


        try {

            URL url = new URL( "http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&units=metric&&appid=e735b6b632e6c008be941b8dbdb346d4 " );

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod( "GET" );
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException( "HttpResponseCode: " + responsecode );
            } else {

                String inline = "";
                Scanner scanner = new Scanner( url.openStream() );

                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }
                scanner.close();

                JSONParser parse = new JSONParser();
                JSONObject data_obj = (JSONObject) parse.parse( inline );
                JSONObject obj = (JSONObject) data_obj;

                Weather weather = new Weather();
                weather.setCity( cityName );
                weather.setTime( (long) obj.get( "dt" ) );
                obj = (JSONObject) data_obj.get( "main" );
                weather.setTemperature((Double) obj.get( "temp" ));
                obj = (JSONObject) data_obj.get( "wind" );
                weather.setWind( Math.round( (Double) obj.get("speed" ) ));
                weather.print();
                weather.setPrevTemperature(weather.getTemperature());
                weather.printWarning(threshold);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
