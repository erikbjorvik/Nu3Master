package no.hvl.erikbjorvik.nu3app.Misc;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import no.hvl.erikbjorvik.nu3app.Enums.MealCategory;
import no.hvl.erikbjorvik.nu3app.MainActivity;
import no.hvl.erikbjorvik.nu3app.Models.ConsumableIntake;
import no.hvl.erikbjorvik.nu3app.R;

/**
 * Created by erikbjorvik on 05.08.2018.
 */

public class RequestHelper {

    public final static int MAIN_CALLBACK = 0;
    public final static int KCAL_BUTTONS = 1;
    public final static int NOTIFY_GRID_ADAPTER = 2;

    public static String userId = "5a9aae60d9b9959ec24d889d";
    private Activity context;

    public RequestHelper(Activity context) {
        this.context = context;
    }

    private void request(int method, String url, final JSONObject body) {
        url = "http://nu3.azurewebsites.net/" + url;
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this.context);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(method, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.i("JSON parsing", "Successed" + MainActivity.meals.toString());

                        }
                        catch(Exception e) {
                            Log.e("JSON parsing", e.toString());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("onErrorResponse", error.toString());
            }
        }) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                return body.toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void request(int method, String url, final JSONObject body, final int callback) {
        url = "http://nu3.azurewebsites.net/" + url;
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(method, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        switch(callback) {
                            case RequestHelper.MAIN_CALLBACK:
                                mainCallback(response);
                            break;
                            case RequestHelper.KCAL_BUTTONS:
                                sumcategoryCallback(response);
                                break;
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Request failed", error.networkResponse.toString());
            }
        }) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                return body.toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public static String getTodayString() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date today = Calendar.getInstance().getTime();
        return df.format(today);
    }

    private static void mainCallback(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ArrayList<ConsumableIntake> newList = mapper.readValue(response, new TypeReference<List<ConsumableIntake>>(){});
            MainActivity.consumableIntakes.clear();
            MainActivity.consumableIntakes.addAll(newList);
            MainActivity.gridAdapter.notifyDataSetChanged();

            Log.i("JSON parsing", "Successed" + MainActivity.meals.toString());

        }
        catch(Exception e) {
            Log.e("JSON parsing", e.toString());
        }
    }

    private void sumcategoryCallback(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<MealCategory, Float> newList = mapper.readValue(response, new TypeReference<Map<MealCategory, Float>>(){});
            MainActivity.mealsAndIntake = newList;

            Button breakfastButton = (Button) context.findViewById(R.id.breakfastButton);
            Button lunchButton = (Button) context.findViewById(R.id.lunchButton);
            Button dinnerButton = (Button) context.findViewById(R.id.dinnerButton);
            Button supperButton = (Button) context.findViewById(R.id.supperButton);
            Button snacksButton = (Button) context.findViewById(R.id.snacksButton);

            breakfastButton.setText("BREAKFAST: " + newList.get(MealCategory.Breakfast).toString().replace(
                    ".0","") + " KCAL");
            lunchButton.setText("LUNCH: " + newList.get(MealCategory.Lunch).toString().replace(
                    ".0","") + " KCAL");
            dinnerButton.setText("DINNER: " + newList.get(MealCategory.Dinner).toString().replace(
                    ".0","") + " KCAL");
            supperButton.setText("SUPPER: " + newList.get(MealCategory.Supper).toString().replace(
                    ".0","") + " KCAL");
            snacksButton.setText("SNACKS: " + newList.get(MealCategory.Snack).toString().replace(
                    ".0","") + " KCAL");
        }
        catch(Exception e) {
            Log.e("JSON parsing", e.toString());
        }
    }
}
