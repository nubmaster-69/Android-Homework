package lab4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hisu.myapplication.R;

import java.util.ArrayList;

import lab4.model.Product;

public class ListViewProductAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Product> products;
    private int idLayout;

    public ListViewProductAdapter(Context context) {
        this.context = context;
    }

    public ListViewProductAdapter(Context context, int idLayout, ArrayList<Product> products) {
        this.context = context;
        this.idLayout = idLayout;
        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return products.size();
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

        TextView txtName = view.findViewById(R.id.txtName);
        TextView txtShop = view.findViewById(R.id.txtShop);
        ImageView img = view.findViewById(R.id.productImg);

        img.setImageResource(products.get(i).getImgUrl());
        txtName.setText(products.get(i).getName());
        txtShop.setText(products.get(i).getShop());

        return view;
    }
}