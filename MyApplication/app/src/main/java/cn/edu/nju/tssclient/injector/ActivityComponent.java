package cn.edu.nju.tssclient.injector;

import cn.edu.nju.tssclient.view.MainActivity;
import cn.edu.nju.tssclient.view.fragment.ExamListFragment;
import cn.edu.nju.tssclient.view.fragment.ExerciseListFragment;
import cn.edu.nju.tssclient.view.fragment.HomeworkListFragment;
import cn.edu.nju.tssclient.view.fragment.StudentsListFragment;
import dagger.Component;

/**
 * Created by tjDu on 2017/6/1.
 */
@Component(modules = MainModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);

    void inject(StudentsListFragment fragment);

    void inject(ExamListFragment fragment);

    void inject(ExerciseListFragment fragment);

    void inject(HomeworkListFragment fragment);
}
