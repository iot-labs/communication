import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class GetWeather {
    public static void main(String[] args) throws Exception {

        GetWeather http = new Main();
        String response = http.sendGet();
        int value = http.getPrecipitationStatus(response);

        // 0: no precipitation / 1: rain / 2: snow
        System.out.println("Precipitation Value: "+value);
    }

    // HTTP GET request
    private String sendGet() throws Exception {

        String city = "seoul";
        String country = "KR";
        String appID = "3c1d53facddc987dbce12048b5fe27b3";
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "," + country + "&units=" + "Metric" + "&mode=xml&APPID="+appID;

        URL owmUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) owmUrl.openConnection();

        // set request method and user-agent
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");

        // get value
        int responseCode = conn.getResponseCode();
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
        return response.toString();
    }

    private int getPrecipitationStatus(String input) {
        String substringPrecipitation = "precipitation mode=\"";
        int substringLength = substringPrecipitation.length();
        int index = input.indexOf(substringPrecipitation);

        System.out.println(index);
        String substringIntermediate = input.substring(index+substringLength);
        System.out.println(substringIntermediate);
        index = substringIntermediate.indexOf("\"");
        System.out.println(index);
        String precipitationValue = substringIntermediate.substring(0, index);
        System.out.println(precipitationValue);

        // no precipitation
        if (precipitationValue.equals("no")) { return 0; }
        // rain
        else if (precipitationValue.equals("rain")) { return 1; }
        // snow
        else { return 2; }
    }
}