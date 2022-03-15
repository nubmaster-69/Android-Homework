package lab4.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.hisu.myapplication.R;

import java.util.ArrayList;

import lab4.adapter.ListViewProductAdapter;
import lab4.model.Product;

public class ListViewActivity extends AppCompatActivity {

    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        img = findViewById(R.id.imageView4);

        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(R.mipmap.ca_nau_lau, "Cá nấu lẩu", "Shop Devang"));
        products.add(new Product(R.mipmap.ga_bo_toi, "1KG khô gà bơ tỏi", "Shop LTD Food"));
        products.add(new Product(R.mipmap.do_choi_dang_mo_hinh, "Đồ chơi mô hình", "Shop thế giới đồ chơi"));

        ListView list = findViewById(R.id.myListView);

        ListViewProductAdapter adapter = new ListViewProductAdapter(this, R.layout.custom_listview_item, products);

        list.setAdapter(adapter);

        img.setOnClickListener(view -> {
            products.add(new Product(R.mipmap.ca_nau_lau, "Cá nấu lẩu", "Shop Devang"));
            adapter.setProducts(products);
        });
    }
}