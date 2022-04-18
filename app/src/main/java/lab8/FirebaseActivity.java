package lab8;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.hisu.myapplication.R;

public class FirebaseActivity extends AppCompatActivity {

    public static final String MODE = "mode";

    private ImageButton btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        btnLogin = findViewById(R.id.fb_btn_login);
        btnRegister = findViewById(R.id.fb_btn_register);

        Intent intent = new Intent(FirebaseActivity.this, LoginSignUpActivity.class);

        btnLogin.setOnClickListener(view -> {
            intent.putExtra(MODE, 1);
            startActivity(intent);
        });

        btnRegister.setOnClickListener(view -> {
            intent.putExtra(MODE, 2);
            startActivity(intent);
        });
    }
}