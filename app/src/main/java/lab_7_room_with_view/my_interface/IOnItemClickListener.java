package lab_7_room_with_view.my_interface;

import lab_7_room_with_view.entity.Place;

public interface IOnItemClickListener {
    void updatePlace(Place place);
    void removePlace(Place place);
}