package lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.hisu.myapplication.R;

import lab6.fragment.ShoeFragment;

public class ShoeActivity extends AppCompatActivity {

    private FrameLayout mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoe);

        mFrameLayout = findViewById(R.id.shoe_layout_container);

        getSupportFragmentManager().beginTransaction()
                .replace(mFrameLayout.getId(), new ShoeFragment()).commit();
    }
}