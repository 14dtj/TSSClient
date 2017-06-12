package cn.edu.nju.tssclient.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.nju.tssclient.R;
import cn.edu.nju.tssclient.data.model.User;
import cn.edu.nju.tssclient.view.fragment.BaseFragment;
import cn.edu.nju.tssclient.view.fragment.ExamListFragment;
import cn.edu.nju.tssclient.view.fragment.ExerciseListFragment;
import cn.edu.nju.tssclient.view.fragment.HomeworkListFragment;
import cn.edu.nju.tssclient.view.fragment.StudentsListFragment;
import cn.edu.nju.tssclient.view.fragment.UserInfoFragment;

/**
 * Created by tjDu on 2017/6/3.
 */

public class UserInfoActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    private User user;
    private String password;
    private BaseFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        user = (User) intent.getSerializableExtra("user");
        password = intent.getStringExtra("password");
        setContentView(R.layout.main_layout);
        ButterKnife.bind(this);
        initNavigation();
        bottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        onTabSelected(0);
    }

    private void initNavigation() {
        switch (user.getType()) {
            case "student":
                break;
            case "teacher":
                bottomNavigationBar
                        .addItem(new BottomNavigationItem(R.drawable.user, "个人"))
                        .addItem(new BottomNavigationItem(R.drawable.exam, "考试"))
                        .addItem(new BottomNavigationItem(R.drawable.student, "学生"))
                        .addItem(new BottomNavigationItem(R.drawable.homework, "作业"))
                        .addItem(new BottomNavigationItem(R.drawable.exercise, "练习"))
                        .initialise();
                break;
            case "admin":
                break;
        }
    }

    @Override
    public void onTabSelected(int i) {
        FragmentManager fm = this.getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (i) {
            case 0:
                UserInfoFragment frag = new UserInfoFragment();
                frag.setUser(user);
                fragment = frag;
                break;
            case 1:
                fragment = new ExamListFragment();
                break;
            case 2:
                fragment = new StudentsListFragment();
                break;
            case 3:
                fragment = new HomeworkListFragment();
                break;
            case 4:
                fragment = new ExerciseListFragment();
                break;
        }
        fragment.setUserInfo(user.getUsername(), password);
        transaction.replace(R.id.fragment_content, fragment);
        transaction.commit();
    }


    @Override
    public void onTabUnselected(int i) {
    }

    @Override
    public void onTabReselected(int i) {
    }
}
