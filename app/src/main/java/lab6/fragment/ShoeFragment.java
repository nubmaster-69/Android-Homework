package lab6.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hisu.myapplication.R;

import java.util.List;

import lab6.ShoeActivity;
import lab6.adapter.ShoeAdapter;
import lab6.model.Shoe;

public class ShoeFragment extends Fragment {

    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shoe, container, false);

        mRecyclerView = view.findViewById(R.id.shoe_recycler_view);
        ShoeAdapter shoeAdapter = new ShoeAdapter(List.of(
                new Shoe(R.drawable.shoes_rm_preview_b, "Nike Shoes", "adidas Women's Stan Smith Low Top Fashion Sneakers Shoes", 100, 50, "Sole: Rubber\n" +
                        "Closure: Lace-Up\n" +
                        "Shoe Width: Medium\n" +
                        "Lace Up\n" +
                        "Grip Rubber Sole\n" +
                        "Padded Insole"),
                new Shoe(R.drawable.shoes_rm_preview_a, "Adidas Shoes", "adidas Women's Stan Smith Low Top Fashion Sneakers Shoes", 110, 80, "Sole: Rubber\n" +
                        "Closure: Lace-Up\n" +
                        "Shoe Width: Medium\n" +
                        "Lace Up\n" +
                        "Grip Rubber Sole\n" +
                        "Padded Insole"),
                new Shoe(R.drawable.shoes_rm_purple, "Nike Shoes", "adidas Women's Stan Smith Low Top Fashion Sneakers Shoes", 123, 30, "Sole: Rubber\n" +
                        "Closure: Lace-Up\n" +
                        "Shoe Width: Medium\n" +
                        "Lace Up\n" +
                        "Grip Rubber Sole\n" +
                        "Padded Insole"),
                new Shoe(R.drawable.shoes_rm_preview, "Yonex Shoes", "adidas Women's Stan Smith Low Top Fashion Sneakers Shoes", 241, 50, "Sole: Rubber\n" +
                        "Closure: Lace-Up\n" +
                        "Shoe Width: Medium\n" +
                        "Lace Up\n" +
                        "Grip Rubber Sole\n" +
                        "Padded Insole"),
                new Shoe(R.drawable.shoes_rm_yellow, "Vector Shoes", "adidas Women's Stan Smith Low Top Fashion Sneakers Shoes", 169, 50, "Sole: Rubber\n" +
                        "Closure: Lace-Up\n" +
                        "Shoe Width: Medium\n" +
                        "Lace Up\n" +
                        "Grip Rubber Sole\n" +
                        "Padded Insole"),
                new Shoe(R.drawable.shoes_white, "Lining Shoes", "adidas Women's Stan Smith Low Top Fashion Sneakers Shoes", 543, 90, "Sole: Rubber\n" +
                        "Closure: Lace-Up\n" +
                        "Shoe Width: Medium\n" +
                        "Lace Up\n" +
                        "Grip Rubber Sole\n" +
                        "Padded Insole")
        ), getContext());

        mRecyclerView.setAdapter(shoeAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}