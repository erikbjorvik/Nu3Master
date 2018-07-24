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

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

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
        final ViewHolder holder;
        if(convertView == null) {
            // Inflate and initialize your layout
            convertView = inflater.inflate(R.layout.grid_food, parent, false);
            holder = new ViewHolder();
            holder.consumableImage = (ImageView) convertView.findViewById(R.id.gridImage);
            holder.plusOne = (Button) convertView.findViewById(R.id.plusOne);
            holder.consumableText = (TextView) convertView.findViewById(R.id.gridText);
            holder.portionCounter = (TextView) convertView.findViewById(R.id.portionCount);

            holder.plusOne.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.portionCounter.setText("fis");
                    MainActivity.meals.get(position).setName("fisen");
                    MainActivity.gridAdapter.notifyDataSetChanged();
                }
            });
            // etc, etc...
            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();

        // Do things that change for every grid item here, like
        Picasso.get().load(list.get(position).getImagePath()).into(holder.consumableImage);
        holder.consumableText.setText(list.get(position).getName());
        return convertView;
    }


    class ViewHolder {
        TextView consumableText;
        TextView portionCounter;
        ImageView consumableImage;
        Button plusOne;
        Button minusOne;
    }

}