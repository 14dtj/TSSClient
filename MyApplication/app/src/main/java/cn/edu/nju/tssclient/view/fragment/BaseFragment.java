package cn.edu.nju.tssclient.view.fragment;

import android.app.Fragment;

/**
 * Created by tjDu on 2017/6/7.
 */

public class BaseFragment extends Fragment {
    protected String username;
    protected String password;

    public void setUserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
