package cn.edu.nju.tssclient.data;

import cn.edu.nju.tssclient.data.model.SimpleUser;
import cn.edu.nju.tssclient.data.model.User;
import rx.Observable;

/**
 * Created by tjDu on 2017/5/31.
 */

public class UserRepository extends BaseRepository {
    private static UserRepository instance;

    public static UserRepository getInstance() {
        if (instance == null)
            instance = new UserRepository();
        return instance;
    }

    private UserRepository() {
        super();
    }

    public Observable<User> getUserInfo(String username, String password) {
        SimpleUser su = new SimpleUser();
        su.setPassword(password);
        su.setUsername(username);
        Observable<User> call = apiService.getUserInfo(su);
        return call;
    }

}
