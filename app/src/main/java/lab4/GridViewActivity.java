package lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;

import com.hisu.myapplication.R;

import java.util.ArrayList;

public class GridViewActivity extends AppCompatActivity {

    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        GridView gridView = findViewById(R.id.myGridView);

        img = findViewById(R.id.imageView2);

        //int imgUrl, String name, double rate, int rateAmount, double price, double discount
        ArrayList<Component> comps = new ArrayList<>();
        comps.add(new Component(R.mipmap.book, "Product name 1 Product name 1", 4.5, 15, "69.200", 35));
        comps.add(new Component(R.mipmap.do_choi_dang_mo_hinh, "Product name 2", 5, 15, "69.200", 35));
        comps.add(new Component(R.mipmap.phone_blue, "Product name 3", 1, 15, "69.200", 35));

        GridViewCompAdapter adapter = new GridViewCompAdapter(this, R.layout.custom_gridview_item, comps);
        gridView.setAdapter(adapter);

        img.setOnClickListener(view -> {
            comps.add(new Component(R.mipmap.phone_blue, "Product name", 4, 15, "69.200", 35));
            gridView.setAdapter(adapter);
        });
    }
}