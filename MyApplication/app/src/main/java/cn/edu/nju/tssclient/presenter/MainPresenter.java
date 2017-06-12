package cn.edu.nju.tssclient.presenter;

import cn.edu.nju.tssclient.data.UserRepository;
import cn.edu.nju.tssclient.data.model.User;
import cn.edu.nju.tssclient.view.contract.BasicView;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by tjDu on 2017/5/31.
 */

public class MainPresenter implements BasePresenter<BasicView> {
    public static final String TAG = "MainPresenter";
    private UserRepository repository;
    private BasicView loginView;
    private Subscription subscription;

    public MainPresenter(BasicView view, UserRepository repository) {
        this.loginView = view;
        this.repository = repository;
    }

    public void login(String username, final String password) {
        subscription = repository.getUserInfo(username, password)
                .subscribeOn(Schedulers.io())
                .onErrorReturn(new Func1<Throwable, User>() {
                    @Override
                    public User call(Throwable throwable) {
                        throwable.printStackTrace();
                        loginView.showError();
                        return null;
                    }
                })
                .subscribe(new Action1<User>() {
                    @Override
                    public void call(User user) {
                        if (user != null) {
                            loginView.changeView(user, password);
                        }
                    }
                });
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void onPause() {

    }
}
