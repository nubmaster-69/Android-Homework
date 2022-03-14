package lab5.adapter;

import android.content.Context;
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

import java.text.DecimalFormat;
import java.util.List;

import lab5.model.Donut;

public class RecycleViewDonutAdapter extends RecyclerView.Adapter<RecycleViewDonutAdapter.ViewHolder> {

    private List<Donut> donuts;
    private Context context;

    public RecycleViewDonutAdapter(Context context) {
        this.context = context;
    }

    public RecycleViewDonutAdapter(List<Donut> donuts, Context context) {
        this.donuts = donuts;
        this.context = context;
    }

    public List<Donut> getDonuts() {
        return donuts;
    }

    public void setDonuts(List<Donut> donuts) {
        this.donuts = donuts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custome_recycle_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewDonutAdapter.ViewHolder holder, int position) {
        Donut donut = donuts.get(position);

        holder.donutImg.setImageResource(donut.getImageResource());
        holder.txtDonutName.setText(donut.getName());
        holder.txtDonutDesc.setText(donut.getDesc());
        holder.txtDonutPrice.setText("$" + new DecimalFormat("#0.00").format(donut.getPrice()));

        holder.btnAdd.setOnClickListener(view -> {
            Toast.makeText(context, donut.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return !donuts.isEmpty() ? donuts.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView parent;
        private ImageView donutImg, btnAdd;
        private TextView txtDonutName, txtDonutDesc, txtDonutPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.card_parent);

            donutImg = itemView.findViewById(R.id.donut_img);
            btnAdd = itemView.findViewById(R.id.donut_btn_add);

            txtDonutName = itemView.findViewById(R.id.donut_name);
            txtDonutDesc = itemView.findViewById(R.id.donut_desc);
            txtDonutPrice = itemView.findViewById(R.id.donut_price);
        }
    }

}