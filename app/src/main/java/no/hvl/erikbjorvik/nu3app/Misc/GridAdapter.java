package no.hvl.erikbjorvik.nu3app.Misc;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import no.hvl.erikbjorvik.nu3app.MainActivity;
import no.hvl.erikbjorvik.nu3app.Models.Consumable;
import no.hvl.erikbjorvik.nu3app.Models.ConsumableIntake;
import no.hvl.erikbjorvik.nu3app.R;

public class GridAdapter extends BaseAdapter{

    private ArrayList<ConsumableIntake> list;
    Context context;
    private RequestHelper requestHelper;
    private static LayoutInflater inflater=null;
    public GridAdapter(MainActivity mainActivity, ArrayList<ConsumableIntake> list) {

        this.context = mainActivity;
        this.list = list;
        this.requestHelper = new RequestHelper(mainActivity);

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView gridText;
        ImageView gridImage;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null) {
            // Inflate and initialize your layout
            convertView = inflater.inflate(R.layout.grid_food, parent, false);
            holder = new ViewHolder();
            holder.consumableImage = (ImageView) convertView.findViewById(R.id.gridImage);
            holder.plusOne = (Button) convertView.findViewById(R.id.plusOne);
            holder.minusOne = (Button) convertView.findViewById(R.id.minusOne);
            holder.consumableText = (TextView) convertView.findViewById(R.id.gridText);
            holder.portionCounter = (TextView) convertView.findViewById(R.id.portionCount);
            holder.portionUnit = (TextView) convertView.findViewById(R.id.portionUnit);

            holder.plusOne.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ConsumableIntake current = MainActivity.consumableIntakes.get(position);
                    JSONObject body = new JSONObject();
                    try {
                        body.put("consumableId", current.consumable.getId());
                        body.put("mealType", current.consumable.getMealCategory());
                        body.put("amount", 1.0);
                        body.put("unit", current.consumable.getDefaultUnit());
                        body.put("accuracy", current.consumable.getNutrients().getAccuracy());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    requestHelper.request(Request.Method.POST,"api/intake/" + RequestHelper.userId, body, RequestHelper.NOTIFY_GRID_ADAPTER);
                    requestHelper.request(Request.Method.GET,"api/intake/meals/"+RequestHelper.userId+"/"+RequestHelper.getTodayString(), null, RequestHelper.KCAL_BUTTONS);

                    current.intake+=1.0f;
                    //holder.portionCounter.setText(""+(list.get(position).intake+1.0f));
                    //MainActivity.meals.get(position).setName("fisen");
                    MainActivity.gridAdapter.notifyDataSetChanged();
                }
            });

            holder.minusOne.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ConsumableIntake current = MainActivity.consumableIntakes.get(position);
                    JSONObject body = new JSONObject();
                    try {
                        body.put("consumableId", current.consumable.getId());
                        body.put("mealType", current.consumable.getMealCategory());
                        body.put("amount", -1.0);
                        body.put("unit", current.consumable.getDefaultUnit());
                        body.put("accuracy", current.consumable.getNutrients().getAccuracy());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    requestHelper.request(Request.Method.POST,"api/intake/" + RequestHelper.userId, body,
                            RequestHelper.NOTIFY_GRID_ADAPTER);
                    requestHelper.request(Request.Method.GET,"api/intake/meals/"+RequestHelper.userId+"/"+RequestHelper.getTodayString(), null, RequestHelper.KCAL_BUTTONS);

                    current.intake-=1.0f;
                    //holder.portionCounter.setText(""+(list.get(position).intake+1.0f));
                    //MainActivity.meals.get(position).setName("fisen");
                    MainActivity.gridAdapter.notifyDataSetChanged();

                }
            });
            // etc, etc...
            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();

        ConsumableIntake elem = list.get(position);
        // Do things that change for every grid item here, like
        Picasso.get().load(elem.consumable.getImagePath()).into(holder.consumableImage);
        holder.consumableText.setText(elem.consumable.getName());


        holder.portionCounter.setText(""+elem.intake);
        holder.portionUnit.setText(""+ (elem.consumable.getDefaultUnit()==null ? "Portion" : elem.consumable.getDefaultUnit().name()) );


        return convertView;
    }


    class ViewHolder {
        TextView consumableText;
        TextView portionCounter;
        TextView portionUnit;
        ImageView consumableImage;
        Button plusOne;
        Button minusOne;
    }

}