package cn.edu.nju.tssclient.injector;

import cn.edu.nju.tssclient.data.TeacherRepository;
import cn.edu.nju.tssclient.data.UserRepository;
import cn.edu.nju.tssclient.presenter.MainPresenter;
import cn.edu.nju.tssclient.presenter.StudentListPresenter;
import cn.edu.nju.tssclient.view.MainActivity;
import cn.edu.nju.tssclient.view.fragment.StudentsListFragment;
import dagger.Module;
import dagger.Provides;

/**
 * Created by tjDu on 2017/6/1.
 */
@Module
public class MainModule {
    private MainActivity activity;
    private StudentsListFragment fragment;

    public MainModule(MainActivity activity) {
        this.activity = activity;
    }

    public MainModule(StudentsListFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    public MainActivity provideActivity() {
        return activity;
    }

    @Provides
    public UserRepository provideRepository() {
        return UserRepository.getInstance();
    }

    @Provides
    public MainPresenter provideMainPresenter(MainActivity activity, UserRepository repository) {
        return new MainPresenter(activity, repository);
    }

    @Provides
    public StudentsListFragment provideFragment() {
        return fragment;
    }

    @Provides
    public TeacherRepository provideTeacherRepository() {
        return TeacherRepository.getInstance();
    }

    @Provides
    public StudentListPresenter getStuListPresenter(StudentsListFragment fragment, TeacherRepository repository) {
        return new StudentListPresenter(fragment, repository);
    }
}
