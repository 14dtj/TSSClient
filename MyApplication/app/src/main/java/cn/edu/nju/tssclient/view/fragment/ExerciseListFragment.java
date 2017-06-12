package cn.edu.nju.tssclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.nju.tssclient.R;
import cn.edu.nju.tssclient.data.model.Exam;
import cn.edu.nju.tssclient.injector.DaggerActivityComponent;
import cn.edu.nju.tssclient.injector.MainModule;
import cn.edu.nju.tssclient.presenter.ExerciseListPresenter;
import cn.edu.nju.tssclient.view.UserInfoActivity;
import cn.edu.nju.tssclient.view.contract.ExamListView;

/**
 * Created by tjDu on 2017/6/7.
 * 练习列表
 */

public class ExerciseListFragment extends BaseFragment implements ExamListView {
    @BindView(R.id.exam_list)
    ListView listView;
    @Inject
    ExerciseListPresenter presenter;
    protected List<Map<String, Object>> examMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (UserInfoActivity) getActivity();
        examMap = new ArrayList<>();
        view = inflater.inflate(R.layout.exam_list, container, false);
        ButterKnife.bind(this, view);
        DaggerActivityComponent.builder()
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
        presenter.getExercise(username, password, 1);
        return view;
    }

    @Override
    public void showError() {
        baseError();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void showList(List<Exam> list) {
        for (Exam exam : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("title", exam.getTitle());
            map.put("description", exam.getDescription());
            map.put("start", "开始时间：" + exam.getStartAt());
            map.put("end", "结束时间：" + exam.getEndAt());
            map.put("status", "状态：" + exam.getStatus());
            examMap.add(map);
        }
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                String[] from = {"title", "description", "start", "end", "status"};
                int[] to = {R.id.exam_title, R.id.description, R.id.start, R.id.end, R.id.status};
                SimpleAdapter adapter = new SimpleAdapter(view.getContext(), examMap, R.layout.short_exam_info, from, to);
                listView.setAdapter(adapter);
            }
        };
        activity.runOnUiThread(myRunnable);
    }
}
