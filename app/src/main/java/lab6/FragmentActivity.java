package lab6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.os.Bundle;

import com.hisu.myapplication.R;

import lab6.fragment.AboveFragment;
import lab6.fragment.MiddleFragment;

public class FragmentActivity extends AppCompatActivity {

    private FragmentContainerView mFragmentAbove, mFragmentMiddle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        initUI();

        addFragment(mFragmentAbove.getId(), new AboveFragment());
        addFragment(mFragmentMiddle.getId(), new MiddleFragment());
    }

    private void addFragment(int fragmentID, Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(fragmentID, fragment).commit();
    }

    private void initUI() {
        mFragmentAbove = findViewById(R.id.fragment_above);
        mFragmentMiddle = findViewById(R.id.fragment_middle);
    }
}