package cn.edu.nju.tssclient.injector;

import javax.inject.Singleton;

import cn.edu.nju.tssclient.data.UserRepository;
import cn.edu.nju.tssclient.presenter.MainPresenter;
import cn.edu.nju.tssclient.view.MainActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Created by tjDu on 2017/6/1.
 */
@Module
public class MainModule {
    private MainActivity activity;

    public MainModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    public MainActivity provideActivity() {
        return activity;
    }

    @Provides
    public UserRepository provideRepository() {
        return new UserRepository();
    }

    @Provides
    public MainPresenter provideMainPresenter(MainActivity activity, UserRepository repository) {
        return new MainPresenter(activity, repository);
    }
}
