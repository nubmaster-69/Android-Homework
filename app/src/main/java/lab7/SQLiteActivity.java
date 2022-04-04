package lab7;

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

import lab7.adapter.UserAdapter;
import lab7.database.MyDatabaseHelper;
import lab7.model.User;

public class SQLiteActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btnSave, btnCancel;
    private EditText edtInput;
    private List<User> userList;
    private UserAdapter userAdapter;

    private MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        initUI();

        addActionForSaveButton();
        addActionForCancelButton();
    }

    private void initUI() {
        myDatabaseHelper = new MyDatabaseHelper(this);

        recyclerView = findViewById(R.id.user_sql_recycler_view);
        btnSave = findViewById(R.id.btn_sql_save);
        btnCancel = findViewById(R.id.btn_sql_cancel);
        edtInput = findViewById(R.id.edt_add);

        userList = new ArrayList<>();
        userList = getDataFromDatabase();

        userAdapter = new UserAdapter(userList, this);

        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<User> getDataFromDatabase() {
        return myDatabaseHelper.getAllUsers();
    }

    private void addActionForSaveButton() {
        btnSave.setOnClickListener(view -> {
            int userId = userList.isEmpty() ? 1 : userList.get(userList.size() - 1).getId() + 1;
            String userFullName = edtInput.getText().toString().isEmpty() ?
                    "Unknown 101" : edtInput.getText().toString();

            User user = new User(userId, userFullName);

            boolean inserted = myDatabaseHelper.insert(user);

            if (inserted) {
                Toast.makeText(SQLiteActivity.this, "User added successfully!", Toast.LENGTH_SHORT).show();
                userList.add(user);
                userAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(SQLiteActivity.this, "failed...", Toast.LENGTH_SHORT).show();
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
        myDatabaseHelper.close();
        super.onDestroy();
    }
}