package lab7_room_with_view.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "places")
public class Place {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    public Place() {
    }

    @Ignore
    public Place(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}