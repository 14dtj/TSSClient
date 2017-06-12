package cn.edu.nju.tssclient.view.contract;

import java.util.List;

import cn.edu.nju.tssclient.data.model.Exam;

/**
 * Created by tjDu on 2017/6/12.
 */

public interface ExamListView {
    void showError();

    void showList(List<Exam> list);
}
