package lab7.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import lab7.model.User;

@Dao
public interface UserDAO {
    @Query("select * from users")
    List<User> getAllUsers();

    @Insert
    long addUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUserByID(User user);
}