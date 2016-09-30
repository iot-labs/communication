import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;


public class GetWeather {
    public static void main(String[] args) throws Exception {
        // 0: no precipitation / 1: rain / 2: snow
        GetWeather http = new GetWeather();
        if (new File("weather_ipc").exists()) {
            RandomAccessFile writer = new RandomAccessFile("weather_ipc", "rw");
            while (true) {
                Calendar cal = Calendar.getInstance();
                if (cal.getTime().getMinutes() == 0 && cal.getTime().getSeconds() == 0) {
                    String response = http.sendGet();
                    int value = http.getPrecipitationStatus(response);
                    writer.write(value);
                    System.out.printf("%d", value);
                }
            }
        } else {
            System.out.print("LED Tutorial not on");
        }
    }

    // HTTP GET request
    private String sendGet() throws Exception {
        String city = "seoul";
        String country = "KR";
        String unit = "Metric";
        String appID = "3c1d53facddc987dbce12048b5fe27b3";
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "," + country + "&units=" + unit + "&mode=xml&APPID=" + appID;

        URL owmUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) owmUrl.openConnection();

        // set request method and user-agent
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");

        // get value
        int responseCode = conn.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    private int getPrecipitationStatus(String input) {
        String substringPrecipitation = "mode=\"";
        int substringLength = substringPrecipitation.length();
        int index = input.indexOf(substringPrecipitation);

        String substringIntermediate = input.substring(index + substringLength);
        index = substringIntermediate.indexOf("\"");
        String precipitationValue = substringIntermediate.substring(0, index);

        switch (precipitationValue) {
            case "no":
                return 0;
            case "rain":
                return 1;
            case "snow":
                return 2;
            default:
                return -1;
        }
    }
}

