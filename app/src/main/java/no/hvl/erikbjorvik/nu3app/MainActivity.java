package no.hvl.erikbjorvik.nu3app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;

import no.hvl.erikbjorvik.nu3app.Enums.MealCategory;
import no.hvl.erikbjorvik.nu3app.Misc.FoodListWithImagesArrayAdapter;
import no.hvl.erikbjorvik.nu3app.Misc.GridAdapter;
import no.hvl.erikbjorvik.nu3app.Misc.MainHelper;
import no.hvl.erikbjorvik.nu3app.Misc.RequestHelper;
import no.hvl.erikbjorvik.nu3app.Models.Consumable;
import no.hvl.erikbjorvik.nu3app.Models.ConsumableIntake;
import no.hvl.erikbjorvik.nu3app.Models.MealsAndIntake;
import no.hvl.erikbjorvik.nu3app.Models.PredefinedMeal;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Consumable> meals;
    public static ArrayList<ConsumableIntake> consumableIntakes;
    public static Map<MealCategory, Float> mealsAndIntake;
    private RequestHelper requestHelper = new RequestHelper(this);

    GridView gridview;
    public static GridAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        this.meals = new ArrayList<Consumable>();
        this.consumableIntakes = new ArrayList<ConsumableIntake>();
        this.gridAdapter = new GridAdapter(this, this.consumableIntakes);

        gridview = (GridView) findViewById(R.id.predefinedMealsTable);
        gridview.setAdapter(this.gridAdapter);
        //requestHelper.request("http://nu3.azurewebsites.net/api/consumable/intake/5a9aae60d9b9959ec24d889d/"+RequestHelper.getTodayString()+"/Dinner",0);
        //requestHelper.request("http://nu3.azurewebsites.net/api/intake/meals/5a9aae60d9b9959ec24d889d/"+RequestHelper.getTodayString(),1);
        requestHelper.request(Request.Method.GET,"api/consumable/intake/"+RequestHelper.userId+"/"+RequestHelper.getTodayString()+"/Dinner", null, RequestHelper.MAIN_CALLBACK);
        requestHelper.request(Request.Method.GET,"api/intake/meals/"+RequestHelper.userId+"/"+RequestHelper.getTodayString(), null, RequestHelper.KCAL_BUTTONS);

    }

    public void onKcalClick(View view) {
        Intent intent = new Intent(this, WebActivity.class);
        startActivity(intent);
    }

    public void mealButtonClick(View v) {
        String tag = v.getTag().toString();
        Toast.makeText(MainActivity.this, tag, Toast.LENGTH_SHORT).show();

        requestHelper.request(Request.Method.GET,"api/consumable/category/"+tag, null, RequestHelper.MAIN_CALLBACK);
        requestHelper.request(Request.Method.GET,"api/consumable/intake/"+RequestHelper.userId+"/"+RequestHelper.getTodayString()+"/"+tag, null, RequestHelper.MAIN_CALLBACK);

    }


}
