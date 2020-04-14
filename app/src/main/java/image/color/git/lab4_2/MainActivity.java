package image.color.git.lab4_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView companyOne;
    private TextView companyTwo;
    private TextView companyThree;
    private TextView companyFour;
    private TextView priceOne;
    private TextView priceTwo;
    private TextView priceThree;
    private TextView priceFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue queue = Volley.newRequestQueue(this);
        companyOne = findViewById(R.id.companyOne);
        companyTwo = findViewById(R.id.companyTwo);
        companyThree = findViewById(R.id.companyThree);
        companyFour = findViewById(R.id.companyFour);

        priceOne = findViewById(R.id.priceOne);
        priceTwo = findViewById(R.id.priceTwo);
        priceThree = findViewById(R.id.priceThree);
        priceFour = findViewById(R.id.priceFour);

        String url = "https://financialmodelingprep.com/api/v3/stock/real-time-price/GOOGL,AAPL,FB,NOK";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
//                        textView.setText("Response: " + response.toString());
                        Log.d("Lab4", "" + response);
//                        Log.d("Lab4", "" + response);
                        try {
                            JSONArray companiesPriceList =  (JSONArray) response.get("companiesPriceList");
                            Log.d("Lab4", "companies array length: " + companiesPriceList.length());
                            for (int i=0; i < companiesPriceList.length(); i++) {
                                JSONObject oneObject = companiesPriceList.getJSONObject(i);
//                                Log.d("Lab4","symbol: " + oneObject.get("symbol"));
                                if ( i == 0) {
                                    companyOne.setText(oneObject.get("symbol").toString());
                                    priceOne.setText(oneObject.get("price").toString());
                                }
                                if (i == 1 ) {
                                    companyTwo.setText(oneObject.get("symbol").toString());
                                    priceTwo.setText(oneObject.get("price").toString());
                                }
                                if ( i == 2 ) {
                                    companyThree.setText(oneObject.get("symbol").toString());
                                    priceThree.setText(oneObject.get("price").toString());
                                }
                                if ( i == 3 ) {
                                    companyFour.setText(oneObject.get("symbol").toString());
                                    priceFour.setText(oneObject.get("price").toString());
                                }
                            }
                            Log.d("Lab4", "companies array: " + companiesPriceList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//                        String appleStock = (String) response.get()
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d("Lab4", "" + error);
                    }
                });

        queue.add(jsonObjectRequest);
// Access the RequestQueue through your singleton class.
//        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

//    private static void parseEmployeeObject(JSONObject employee)
//    {
//        //Get employee object within list
//        JSONObject employeeObject = (JSONObject) employee.get("employee");
//
//        //Get employee first name
//        String firstName = (String) employeeObject.get("firstName");
//        System.out.println(firstName);
//
//        //Get employee last name
//        String lastName = (String) employeeObject.get("lastName");
//        System.out.println(lastName);
//
//        //Get employee website name
//        String website = (String) employeeObject.get("website");
//        System.out.println(website);
//    }

}
