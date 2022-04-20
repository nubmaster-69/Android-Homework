package lab8.my_listener;

import lab8.entity.UserHappy;

public interface IOnFirebaseStoreChange {
    void writeToFirestore(UserHappy userHappy);
}