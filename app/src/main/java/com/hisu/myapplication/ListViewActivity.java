package com.hisu.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(R.mipmap.ca_nau_lau, "Cá nấu lẩu", "Shop Devang"));
        products.add(new Product(R.mipmap.ga_bo_toi, "1KG khô gà bơ tỏi", "Shop LTD Food"));
        products.add(new Product(R.mipmap.do_choi_dang_mo_hinh, "Đồ chơi mô hình", "Shop thế giới đồ chơi"));

        products.add(new Product(R.mipmap.ca_nau_lau, "Cá nấu lẩu", "Shop Devang"));
        products.add(new Product(R.mipmap.ga_bo_toi, "1KG khô gà bơ tỏi", "Shop LTD Food"));
        products.add(new Product(R.mipmap.do_choi_dang_mo_hinh, "Đồ chơi mô hình", "Shop thế giới đồ chơi"));

        products.add(new Product(R.mipmap.ca_nau_lau, "Cá nấu lẩu", "Shop Devang"));
        products.add(new Product(R.mipmap.ga_bo_toi, "1KG khô gà bơ tỏi", "Shop LTD Food"));
        products.add(new Product(R.mipmap.do_choi_dang_mo_hinh, "Đồ chơi mô hình", "Shop thế giới đồ chơi"));

        ListView list = findViewById(R.id.myListView);

        ListViewProductAdapter adapter = new ListViewProductAdapter(this, R.layout.custom_listview_item, products);

        list.setAdapter(adapter);
    }
}