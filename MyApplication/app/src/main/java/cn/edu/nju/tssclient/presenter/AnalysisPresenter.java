package cn.edu.nju.tssclient.presenter;


import java.util.ArrayList;
import java.util.List;

import cn.edu.nju.tssclient.data.StudentRepository;
import cn.edu.nju.tssclient.data.model.MetricData;
import cn.edu.nju.tssclient.data.model.QuestionResult;
import cn.edu.nju.tssclient.data.model.ScoreResult;
import cn.edu.nju.tssclient.data.model.TestResult;
import cn.edu.nju.tssclient.data.model.Testcase;
import cn.edu.nju.tssclient.view.contract.ResultListView;

/**
 * Created by tjDu on 2017/6/14.
 */

public class AnalysisPresenter implements BasePresenter<ResultListView> {
    private StudentRepository repository;
    private ResultListView view;

    public void getAnalysis(String username, String password, int userId, int assignmentId) {
        List<QuestionResult> list = new ArrayList<>();
        QuestionResult qr = new QuestionResult();
        qr.setQuestionTitle("题目1");
        MetricData md = new MetricData();
        md.setGitUrl("xxxx.git");
        md.setCommentLineCount(15);
        md.setTotalLineCount(20);
        md.setFieldCount(2);
        md.setMethodCount(2);
        md.setMaxCoc(2);
        qr.setMetricData(md);
        ScoreResult sr = new ScoreResult();
        sr.setScore(100);
        qr.setScoreResult(sr);
        TestResult tr = new TestResult();
        Testcase tc = new Testcase();
        tc.setName("testcase1");
        tc.setPassed(true);
        List<Testcase> cases = new ArrayList<>();
        cases.add(tc);
        tr.setCompileSucceed(true);
        tr.setTestcases(cases);
        qr.setTestResult(tr);
        list.add(qr);
        view.showList(list);
    }

    public AnalysisPresenter(StudentRepository repository, ResultListView view) {
        this.repository = repository;
        this.view = view;
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
