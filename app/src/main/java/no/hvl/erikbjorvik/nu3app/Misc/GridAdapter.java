package no.hvl.erikbjorvik.nu3app.Misc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import no.hvl.erikbjorvik.nu3app.MainActivity;
import no.hvl.erikbjorvik.nu3app.Models.Consumable;
import no.hvl.erikbjorvik.nu3app.R;

public class GridAdapter extends BaseAdapter{

    private ArrayList<Consumable> list;

    String [] result;
    Context context;
    int [] imageId;

    private static LayoutInflater inflater=null;
    public GridAdapter(MainActivity mainActivity, ArrayList<Consumable> list) {

        this.context = mainActivity;
        this.list = list;

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
        Button plusOne;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        View rowView = inflater.inflate(R.layout.grid_food, null);

        if (convertView == null) {
            // Inflate and initialize your layout
            convertView = inflater.inflate(R.layout.grid_food, parent, false);
            holder = new Holder();
            holder.gridText = (TextView) rowView.findViewById(R.id.gridText);
            holder.gridImage = (ImageView) rowView.findViewById(R.id.gridImage);
            holder.plusOne = (Button) rowView.findViewById(R.id.plusButton);
            holder.gridText.setText(list.get(position).getName());
            Picasso.get().load(list.get(position).getImagePath()).into((ImageView)rowView.findViewById(R.id.gridImage));


            convertView.setTag(holder);
        }
        else
            holder = (Holder) convertView.getTag();


        // Do things that change for every grid item here, like
        return convertView;
    }

}
