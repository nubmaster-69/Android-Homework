package lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.hisu.myapplication.R;

import lab6.fragment.ShoeDetailFragment;
import lab6.model.Shoe;

public class ShoeDetailActivity extends AppCompatActivity {

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoe_detail);

        frameLayout = findViewById(R.id.shoe_detail_container);

        Shoe shoe = (Shoe) getIntent().getSerializableExtra(ShoeDetailFragment.SHOE_DATA_KEY);

        getSupportFragmentManager().beginTransaction()
                .replace(frameLayout.getId(), ShoeDetailFragment.ShoeDetailFragment(shoe)).commit();
    }
}