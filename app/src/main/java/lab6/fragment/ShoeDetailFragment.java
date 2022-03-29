package lab6.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hisu.myapplication.R;

import java.text.DecimalFormat;

import lab6.model.Shoe;

public class ShoeDetailFragment extends Fragment {
    public static final String SHOE_DATA_KEY = "shoe";

    private ImageView shoeImg;
    private TextView txtName, txtDesc, txtPrice;

    public static ShoeDetailFragment ShoeDetailFragment(Shoe shoe) {

        ShoeDetailFragment shoeDetailFragment = new ShoeDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SHOE_DATA_KEY, shoe);

        shoeDetailFragment.setArguments(bundle);

        return shoeDetailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shoe_detail, container, false);

        Shoe shoe = (Shoe) getArguments().getSerializable(SHOE_DATA_KEY);

        shoeImg = view.findViewById(R.id.shoe_detail_img);
        txtName = view.findViewById(R.id.shoe_detail_name);
        txtDesc = view.findViewById(R.id.shoe_detail_desc);
        txtPrice = view.findViewById(R.id.shoe_detail_price);

        shoeImg.setImageResource(shoe.getImageResource());
        txtName.setText(shoe.getName());
        txtDesc.setText(shoe.getDesc());
        txtPrice.setText("$" + new DecimalFormat("#.##").format(shoe.getPrice()));

        return view;
    }
}
