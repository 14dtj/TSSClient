package cn.edu.nju.tssclient.data;

/**
 * Created by tjDu on 2017/6/7.
 */

public abstract class BaseRepository {
    protected ApiInterface apiService;

    public BaseRepository() {
        apiService = ApiClient.getClient().create(ApiInterface.class);
    }
}
