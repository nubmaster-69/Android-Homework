package lab6.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hisu.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import lab6.ShoeActivity;
import lab6.ShoeDetailActivity;
import lab6.fragment.ShoeDetailFragment;
import lab6.model.Shoe;

public class ShoeAdapter extends RecyclerView.Adapter<ShoeAdapter.ShoeViewHolder> {

    private List<Shoe> shoes;
    private List<CardView> cardViewList;
    private Context context;
    private ShoeActivity shoeActivity;

    public ShoeAdapter(List<Shoe> shoes, Context context) {
        this.shoes = shoes;
        this.context = context;
        shoeActivity = (ShoeActivity) context;
        cardViewList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ShoeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_shoe_item, parent, false);
        return new ShoeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoeAdapter.ShoeViewHolder holder, int position) {
        Shoe shoe = shoes.get(position);

        cardViewList.add(holder.parent);

        holder.shoeImg.setImageResource(shoe.getImageResource());
        holder.txtName.setText(shoe.getBrand() + "-discount " + shoe.getDiscount() + "%");

        holder.parent.setOnClickListener(view -> {
            Intent intent = new Intent(context, ShoeDetailActivity.class);
            intent.putExtra(ShoeDetailFragment.SHOE_DATA_KEY, shoe);
            context.startActivity(intent);
        });

        int Orientation = context.getResources().getConfiguration().orientation;
        if (Orientation == Configuration.ORIENTATION_LANDSCAPE) {
            holder.parent.setOnClickListener(v -> {
                for (CardView cardView : cardViewList)
                    cardView.setCardBackgroundColor(Color.parseColor("#D9CEBA"));

                shoeActivity.setShoeDetailFragment(shoe);
                holder.parent.setCardBackgroundColor(Color.parseColor("#FDC251"));
            });
        }
    }

    @Override
    public int getItemCount() {
        return shoes.size();
    }

    public class ShoeViewHolder extends RecyclerView.ViewHolder {

        private CardView parent;
        private ImageView shoeImg;
        private TextView txtName;

        public ShoeViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.shoe_parent);
            shoeImg = itemView.findViewById(R.id.shoe_img);
            txtName = itemView.findViewById(R.id.shoe_name);
        }
    }
}