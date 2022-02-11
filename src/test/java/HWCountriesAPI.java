
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Scanner;


public class HWCountriesAPI {
    private static Scanner usrI;

    public static void main(String[] args) throws IOException, JSONException {
        usrI = new Scanner(System.in);
        System.out.println("Enter Country Name:");
        String usrInput = usrI.next();
        getData(usrInput);
    }

        private static void getData(String usrInput) throws IOException {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://restcountries.com/v3.1/name/" + usrInput)
                    .build();
            Response response = client.newCall(request).execute();
            String jsonData = response.body().string();
            if(response.code() == 404){
                System.out.println("County not found!");
                tryAgain();
            }

            // parse JSON
            JSONArray mainJsonArray = new JSONArray(jsonData);
            JSONObject mainJsonObject = (JSONObject) mainJsonArray.get(0);
            String region = mainJsonObject.getString("region");
//        array
            JSONArray borders = mainJsonObject.getJSONArray("borders");
//        outside object
            JSONObject curr = mainJsonObject.getJSONObject("currencies");
//        inner object
            JSONObject ILS = curr.getJSONObject("ILS");
            String symbol = ILS.getString("symbol");
            // get value
            System.out.println("region: " + region + "\n" + "borders: " + borders + "\n" + "symbol: " + symbol);


        }

        private static void tryAgain() throws IOException{
            System.out.println("Enter counte name or q to exit:");
            String usrInput = usrI.next();
            if(usrInput.equals(("q"))){
                getData(usrInput);
            }
            else{
                System.exit(0);
            }
        }





}
