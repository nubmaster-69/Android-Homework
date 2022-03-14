package lab5.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hisu.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import lab5.adapter.RecycleViewDonutAdapter;
import lab5.model.Donut;

public class DonutActivity extends AppCompatActivity {

    private RecyclerView myRecyclerView;
    private FloatingActionButton btnAddDonut;
    private List<Donut> donuts;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);

        initUI();

        RecycleViewDonutAdapter adapter = new RecycleViewDonutAdapter(this);

//        int imageResource, String name, String desc, double price
        donuts = new ArrayList<>();
        donuts.add(new Donut(R.drawable.donut_yellow, "Tasty Donut", "Spicy tasty donut family", 10));
        donuts.add(new Donut(R.drawable.tasty_donut, "Pink Donut", "Spicy tasty donut family", 20));
        donuts.add(new Donut(R.drawable.green_donut, "Floating Donut", "Spicy tasty donut family", 30));

        adapter.setDonuts(donuts);
        myRecyclerView.setAdapter(adapter);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnAddDonut.setOnClickListener(view -> {
            donuts.add(new Donut(R.drawable.donut_red, "Floating Donut", "Spicy tasty donut family", 40));
            adapter.setDonuts(donuts);
        });
    }

    private void initUI() {
        myRecyclerView = findViewById(R.id.my_recycle_view);
        btnAddDonut = findViewById(R.id.btnAdddDonut);
    }
}