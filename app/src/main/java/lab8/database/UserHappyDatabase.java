package lab8.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lab8.dao.UserHappyDAO;
import lab8.entity.UserHappy;

@Database(entities = {UserHappy.class}, version = 1, exportSchema = false)
public abstract class UserHappyDatabase extends RoomDatabase {
    private static final String DB_NAME = "UserHappy_DB";
    public static ExecutorService service = Executors.newFixedThreadPool(4);

    private static UserHappyDatabase instance;

    public abstract UserHappyDAO userHappyDAO();

    public static UserHappyDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, UserHappyDatabase.class, DB_NAME)
                    .allowMainThreadQueries().build();
        }
        return instance;
    }
}