package lab_7_room_with_view.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import lab_7_room_with_view.dao.PlaceDAO;
import lab_7_room_with_view.database.PlaceDatabase;
import lab_7_room_with_view.entity.Place;

public class PlaceRepository {
    private LiveData<List<Place>> liveData;
    private PlaceDAO placeDAO;

    public PlaceRepository(Application application) {
        placeDAO = PlaceDatabase.getInstance(application).placeDAO();
        liveData = placeDAO.getAllPlaces();
    }

    public LiveData<List<Place>> getLiveData() {
        return liveData;
    }

    public void insert(Place... places) {
        PlaceDatabase.service.execute(() -> {
            placeDAO.insert(places);
        });
    }

    public void delete(Place... places) {
        PlaceDatabase.service.execute(() -> {
            placeDAO.delete(places);
        });
    }

    public void update(Place... places) {
        PlaceDatabase.service.execute(() -> {
            placeDAO.update(places);
        });
    }
}