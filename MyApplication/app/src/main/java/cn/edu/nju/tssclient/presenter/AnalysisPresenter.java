package cn.edu.nju.tssclient.presenter;


import cn.edu.nju.tssclient.data.StudentRepository;
import cn.edu.nju.tssclient.data.model.Analysis;
import cn.edu.nju.tssclient.view.contract.ResultListView;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by tjDu on 2017/6/14.
 */

public class AnalysisPresenter implements BasePresenter<ResultListView> {
    private StudentRepository repository;
    private ResultListView view;
    private Subscription subscription;

    public void getAnalysis(String username, String password, int userId, int assignmentId) {
        subscription = repository.getAnalysis(username, password, userId, assignmentId)
                .subscribeOn(Schedulers.io())
                .onErrorReturn(new Func1<Throwable, Analysis>() {
                    @Override
                    public Analysis call(Throwable throwable) {
                        throwable.printStackTrace();
                        view.showError();
                        return null;
                    }
                })
                .subscribe(new Action1<Analysis>() {
                    @Override
                    public void call(Analysis groups) {
                        if (groups != null) {
                            view.showList(groups.getQuestionResults());
                        }
                    }
                });
//        List<QuestionResult> list = new ArrayList<>();
//        QuestionResult qr = new QuestionResult();
//        qr.setQuestionTitle("题目1");
//        MetricData md = new MetricData();
//        md.setGitUrl("xxxx.git");
//        md.setCommentLineCount(15);
//        md.setTotalLineCount(20);
//        md.setFieldCount(2);
//        md.setMethodCount(2);
//        md.setMaxCoc(2);
//        qr.setMetricData(md);
//        ScoreResult sr = new ScoreResult();
//        sr.setScore(100);
//        qr.setScoreResult(sr);
//        TestResult tr = new TestResult();
//        Testcase tc = new Testcase();
//        tc.setName("testcase1");
//        tc.setPassed(true);
//        List<Testcase> cases = new ArrayList<>();
//        cases.add(tc);
//        tr.setCompileSucceed(true);
//        tr.setTestcases(cases);
//        qr.setTestResult(tr);
//        list.add(qr);
//        view.showList(list);
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
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void onPause() {

    }
}
