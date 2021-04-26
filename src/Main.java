import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws Exception {

        HttpURLConnectionExample obj = new HttpURLConnectionExample();

        System.out.println("Sending HTTP GET request");
        obj.sendGet();
    }
}

class HttpURLConnectionExample {

    void sendGet() throws Exception {

        String url = "https://www.nbkr.kg";

        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

        // optional default is GET
        httpClient.setRequestMethod("GET");

        //add request header
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = httpClient.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }

        //    Parsing from - nbkr.kg

            int a = 0;
            int b = 0;

            for (int i = 0; i < response.length()-8; i++) {
                if (response.substring(i, i+7).equals("<title>")){
                    a = i;
                }
                if (response.substring(i, i+8).equals("</title>")){
                    b = i;
                    break;
                }
            }

            String result = response.substring(a + 7, b).replace("  ", "");

            System.out.println();
            System.out.println("------------Result-------------- "  + "\n" + result + "\n" + "--------------------------------");

        }
    }
}


