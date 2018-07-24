package no.hvl.erikbjorvik.nu3app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
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
import no.hvl.erikbjorvik.nu3app.Misc.GridAdapter;
import no.hvl.erikbjorvik.nu3app.Misc.MainHelper;
import no.hvl.erikbjorvik.nu3app.Models.Consumable;
import no.hvl.erikbjorvik.nu3app.Models.PredefinedMeal;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Consumable> meals;

    GridView gridview;
    public static GridAdapter gridAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        this.meals = new ArrayList<Consumable>();
        this.gridAdapter = new GridAdapter(this, this.meals);

        gridview = (GridView) findViewById(R.id.predefinedMealsTable);
        gridview.setAdapter(this.gridAdapter);
        request("http://nu3.azurewebsites.net/api/consumable/category/general");

        /*ListView listView = (ListView) findViewById(R.id.predefinedMealsTable);
        this.adapter = new FoodListWithImagesArrayAdapter(
                getApplicationContext(), R.layout.foodlistwithimages, this.meals);
        listView.setAdapter(this.adapter);
        request("http://nu3.azurewebsites.net/api/consumable/category/general");*/

    }

    private void request(String url) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            ObjectMapper mapper = new ObjectMapper();
                            ArrayList<Consumable> newList = mapper.readValue(response, new TypeReference<List<Consumable>>(){});
                            MainActivity.meals.clear();
                            MainActivity.meals.addAll(newList);
                            MainActivity.gridAdapter.notifyDataSetChanged();

                            Log.i("JSON parsing", "Successed" + MainActivity.meals.toString());

                        }
                        catch(Exception e) {
                            Log.e("JSON parsing", e.toString());
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

    public void mealButtonClick(View v) {
        String tag = v.getTag().toString();
        Toast.makeText(MainActivity.this, tag, Toast.LENGTH_SHORT).show();
        if (tag.equals("dinner")) {
            request("http://nu3.azurewebsites.net/api/consumable/category/"+tag);
        }
        else {
            request("http://nu3.azurewebsites.net/api/consumable/category/general");

        }
    }

}
