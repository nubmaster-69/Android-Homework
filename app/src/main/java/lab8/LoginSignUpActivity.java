package lab8;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hisu.myapplication.R;

import lab8.dao.UserHappyDAO;
import lab8.database.UserHappyDatabase;
import lab8.entity.UserHappy;
import lab8.fragment.FaceFragment;
import lab8.fragment.RegisterFragment;
import lab8.fragment.SignInFragment;

public class LoginSignUpActivity extends AppCompatActivity {

    private FrameLayout container;
    private FirebaseAuth firebaseAuth;
    private UserHappyDAO dao;
    private FirebaseFirestore db;
    private CollectionReference userCollection;

    public static final String FACE_KEY = "face";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign_up);

        container = findViewById(R.id.login_signup_container);
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        userCollection = db.collection("User_happy");

        dao = UserHappyDatabase.getInstance(getApplicationContext()).userHappyDAO();

        int mode = getIntent().getIntExtra(FirebaseActivity.MODE, 1);

        if (mode == 1)
            setFragment(new SignInFragment((email, password) ->
                    firebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginSignUpActivity.this, task -> {
                                if (task.isSuccessful()) {
                                    UserHappy userHappy = dao.getUserByEmail(email);
                                    setFragment(new FaceFragment(userHappy));
                                } else
                                    showAlert("Incorrect email or password!");
                            })));
        else
            setFragment(new RegisterFragment(user -> {
                firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                        .addOnCompleteListener(LoginSignUpActivity.this, task -> {
                            if (task.isSuccessful()) {
                                showAlert("Registered! Now you can login using this account!");
                                dao.insert(user);
                                addUserToFirestore(user);
                            } else
                                showAlert("Failed to register new account! Please try again!");
                        });
            }));
    }

    private void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(container.getId(), fragment).commit();
    }

    private void showAlert(String msg) {
        new AlertDialog.Builder(this)
                .setMessage(msg).setPositiveButton("Ok", null).show();
    }

    private void addUserToFirestore(UserHappy user) {
        userCollection.add(user).addOnSuccessListener(this, documentReference -> {
            showAlert("Added to fire-store successfully!");
        }).addOnFailureListener(this, e -> {
            showAlert("Something went wrong...");
            Log.e(LoginSignUpActivity.class.getName(), e.getMessage());
        });
    }
}