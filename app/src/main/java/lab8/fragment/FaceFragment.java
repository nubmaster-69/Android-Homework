package lab8.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.SetOptions;
import com.hisu.myapplication.R;

import java.util.HashMap;
import java.util.Map;

import lab8.LoginSignUpActivity;
import lab8.dao.UserHappyDAO;
import lab8.database.UserHappyDatabase;
import lab8.entity.UserHappy;

public class FaceFragment extends Fragment {

    private ImageView faceHappy, faceNormal, faceUnhappy;
    private ImageButton btnFinish;

    public FaceFragment(UserHappy userHappy) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(LoginSignUpActivity.FACE_KEY, userHappy);
        setArguments(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        UserHappyDAO dao = UserHappyDatabase.getInstance(getContext()).userHappyDAO();
        View faceView = inflater.inflate(R.layout.fragment_face, container, false);

        UserHappy userHappy = (UserHappy) getArguments()
                .getSerializable(LoginSignUpActivity.FACE_KEY);

        faceHappy = faceView.findViewById(R.id.face_happy);
        faceNormal = faceView.findViewById(R.id.face_normal);
        faceUnhappy = faceView.findViewById(R.id.face_unhappy);
        btnFinish = faceView.findViewById(R.id.btn_finish);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        faceHappy.setOnClickListener(view -> {
            userHappy.setHappy(userHappy.getHappy() + 1);
            UserHappyDatabase.service.execute(() -> {
                dao.update(userHappy);
            });
            showToast();
        });

        faceNormal.setOnClickListener(view -> {
            userHappy.setNormal(userHappy.getNormal() + 1);
            UserHappyDatabase.service.execute(() -> {
                dao.update(userHappy);
            });
            showToast();
        });

        faceUnhappy.setOnClickListener(view -> {
            userHappy.setUnhappy(userHappy.getUnhappy() + 1);
            UserHappyDatabase.service.execute(() -> {
                dao.update(userHappy);
            });
            showToast();
        });

        btnFinish.setOnClickListener(view -> {

            UserHappy updated = dao.getUserByEmail(userHappy.getEmail());

            new AlertDialog.Builder(getContext())
                    .setMessage("Email: " + updated.getEmail() +
                            "Happy: " + updated.getHappy() +
                            "\nNormal: " + updated.getNormal() +
                            "\nUnhappy: " + updated.getUnhappy())
                    .setPositiveButton("Ok", null).show();

            CollectionReference col = db.collection("User_happy");
            col.whereEqualTo("email", updated.getEmail()).get()
                    .addOnCompleteListener(getActivity(), task -> {
                        if (task.isSuccessful())
                            for (QueryDocumentSnapshot doc : task.getResult()) {
                                Map<Object, Object> updatedUser = new HashMap<>();
                                updatedUser.put("happy", (int) updated.getHappy());
                                updatedUser.put("unhappy", (int) updated.getUnhappy());
                                updatedUser.put("normal", (int) updated.getNormal());
                                col.document(doc.getId()).set(updatedUser, SetOptions.merge());
                            }
                    });
        });

        return faceView;
    }

    private void showToast() {
        Toast.makeText(getContext(), "Thank you for your feedback! :)", Toast.LENGTH_SHORT).show();
    }
}