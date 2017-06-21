package cn.edu.nju.tssclient.view.fragment;

import android.app.Fragment;
import android.view.View;
import android.widget.Toast;

import cn.edu.nju.tssclient.R;
import cn.edu.nju.tssclient.view.UserInfoActivity;

/**
 * Created by tjDu on 2017/6/7.
 */

public class BaseFragment extends Fragment {
    protected String username;
    protected String password;
    protected String type;
    protected int userId;
    protected UserInfoActivity activity;
    protected View view;
    protected int courseId = 2;

    public void setUserInfo(String username, String password, String type, int userId) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.userId = userId;
    }

    public void baseError() {
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, R.string.network_error, Toast.LENGTH_LONG).show();
            }
        };
        activity.runOnUiThread(myRunnable);
    }
}
