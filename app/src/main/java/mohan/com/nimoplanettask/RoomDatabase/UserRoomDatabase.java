package mohan.com.nimoplanettask.RoomDatabase;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.util.List;

import mohan.com.nimoplanettask.Entity.User;
import mohan.com.nimoplanettask.EntityDAO.UserDAO;
import mohan.com.nimoplanettask.MainActivity;
import mohan.com.nimoplanettask.RetrofitNetwork.GetDataRetro;
import mohan.com.nimoplanettask.RetrofitNetwork.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserRoomDatabase extends RoomDatabase {

    public abstract UserDAO userDAO();

    private static volatile UserRoomDatabase INSTANCE;

    public static UserRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UserRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UserRoomDatabase.class, "user_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            callApi(INSTANCE);
        }
    };

    private static void callApi(UserRoomDatabase INSTANCE) {
        final UserDAO userDAO = INSTANCE.userDAO();


        /*Create handle for the RetrofitInstance interface*/
        GetDataRetro service = RetrofitClientInstance.getRetrofitInstance().create(GetDataRetro.class);

        Call<List<User>> call = service.getAllUser();
        call.enqueue(new retrofit2.Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull final Response<List<User>> response) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        userDAO.deleteAll();

                        for (int i = 0; i < response.body().size(); i++) {
                            userDAO.insert(response.body().get(i));
                        }
                    }
                }).start();
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {

            }
        });
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

//    @NonNull
//    @Override
//    protected InvalidationTracker createInvalidationTracker() {
//        return null;
//    }
//
//    @Override
//    public void clearAllTables() {
//
//    }
}
