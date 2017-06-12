package cn.edu.nju.tssclient.view.contract;

import cn.edu.nju.tssclient.data.model.User;

/**
 * Created by tjDu on 2017/5/31.
 */

public interface BasicView {
    void showError();
    void changeView(User user,String password);
}
