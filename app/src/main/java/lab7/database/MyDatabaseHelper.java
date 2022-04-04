package lab7.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import lab7.model.User;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "MyUser";
    private static final String TABLE_NAME = "User";
    private static final String COL_ID = "id";
    private static final String COL_FULL_NAME = "fullName";

    public static final String CREATE_QUERY = String.format("create table %s (" +
            "%s Integer primary key," +
            "%s text)", TABLE_NAME, COL_ID, COL_FULL_NAME);

    public static final String DROP_QUERY = String.format("Drop table if exists %s", TABLE_NAME);

    public MyDatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_QUERY);
        onCreate(db);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME, new String[]{COL_ID, COL_FULL_NAME},
                null, null, null, null, null
        );

        while (cursor.moveToNext()) {
            User user = new User(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_FULL_NAME))
            );
            users.add(user);
        }

        return users;
    }

    public boolean insert(User user) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = convertToContentValues(user);

        long inserted = db.insert(TABLE_NAME, null, values);

        return inserted != -1;
    }

    public boolean removeUserByID(int id) {
        SQLiteDatabase db = getWritableDatabase();

        int deletedRows = db.delete(TABLE_NAME
                , COL_ID + " = ?",
                new String[]{String.valueOf(id)});

        return deletedRows > 0;
    }

    public boolean updateUser(User user) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = convertToContentValues(user);

        int updatedRows = db.update(TABLE_NAME,
                values, COL_ID + " = ?",
                new String[]{String.valueOf(user.getId())});

        return updatedRows > 0;
    }

    private ContentValues convertToContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(COL_ID, user.getId());
        values.put(COL_FULL_NAME, user.getFullName());
        return values;
    }
}