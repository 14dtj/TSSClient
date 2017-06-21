package cn.edu.nju.tssclient.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
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
import cn.edu.nju.tssclient.presenter.HomeListPresenter;
import cn.edu.nju.tssclient.view.HomeworkActivity;
import cn.edu.nju.tssclient.view.UserInfoActivity;
import cn.edu.nju.tssclient.view.contract.ExamListView;

/**
 * Created by tjDu on 2017/6/7.
 * 作业列表
 */

public class HomeworkListFragment extends BaseFragment implements ExamListView {
    @BindView(R.id.exam_list)
    ListView listView;
    @BindView(R.id.search_view)
    SearchView searchView;
    @Inject
    HomeListPresenter presenter;
    protected List<Map<String, Object>> examMap;
    private List<Exam> exams;
    private SimpleAdapter adapter;

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
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                examMap.clear();
                for (Exam exam : exams) {
                    if (exam.getTitle().contains(newText)) {
                        putData(exam);
                    }
                }
                adapter.notifyDataSetChanged();
                return true;
            }
        });
        presenter.getHomework(username, password, courseId);
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
        this.exams = list;
        for (Exam exam : list) {
            putData(exam);
        }
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                String[] from = {"title", "description", "start", "end", "status"};
                int[] to = {R.id.exam_title, R.id.description, R.id.start, R.id.end, R.id.status};
                adapter = new SimpleAdapter(view.getContext(), examMap, R.layout.short_exam_info, from, to);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(view.getContext(), HomeworkActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("exam", exams.get(position));
                        intent.putExtras(bundle);
                        intent.putExtra("username", username);
                        intent.putExtra("password", password);
                        intent.putExtra("type", type);
                        intent.putExtra("userId", userId);
                        startActivity(intent);
                    }
                });
            }
        };
        activity.runOnUiThread(myRunnable);
    }

    private void putData(Exam exam) {
        Map<String, Object> map = new HashMap<>();
        map.put("title", exam.getTitle());
        map.put("description", exam.getDescription());
        map.put("start", "开始时间：" + exam.getStartAt());
        map.put("end", "结束时间：" + exam.getEndAt());
        map.put("status", "状态：" + exam.getStatus());
        examMap.add(map);
    }
}
