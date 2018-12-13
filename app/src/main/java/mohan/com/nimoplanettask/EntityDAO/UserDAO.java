package mohan.com.nimoplanettask.EntityDAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import mohan.com.nimoplanettask.Entity.User;

@Dao
public interface UserDAO {

    @Query("SELECT * from user_table")
    LiveData<List<User>> getUsers();

    @Insert
    void insert(User user);

    @Query("DELETE FROM user_table")
    void deleteAll();
}
