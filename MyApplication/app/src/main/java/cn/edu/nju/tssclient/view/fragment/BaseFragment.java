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
    protected UserInfoActivity activity;
    protected View view;

    public void setUserInfo(String username, String password) {
        this.username = username;
        this.password = password;
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
