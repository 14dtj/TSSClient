package cn.edu.nju.tssclient.presenter;

import cn.edu.nju.tssclient.data.StudentRepository;
import cn.edu.nju.tssclient.data.model.Analysis;
import cn.edu.nju.tssclient.data.model.Readme;
import cn.edu.nju.tssclient.view.contract.ContentView;
import cn.edu.nju.tssclient.view.util.ReadmeParams;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by tjDu on 2017/6/21.
 */

public class ReadmePresenter implements BasePresenter<ContentView> {
    private StudentRepository repository;
    private ContentView view;
    private Subscription subscription;

    public ReadmePresenter(StudentRepository repository, ContentView view) {
        this.repository = repository;
        this.view = view;
    }

    public void getReadme(ReadmeParams params) {
        subscription = repository.getReadme(params)
                .subscribeOn(Schedulers.io())
                .onErrorReturn(new Func1<Throwable, Readme>() {
                    @Override
                    public Readme call(Throwable throwable) {
                        throwable.printStackTrace();
                        view.showError();
                        return null;
                    }
                })
                .subscribe(new Action1<Readme>() {
                    @Override
                    public void call(Readme groups) {
                        if (groups != null) {
                            view.showContent(groups.getContent());
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
