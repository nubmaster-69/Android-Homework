package lab4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hisu.myapplication.R;

import java.util.ArrayList;

import lab4.model.Component;

public class GridViewCompAdapter extends BaseAdapter {

    private Context context;
    private int idLayout;
    private ArrayList<Component> components;

    public GridViewCompAdapter(Context context, int idLayout, ArrayList<Component> components) {
        this.context = context;
        this.idLayout = idLayout;
        this.components = components;
    }

    @Override
    public int getCount() {
        return components.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(idLayout, viewGroup, false);
        }

        ImageView img = view.findViewById(R.id.compImg);
        TextView name = view.findViewById(R.id.txtCompName);
        TextView revs = view.findViewById(R.id.txtRevs);
        RatingBar rate = view.findViewById(R.id.compRate);
        TextView price = view.findViewById(R.id.compPrice);
        TextView dis = view.findViewById(R.id.compDiscount);

        img.setImageResource(components.get(i).getImgUrl());
        name.setText(components.get(i).getName());
        rate.setRating((float)components.get(i).getRate());
        revs.setText(String.format("(%d)", components.get(i).getRateAmount()));
        price.setText(String.format("%s đ", components.get(i).getPrice()));
        dis.setText("-" + components.get(i).getDiscount() + "%");

        return view;
    }
}
