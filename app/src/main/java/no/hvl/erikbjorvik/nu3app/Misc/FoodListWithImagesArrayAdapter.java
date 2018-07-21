package no.hvl.erikbjorvik.nu3app.Misc;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import no.hvl.erikbjorvik.nu3app.Models.Consumable;
import no.hvl.erikbjorvik.nu3app.Models.PredefinedMeal;
import no.hvl.erikbjorvik.nu3app.R;

/**
 * Created by erikbjorvik on 14.07.2018.
 */

public class FoodListWithImagesArrayAdapter extends ArrayAdapter<Consumable> {

    private static final String TAG = "FoodListWithImagesAdapter";
    private Context context;
    private int resource;

    public FoodListWithImagesArrayAdapter(Context context, int resource, ArrayList<Consumable> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent,false);

        TextView name = (TextView) convertView.findViewById(R.id.tableText);
        ImageView image = (ImageView) convertView.findViewById(R.id.tableImage);
        name.setText(getItem(position).getName());

        if (getItem(position).getImagePath() != null) {
            Picasso.get().load(getItem(position).getImagePath()).into(image);
        }

        return convertView;
    }
}
