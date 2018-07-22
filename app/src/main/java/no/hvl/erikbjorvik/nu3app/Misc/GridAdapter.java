package no.hvl.erikbjorvik.nu3app.Misc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.grid_food, null);
        holder.gridText = (TextView) rowView.findViewById(R.id.gridText);
        holder.gridImage = (ImageView) rowView.findViewById(R.id.gridImage);

        holder.gridText.setText(list.get(position).getName());

        if (list.get(position).getImagePath() != null) {
            Picasso.get().load(list.get(position).getImagePath()).into(holder.gridImage);

        }
        //holder.os_img.setImageResource(imageId[position]);

        rowView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_SHORT).show();
            }
        });

        return rowView;
    }

}