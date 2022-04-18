package lab7_room_with_view;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hisu.myapplication.R;

import lab7_room_with_view.adapter.PlaceAdapter;
import lab7_room_with_view.database.PlaceDatabase;
import lab7_room_with_view.entity.Place;
import lab7_room_with_view.my_interface.IOnItemClickListener;
import lab7_room_with_view.view_model.PlaceViewModel;

public class RoomWithViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btnSave, btnCancel;
    private EditText edtInput;
    private PlaceAdapter placeAdapter;
    private PlaceViewModel placeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        initUI();
    }

    private void initUI() {
        recyclerView = findViewById(R.id.user_sql_recycler_view);
        btnSave = findViewById(R.id.btn_sql_save);
        btnCancel = findViewById(R.id.btn_sql_cancel);
        edtInput = findViewById(R.id.edt_add);

        placeAdapter = new PlaceAdapter(new IOnItemClickListener() {
            @Override
            public void updatePlace(Place place) {
                place.setName(place.getName() + " Updated!");
                placeViewModel.updatePlace(place);
            }

            @Override
            public void removePlace(Place place) {
                new AlertDialog.Builder(RoomWithViewActivity.this)
                        .setMessage("Are you sure you want to remove this place?!")
                        .setPositiveButton("Yes, do it!",
                                (dialogInterface, i) -> placeViewModel.removePlace(place))
                        .setNegativeButton("Nah, mah mistake!", null).show();
            }
        });

        recyclerView.setAdapter(placeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        placeViewModel = new ViewModelProvider(this).get(PlaceViewModel.class);
        placeViewModel.getLiveData().observe(this, places -> {
            placeAdapter.setPlaceList(places);
        });

        btnSave.setOnClickListener(view -> btnSaveAction());
        btnCancel.setOnClickListener(view -> btnCancelAction());
    }

    private void btnCancelAction() {
        edtInput.setText("");
        edtInput.requestFocus();
    }

    private void btnSaveAction() {
        String placeName = edtInput.getText().toString();
        if (TextUtils.isEmpty(placeName)) {
            showAlert("Opps! Look like the place you wanna save is empty! :<");
            return;
        }

        placeViewModel.addPlace(new Place(placeName));
        showAlert("Yay! Your place has been saved! (^_^)!");
        btnCancelAction();
    }

    private void showAlert(String alertMsg) {
        new AlertDialog.Builder(this).setMessage(alertMsg)
                .setPositiveButton("Ok", (dialogInterface, i) -> edtInput.requestFocus()).show();
    }

    @Override
    protected void onDestroy() {
        PlaceDatabase.closeDatabase();
        super.onDestroy();
    }
}