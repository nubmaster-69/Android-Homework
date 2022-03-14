package lab5.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.hisu.myapplication.R;

import java.text.DecimalFormat;

import lab5.model.Donut;

public class DonutDetailActivity extends AppCompatActivity {

    private ImageView donutImg;
    private TextView donutName, donutDesc, donutPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_detail);

        Donut donut = (Donut) getIntent().getSerializableExtra("donut");

        initUI();

        donutImg.setImageResource(donut.getImageResource());
        donutName.setText(donut.getName());
        donutDesc.setText(donut.getDesc());
        donutPrice.setText("$" + new DecimalFormat("#0.00").format(donut.getPrice()));
    }

    private void initUI() {
        donutImg = findViewById(R.id.donut_detail_img);
        donutName = findViewById(R.id.txt_donut_detail_name);
        donutDesc = findViewById(R.id.txt_donut_detail_desc);
        donutPrice = findViewById(R.id.txt_donut_detail_price);
    }
}