package lab_7_room_with_view.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import lab_7_room_with_view.entity.Place;

@Dao
public interface PlaceDAO {
    @Query("select * from places")
    LiveData<List<Place>> getAllPlaces();

    @Insert
    void insert(Place... places);

    @Update
    void update(Place... places);

    @Delete
    void delete(Place...places);

    @Query("delete from places")
    void deleteAll();
}