package cn.edu.nju.tssclient.presenter;

import java.util.List;

import cn.edu.nju.tssclient.data.TeacherRepository;
import cn.edu.nju.tssclient.data.model.Exam;
import cn.edu.nju.tssclient.view.contract.ExamListView;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by tjDu on 2017/6/12.
 */

public class ExamListPresenter implements BasePresenter<ExamListView> {
    private Subscription subscription;
    private TeacherRepository repository;
    private ExamListView view;

    public ExamListPresenter(ExamListView view, TeacherRepository repository) {
        this.repository = repository;
        this.view = view;
    }

    public void getExams(String username, String password, int courseId) {
        subscription = repository.getExams(username, password, courseId)
                .subscribeOn(Schedulers.io())
                .onErrorReturn(new Func1<Throwable, List<Exam>>() {
                    @Override
                    public List<Exam> call(Throwable throwable) {
                        throwable.printStackTrace();
                        view.showError();
                        return null;
                    }
                })
                .subscribe(new Action1<List<Exam>>() {
                    @Override
                    public void call(List<Exam> groups) {
                        if (groups != null) {
                            view.showList(groups);
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
