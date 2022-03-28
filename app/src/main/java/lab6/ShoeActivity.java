package lab6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

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

        ShoeFragment shoeFragment = new ShoeFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(mFrameLayout.getId(), shoeFragment).commit();

        int Orientation = getResources().getConfiguration().orientation;
        if (Orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Shoe shoe = (Shoe) getIntent().getSerializableExtra(ShoeDetailFragment.SHOE_DATA_KEY);
            if (shoe != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(mShoeDetail.getId(), ShoeDetailFragment.ShoeDetailFragment(shoe)).commit();
            }
        }
    }
}