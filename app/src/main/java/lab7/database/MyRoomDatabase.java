package lab7.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import lab7.dao.UserDAO;
import lab7.entity.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class MyRoomDatabase extends RoomDatabase {
    private static final String DB_NAME = "Room_User_DB";
    private static MyRoomDatabase instance;

    public static MyRoomDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MyRoomDatabase.class, DB_NAME).allowMainThreadQueries().build();
        }
        return instance;
    }

    public static void closeDatabaseConnection() {
        if (instance != null)
            instance.close();
    }

    public abstract UserDAO userDao();
}