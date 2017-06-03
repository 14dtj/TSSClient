package cn.edu.nju.tssclient.data;

import cn.edu.nju.tssclient.data.model.SimpleUser;
import cn.edu.nju.tssclient.data.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by tjDu on 2017/5/31.
 */

public interface ApiInterface {
    @POST("user/auth")
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Call<User> getUserInfo(@Body SimpleUser user);
}
