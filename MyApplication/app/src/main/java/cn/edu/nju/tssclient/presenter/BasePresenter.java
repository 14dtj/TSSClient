package cn.edu.nju.tssclient.presenter;

import cn.edu.nju.tssclient.view.contract.BasicView;

/**
 * Created by tjDu on 2017/5/31.
 */

public interface BasePresenter<T extends BasicView> {
    void onCreate();

    void onStart();

    void onStop();

    void onPause();
}