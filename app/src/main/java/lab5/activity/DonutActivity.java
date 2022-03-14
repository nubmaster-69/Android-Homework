package lab5.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hisu.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import lab5.adapter.RecyclerViewDonutAdapter;
import lab5.model.Donut;

import static android.graphics.Color.parseColor;

public class DonutActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;

    private List<Donut> donuts;
    private List<Donut> searchDonuts;

    private EditText edtSearch;

    private Button btnDonut, btnDonutPink, btnDonutFloating, prevFocusBtn;
    private Button[] donutBtns;

    private final String UN_FOCUS_BTN_COLOR = "#D3D3D3";
    private final String FOCUS_BTN_COLOR = "#F1B000";

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);

        initUI();
        donutBtns = new Button[]{btnDonut, btnDonutPink, btnDonutFloating};
        searchDonuts = new ArrayList<>();

        RecyclerViewDonutAdapter adapter = new RecyclerViewDonutAdapter(this);

        donuts = new ArrayList<>();
        donuts.add(new Donut(R.drawable.donut_yellow, "Tasty Donut", "Spicy tasty donut family", 10));
        donuts.add(new Donut(R.drawable.tasty_donut, "Pink Donut", "Spicy tasty donut family", 20));
        donuts.add(new Donut(R.drawable.green_donut, "Floating Donut", "Spicy tasty donut family", 30));

        adapter.setDonuts(donuts);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        /**
         * Bắt sự kiện người dùng nhập vào EditText
         * + Nếu trong mảng có tồn tại phần tử có tên giống hoặc chứa ký tự giống với người dùng
         *  vừa nhập --> thêm donut đó vào mảng mới và set lại adapter
         * + Nếu không tồn tại phần tử nào có tên hoặc ký tự giống với người dùng vừa nhập
         *  thì sẽ gán lại dữ liệu của mảng lúc ban đầu
         */
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String donutName = edtSearch.getText().toString();

                searchDonuts.clear();
                donuts.forEach(donut -> {
                    String curDonutName = donut.getName().toLowerCase();
                    if (curDonutName.equals(donutName) || curDonutName.contains(donutName.toLowerCase()))
                        searchDonuts.add(donut);
                });

                if (!searchDonuts.isEmpty())
                    adapter.setDonuts(searchDonuts);
                else
                    adapter.setDonuts(donuts);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        prevFocusBtn = donutBtns[0];
        for (int i = 0; i < donutBtns.length; i++)
            donutBtns[i].setOnClickListener(this);
    }

    private void initUI() {
        mRecyclerView = findViewById(R.id.my_recycle_view);

        btnDonut = findViewById(R.id.btnDonut);
        btnDonutPink = findViewById(R.id.btnDonutPink);
        btnDonutFloating = findViewById(R.id.btnFloating);

        edtSearch = findViewById(R.id.edtSearch);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDonut:
                changeButtonState(prevFocusBtn, donutBtns[0]);
                break;
            case R.id.btnDonutPink:
                changeButtonState(prevFocusBtn, donutBtns[1]);
                break;
            case R.id.btnFloating:
                changeButtonState(prevFocusBtn, donutBtns[2]);
                break;
        }
    }

    private void changeButtonState(Button prevFocusBtn, Button donutBtn) {
        prevFocusBtn.setBackgroundColor(parseColor(UN_FOCUS_BTN_COLOR));
        donutBtn.setBackgroundColor(parseColor(FOCUS_BTN_COLOR));
        this.prevFocusBtn = donutBtn;
    }
}