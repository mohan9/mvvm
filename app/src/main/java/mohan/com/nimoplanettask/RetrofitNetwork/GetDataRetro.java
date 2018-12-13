package mohan.com.nimoplanettask.RetrofitNetwork;

import java.util.List;

import mohan.com.nimoplanettask.Entity.User;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataRetro {

    @GET("/shinilms/fake-json-server/posts")
    Call<List<User>> getAllUser();
}
