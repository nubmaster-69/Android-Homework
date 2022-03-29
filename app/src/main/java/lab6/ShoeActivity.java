package lab6;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.hisu.myapplication.R;

import lab6.fragment.ShoeDetailFragment;
import lab6.fragment.ShoeFragment;
import lab6.model.Shoe;

public class ShoeActivity extends AppCompatActivity {

    private FrameLayout mFrameLayout, mShoeDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoe);

        mFrameLayout = findViewById(R.id.shoe_layout_container);
        mShoeDetail = findViewById(R.id.shoe_detail_layout_container);

        getSupportFragmentManager().beginTransaction()
                .replace(mFrameLayout.getId(),  new ShoeFragment()).commit();
    }

    public void setShoeDetailFragment(Shoe shoe) {
        getSupportFragmentManager().beginTransaction()
                .replace(mShoeDetail.getId(), ShoeDetailFragment.ShoeDetailFragment(shoe)).commit();
    }
}