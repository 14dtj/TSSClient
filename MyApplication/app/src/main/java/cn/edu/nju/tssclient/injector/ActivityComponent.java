package cn.edu.nju.tssclient.injector;

import cn.edu.nju.tssclient.view.MainActivity;
import dagger.Component;

/**
 * Created by tjDu on 2017/6/1.
 */
@Component(modules = MainModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);
}
