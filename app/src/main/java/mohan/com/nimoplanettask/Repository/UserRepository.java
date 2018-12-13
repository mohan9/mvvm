package mohan.com.nimoplanettask.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import mohan.com.nimoplanettask.Entity.User;
import mohan.com.nimoplanettask.EntityDAO.UserDAO;
import mohan.com.nimoplanettask.RoomDatabase.UserRoomDatabase;

public class UserRepository {

    private UserDAO userDAO;
    private LiveData<List<User>> listLiveData;

    public UserRepository(Application application) {
        UserRoomDatabase db = UserRoomDatabase.getDatabase(application);
        userDAO = db.userDAO();
        listLiveData = userDAO.getUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return listLiveData;
    }

    public void insert(User user) {
        new insertAsyncTask(userDAO).execute(user);
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDAO mAsyncTaskDao;

        insertAsyncTask(UserDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
