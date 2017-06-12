package cn.edu.nju.tssclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.nju.tssclient.R;
import cn.edu.nju.tssclient.data.model.User;
import cn.edu.nju.tssclient.view.util.DownloadImageTask;

/**
 * Created by tjDu on 2017/6/7.
 */

public class UserInfoFragment extends BaseFragment {
    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.exam_title)
    TextView name;
    @BindView(R.id.gender)
    TextView gender;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.gitId)
    TextView gitId;
    @BindView(R.id.number)
    TextView number;

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_info, container, false);
        ButterKnife.bind(this, view);
        new DownloadImageTask(avatar).execute(user.getAvatar());
        username.setText("用户名：" + user.getUsername());
        name.setText("姓名：" + user.getName());
        gender.setText("性别：" + user.getGender());
        email.setText("邮箱：" + user.getEmail());
        if (user.getType().equals("student")) {
            gitId.setText("github帐号：" + user.getGitId());
            number.setText("学号：" + user.getNumber());
        }
        return view;
    }
}
