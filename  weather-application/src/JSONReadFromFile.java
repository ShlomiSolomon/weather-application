import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Timer;
import java.util.TimerTask;

public class JSONReadFromFile {
    @SuppressWarnings("unchecked")
    public static void main() {


        JSONParser parser = new JSONParser();
        String cityName = null;
        try {
            Object obj = parser.parse( new FileReader( "JSONExample.json" ) );

            JSONArray jsonArray = (JSONArray) obj;

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get( i );
                long frequency = (long) jsonObject.get("frequency");
                long threshold = (long) jsonObject.get("threshold");
                cityName = (String) jsonObject.get( "city_name" );

                Timer timer = new Timer();
                String finalCityName = cityName;
                timer.schedule( new TimerTask() {
                    @Override
                    public void run() {
                        GetFromOpenWeatherMap.main( finalCityName,threshold );

                    }
                }, 0, frequency*1000);  // (based on its own frequency) (*1000 - in seconds)

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}