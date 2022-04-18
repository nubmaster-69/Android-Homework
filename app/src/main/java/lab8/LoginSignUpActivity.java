package lab8;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.hisu.myapplication.R;

import lab8.my_listener.IOnEmailAuthenListener;

public class LoginSignUpActivity extends AppCompatActivity {

    private FrameLayout container;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign_up);

        container = findViewById(R.id.login_signup_container);
        firebaseAuth = FirebaseAuth.getInstance();

        int mode = getIntent().getIntExtra(FirebaseActivity.MODE, 1);

        if (mode == 1)
            setFragment(new SignInFragment(new IOnEmailAuthenListener() {
                @Override
                public void loginOrSignUpWithEmailAndPassword(String email, String password) {
                    firebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginSignUpActivity.this, task -> {
                                if (task.isSuccessful())
                                    setFragment(new FaceFragment());
                                else
                                    showAlert("Incorrect email or password!");
                            });
                }
            }));
        else
            setFragment(new RegisterFragment((email, password) -> {
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                        LoginSignUpActivity.this, task -> {
                            if (task.isSuccessful())
                                showAlert("Registered! Now you can login!");
                            else
                                showAlert("Something went wrong... Please try again!");
                        });
            }));
    }

    private void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(container.getId(), fragment).commit();
    }

    private void showAlert(String msg) {
        new AlertDialog.Builder(this)
                .setMessage(msg)
                .setPositiveButton("Ok", null).show();
    }
}