package lab8;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.hisu.myapplication.R;

import lab8.my_listener.IOnEmailAuthenListener;

public class SignInFragment extends Fragment {
    private EditText edtEmail, edtPwd;
    private ImageButton btnLogin;
    private IOnEmailAuthenListener onEmailLoginListener;

    public SignInFragment(IOnEmailAuthenListener onEmailLoginListener) {
        this.onEmailLoginListener = onEmailLoginListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View loginView = inflater.inflate(R.layout.fragment_sign_in, container, false);

        edtEmail = loginView.findViewById(R.id.fb_edt_email);
        edtPwd = loginView.findViewById(R.id.fb_edt_password);
        btnLogin = loginView.findViewById(R.id.fb_btn_login_fm);

        btnLogin.setOnClickListener(view -> {
            String email = edtEmail.getText().toString();
            String pwd = edtPwd.getText().toString();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pwd)) {
                showAlert("Oh no! Look like Email or Password is empty!");
                return;
            }

            onEmailLoginListener.loginOrSignUpWithEmailAndPassword(email, pwd);
        });


        return loginView;
    }

    private void showAlert(String msg) {
        new AlertDialog.Builder(getContext())
                .setMessage(msg)
                .setPositiveButton("Ok", null).show();
    }
}