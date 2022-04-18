package lab7_room_with_view.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hisu.myapplication.R;

import java.util.List;

import lab7_room_with_view.entity.Place;
import lab7_room_with_view.my_interface.IOnItemClickListener;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {

    private List<Place> placeList;
    private IOnItemClickListener itemClickListener;

    public PlaceAdapter(IOnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setPlaceList(List<Place> placeList) {
        this.placeList = placeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View placeView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_user, parent, false);
        return new PlaceViewHolder(placeView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceAdapter.PlaceViewHolder holder, int position) {
        Place place = placeList.get(position);

        holder.setPlaceData(place);
        holder.btnEdit.setOnClickListener(view -> itemClickListener.updatePlace(place));
        holder.btnDel.setOnClickListener(view -> itemClickListener.removePlace(place));
    }

    @Override
    public int getItemCount() {
        if (placeList == null) return 0;
        return placeList.size();
    }

    /* This viewHolder use the same viewHolder as lab7.userViewHolder
     but with a different name :v */
    public static class PlaceViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtPlace;
        private final ImageButton btnEdit, btnDel;

        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPlace = itemView.findViewById(R.id.txt_user_info);
            btnEdit = itemView.findViewById(R.id.btn_sql_edit);
            btnDel = itemView.findViewById(R.id.btn_sql_del);
        }

        @SuppressLint("SetTextI18n")
        private void setPlaceData(Place place) {
            txtPlace.setText(place.getId() + ". " + place.getName());
        }
    }
}