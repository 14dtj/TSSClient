package cn.edu.nju.tssclient.view.contract;

import java.util.List;

import cn.edu.nju.tssclient.data.model.QuestionResult;

/**
 * Created by tjDu on 2017/6/14.
 */

public interface ResultListView {
    void showError();

    void showList(List<QuestionResult> list);
}
