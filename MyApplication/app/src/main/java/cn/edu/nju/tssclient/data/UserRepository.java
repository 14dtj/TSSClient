package cn.edu.nju.tssclient.data;

import android.os.AsyncTask;

import java.io.IOException;

import cn.edu.nju.tssclient.data.model.SimpleUser;
import cn.edu.nju.tssclient.data.model.User;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by tjDu on 2017/5/31.
 */

public class UserRepository extends AsyncTask<String, Integer, User> {
    private ApiInterface apiService;

    public UserRepository() {
        apiService = ApiClient.getClient().create(ApiInterface.class);
    }

    @Override
    protected User doInBackground(String... params) {
        return getUserInfo(params[0], params[1]);
    }

    private User getUserInfo(String username, String password) {
        SimpleUser su = new SimpleUser();
        su.setPassword(password);
        su.setUsername(username);
        Call<User> call = apiService.getUserInfo(su);
        User result = null;
        Response<User> response;
        try {
            response = call.execute();
            result = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
