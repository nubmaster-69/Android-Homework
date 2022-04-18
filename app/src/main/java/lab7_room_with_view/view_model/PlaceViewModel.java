package lab7_room_with_view.view_model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import lab7_room_with_view.entity.Place;
import lab7_room_with_view.repository.PlaceRepository;

public class PlaceViewModel extends AndroidViewModel {

    private PlaceRepository mPlaceRepository;
    private LiveData<List<Place>> liveData;

    public PlaceViewModel(Application application) {
        super(application);
        mPlaceRepository = new PlaceRepository(application);
        liveData = mPlaceRepository.getLiveData();
    }

    public LiveData<List<Place>> getLiveData() {
        return liveData;
    }

    public void addPlace(Place... places) {
        mPlaceRepository.insert(places);
    }

    public void removePlace(Place... places) {
        mPlaceRepository.delete(places);
    }

    public void updatePlace(Place... places) {
        mPlaceRepository.update(places);
    }
}