package cn.edu.nju.tssclient.view.contract;

import java.util.List;

import cn.edu.nju.tssclient.data.model.Group;
import cn.edu.nju.tssclient.data.model.User;

/**
 * Created by tjDu on 2017/6/7.
 */

public interface BasicListView {
    void showError();

    void addStudents(List<User> list);

    void showGroup(List<Group> list);
}
