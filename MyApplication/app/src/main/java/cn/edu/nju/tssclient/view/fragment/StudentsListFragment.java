package cn.edu.nju.tssclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.nju.tssclient.R;
import cn.edu.nju.tssclient.data.model.Group;
import cn.edu.nju.tssclient.data.model.User;
import cn.edu.nju.tssclient.injector.DaggerActivityComponent;
import cn.edu.nju.tssclient.injector.MainModule;
import cn.edu.nju.tssclient.presenter.StudentListPresenter;
import cn.edu.nju.tssclient.view.UserInfoActivity;
import cn.edu.nju.tssclient.view.contract.BasicListView;

/**
 * Created by tjDu on 2017/6/7.
 * 学生列表
 */

public class StudentsListFragment extends BaseFragment implements BasicListView {
    @BindView(R.id.list)
    ListView listView;
    @Inject
    StudentListPresenter presenter;
    private View view;
    private UserInfoActivity activity;
    private List<Map<String, Object>> studentMap;
    private int groupNum;
    private int callCount;

    @Override
    public void showError() {
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, R.string.network_error, Toast.LENGTH_LONG).show();
            }
        };
        activity.runOnUiThread(myRunnable);
    }

    @Override
    public void addStudents(List<User> list) {
        callCount++;
        if (studentMap == null) {
            studentMap = new ArrayList<>();
        }
        if (callCount == groupNum) {
            showList();
        } else {
            for (User user : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("title", user.getName());
                map.put("content", user.getNumber());
                studentMap.add(map);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (UserInfoActivity) getActivity();
        view = inflater.inflate(R.layout.student_list, container, false);
        ButterKnife.bind(this, view);
        DaggerActivityComponent.builder()
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
        presenter.getGroups(username, password);
        return view;
    }

    @Override
    public void showGroup(List<Group> list) {
        groupNum = list.size();
        for (Group group : list) {
            presenter.getStudents(username, password, group.getId());
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    private void showList() {
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                String[] from = {"title", "content"};
                int[] to = {R.id.title, R.id.content};
                SimpleAdapter adapter = new SimpleAdapter(view.getContext(), studentMap, R.layout.short_student_info, from, to);
                listView.setAdapter(adapter);
            }
        };
        activity.runOnUiThread(myRunnable);
    }

}
