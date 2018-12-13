package mohan.com.nimoplanettask.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import mohan.com.nimoplanettask.Entity.User;
import mohan.com.nimoplanettask.Repository.UserRepository;

public class UserViewModel extends AndroidViewModel {

    private UserRepository mRepository;
    private LiveData<List<User>> listLiveData;

    public UserViewModel(@NonNull Application application) {
        super(application);

        mRepository = new UserRepository(application);
        listLiveData = mRepository.getAllUsers();
    }

    public LiveData<List<User>> getListLiveData() {
        return listLiveData;
    }

    void insert(User user) {
        mRepository.insert(user);
    }
}
