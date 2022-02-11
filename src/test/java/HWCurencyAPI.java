import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Scanner;

public class HWCurencyAPI {

    public static void main(String[] args) throws IOException {
        Scanner usrInput = new Scanner(System.in);
        System.out.println("Welcome to currency converter program:");
        System.out.println("Enter ammount to convert:");
        String ammount = usrInput.next();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://api.exchangeratesapi.io/v1/latest?access_key=c19e180baa83ac8b9a3823ff4d3087e1&symbols=USD")
                .build();
        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();
        JSONObject mainJsonObject = new JSONObject(jsonData);
        JSONObject ratesJsonObject = mainJsonObject.getJSONObject("rates");
        System.out.println("Result is: " + (usrInput.nextDouble() * ratesJsonObject.getDouble("USD")));



    }
}
