package no.hvl.erikbjorvik.nu3app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import no.hvl.erikbjorvik.nu3app.Misc.FoodListWithImagesArrayAdapter;
import no.hvl.erikbjorvik.nu3app.Models.PredefinedMeal;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        request();
    }

    private void request() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://nu3.azurewebsites.net/api/predefinedmeals";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<PredefinedMeal> meals;
                        try {
                            ObjectMapper mapper = new ObjectMapper();
                            meals = mapper.readValue(response, new TypeReference<List<PredefinedMeal>>(){});
                            ListView listView = (ListView) findViewById(R.id.predefinedMealsTable);

                            FoodListWithImagesArrayAdapter adapter = new FoodListWithImagesArrayAdapter(
                                    getApplicationContext(), R.layout.foodlistwithimages, meals);

                            listView.setAdapter(adapter);
                        } catch(Exception e) {
                            ListView listView = (ListView) findViewById(R.id.predefinedMealsTable);

                            ArrayList<PredefinedMeal> predefinedMealsArrayList = new ArrayList<>();
                            predefinedMealsArrayList.add(new PredefinedMeal("Egg ja"));
                            predefinedMealsArrayList.add(new PredefinedMeal("Lasagne"));

                            FoodListWithImagesArrayAdapter adapter = new FoodListWithImagesArrayAdapter(
                                    getApplicationContext(), R.layout.foodlistwithimages, predefinedMealsArrayList);

                            listView.setAdapter(adapter);
                            Log.w("JSON parsing", "Could not parse response to json:" + response);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "That didn't work!", Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
