package cn.edu.nju.tssclient.presenter;

import java.util.concurrent.ExecutionException;

import cn.edu.nju.tssclient.data.UserRepository;
import cn.edu.nju.tssclient.data.model.User;
import cn.edu.nju.tssclient.view.contract.BasicView;

/**
 * Created by tjDu on 2017/5/31.
 */

public class MainPresenter implements BasePresenter<BasicView> {
    public static final String TAG = "MainPresenter";
    private UserRepository repository;
    private BasicView loginView;

    public MainPresenter(BasicView view, UserRepository repository) {
        this.loginView = view;
        this.repository = repository;
    }

    public User login(String username, String password) {
        User user = null;
        try {
            user = repository.execute(username, password).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (user == null) {
            loginView.showError();
        }
        return user;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onPause() {

    }
}
