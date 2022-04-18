package lab7_room_with_view.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lab7_room_with_view.dao.PlaceDAO;
import lab7_room_with_view.entity.Place;

@Database(entities = {Place.class}, version = 1, exportSchema = false)
public abstract class PlaceDatabase extends RoomDatabase {

    private static volatile PlaceDatabase instance;
    private static final String DATABASE_NAME = "Place_DB";
    private static final int NUM_OF_THREAD = 4;
    public static ExecutorService service
            = Executors.newFixedThreadPool(NUM_OF_THREAD);

    public abstract PlaceDAO placeDAO();

    public static PlaceDatabase getInstance(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PlaceDatabase.class, DATABASE_NAME).addCallback(callback).build();
        return instance;
    }

    public static void closeDatabase() {
        if (instance != null) instance.close();
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            service.execute(() -> {
                PlaceDAO placeDAO = instance.placeDAO();
                placeDAO.deleteAll();
                placeDAO.insert(new Place("Đà Lạt"), new Place("Hà Nội"));
            });
        }
    };
}