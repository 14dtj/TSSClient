package cn.edu.nju.tssclient.presenter;

/**
 * Created by tjDu on 2017/5/31.
 */

public interface BasePresenter<T> {
    void onCreate();

    void onStart();

    void onStop();

    void onPause();
}