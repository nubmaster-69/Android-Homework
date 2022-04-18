package lab7.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hisu.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import lab7.adapter.RoomUserAdapter;
import lab7.dao.UserDAO;
import lab7.database.MyRoomDatabase;
import lab7.entity.User;

public class RoomActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btnSave, btnCancel;
    private EditText edtInput;
    private List<User> userList;
    private RoomUserAdapter userAdapter;

    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        initUI();
        addActionForSaveButton();
        addActionForCancelButton();
    }

    private void initUI() {
        userDAO = MyRoomDatabase.getInstance(this).userDao();

        recyclerView = findViewById(R.id.user_sql_recycler_view);
        btnSave = findViewById(R.id.btn_sql_save);
        btnCancel = findViewById(R.id.btn_sql_cancel);
        edtInput = findViewById(R.id.edt_add);

        userList = new ArrayList<>();
        userList = userDAO.getAllUsers();

        userAdapter = new RoomUserAdapter(userList, this);

        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void addActionForSaveButton() {
        btnSave.setOnClickListener(view -> {
            String userFullName = edtInput.getText().toString().isEmpty() ?
                    "No name 101" : edtInput.getText().toString();

            User user = new User(userFullName);

            boolean inserted = userDAO.addUser(user) > 0;

            if (inserted) {
                Toast.makeText(
                        RoomActivity.this, "User added successfully!", Toast.LENGTH_SHORT).show();
                userList = userDAO.getAllUsers();
                userAdapter.setUserList(userList);
            } else {
                Toast.makeText(RoomActivity.this, "failed...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addActionForCancelButton() {
        btnCancel.setOnClickListener(view -> {
            edtInput.setText("");
            edtInput.clearFocus();
        });
    }

    @Override
    protected void onDestroy() {
        MyRoomDatabase.getInstance(this).closeDatabaseConnection();
        super.onDestroy();
    }
}