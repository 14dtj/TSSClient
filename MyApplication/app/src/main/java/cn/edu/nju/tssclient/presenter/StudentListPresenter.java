package cn.edu.nju.tssclient.presenter;

import java.util.List;

import cn.edu.nju.tssclient.data.TeacherRepository;
import cn.edu.nju.tssclient.data.model.Group;
import cn.edu.nju.tssclient.data.model.User;
import cn.edu.nju.tssclient.view.contract.BasicListView;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by tjDu on 2017/6/7.
 */

public class StudentListPresenter implements BasePresenter<BasicListView> {
    private Subscription subscription;
    private BasicListView view;
    private TeacherRepository repository;

    public StudentListPresenter(BasicListView view, TeacherRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void getGroups(String username, String password) {
        subscription = repository.getGroups(username, password)
                .subscribeOn(Schedulers.io())
                .onErrorReturn(new Func1<Throwable, List<Group>>() {
                    @Override
                    public List<Group> call(Throwable throwable) {
                        throwable.printStackTrace();
                        view.showError();
                        return null;
                    }
                })
                .subscribe(new Action1<List<Group>>() {
                    @Override
                    public void call(List<Group> groups) {
                        if (groups != null) {
                            view.showGroup(groups);
                        }
                    }
                });
    }

    public void getStudents(String username, String password, int groupId) {
        subscription = repository.getStudents(username, password, groupId)
                .subscribeOn(Schedulers.io())
                .onErrorReturn(new Func1<Throwable, List<User>>() {
                    @Override
                    public List<User> call(Throwable throwable) {
                        throwable.printStackTrace();
                        view.showError();
                        return null;
                    }
                })
                .subscribe(new Action1<List<User>>() {
                    @Override
                    public void call(List<User> groups) {
                        if (groups != null) {
                            view.addStudents(groups);
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
