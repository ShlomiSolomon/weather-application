import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Weather {
    private String city;
    private Double temperature;
    private Double prevtemperature;
    private long time ;
    private long wind ;

    public long getWind() {
        return wind;
    }

    public void setWind(long wind) {
        this.wind = wind;
    }

    public String getTime() {
        Date timeDate = new Date( time * 1000L );
        Calendar timecal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat( "dd-MM-yyyy HH:mm:ss z" );
        timecal.setTime( timeDate );
        String formattedDate = sdf.format( timeDate );
        return formattedDate;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Weather() {

    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getPrevTemperature() {
        return prevtemperature;
    }

    public void setPrevTemperature(Double prevtemperature) {
        if (this.getCity()==city)
            this.prevtemperature=getTemperature();
    }

    public void print() {
        System.out.println( "\nTime: " + getTime() +
                "\nCity Name: " + getCity() +
                "\nTemperature: " + Math.round(getTemperature()) +
                "°C\nWind speed: " + getWind()

        );
    }

    public void printWarning(long threshold) {
        if (getPrevTemperature()!=null) {
            Double per =  (((getPrevTemperature() - getTemperature()) / getPrevTemperature()) * 100);
            if (per > threshold) {
                System.out.println( "warning ! The temperature result changed by " + per + " %" );
            }
        }
    }
}
