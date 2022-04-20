package lab8.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.hisu.myapplication.R;

import lab8.entity.UserHappy;
import lab8.my_listener.IOnFirebaseStoreChange;

public class RegisterFragment extends Fragment {

    private IOnFirebaseStoreChange onFirebaseStoreChange;
    private ImageButton btnRegister;
    private EditText edtUsername, edtEmail, edtPwd, edtConfirmPwd;

    public RegisterFragment(IOnFirebaseStoreChange onFirebaseStoreChange) {
        this.onFirebaseStoreChange = onFirebaseStoreChange;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View registerView = inflater.inflate(R.layout.fragment_register, container, false);

        edtUsername = registerView.findViewById(R.id.edt_fb_user_name);
        edtEmail = registerView.findViewById(R.id.edt_fb_user_email);
        edtPwd = registerView.findViewById(R.id.edt_fb_user_pwd);
        edtConfirmPwd = registerView.findViewById(R.id.edt_fb_user_confirm_pwd);
        btnRegister = registerView.findViewById(R.id.btn_fm_regis);


        btnRegister.setOnClickListener(view -> {

            String user = edtUsername.getText().toString();
            String email = edtEmail.getText().toString();
            String pwd = edtPwd.getText().toString();
            String confirmPwd = edtConfirmPwd.getText().toString();

            if (TextUtils.isEmpty(user) || TextUtils.isEmpty(email) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(confirmPwd)) {
                showAlert("Please fill out all field!");
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                edtEmail.setError("Invalid email!");
                edtEmail.requestFocus();
                return;
            }

            if (pwd.length() < 6) {
                edtPwd.setError("Password must have more than 5 characters!");
                edtPwd.requestFocus();
                return;
            }

            if (!TextUtils.equals(pwd, confirmPwd)) {
                edtConfirmPwd.setError("Password not match!");
                edtConfirmPwd.requestFocus();
                return;
            }

            UserHappy userHappy = new UserHappy(user, email, pwd);
            onFirebaseStoreChange.writeToFirestore(userHappy);
        });


        return registerView;
    }

    private void showAlert(String msg) {
        new AlertDialog.Builder(getContext())
                .setMessage(msg)
                .setPositiveButton("Ok", null).show();
    }
}