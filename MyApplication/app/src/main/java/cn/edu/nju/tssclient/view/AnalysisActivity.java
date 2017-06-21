package cn.edu.nju.tssclient.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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
import cn.edu.nju.tssclient.data.model.QuestionResult;
import cn.edu.nju.tssclient.injector.DaggerActivityComponent;
import cn.edu.nju.tssclient.injector.MainModule;
import cn.edu.nju.tssclient.presenter.AnalysisPresenter;
import cn.edu.nju.tssclient.view.contract.ResultListView;
import cn.edu.nju.tssclient.view.util.ReadmeParams;

/**
 * Created by tjDu on 2017/6/14.
 */

public class AnalysisActivity extends AppCompatActivity implements ResultListView {
    private List<Map<String, Object>> resultMap;
    private List<QuestionResult> questionResults;
    private String username;
    private String password;
    private int assignmentId;
    private int userId;

    @Inject
    AnalysisPresenter presenter;
    @BindView(R.id.result_list)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");
        userId = intent.getIntExtra("userId", 0);
        assignmentId = intent.getIntExtra("assignmentId", 0);
        setContentView(R.layout.analysis_layout);
        ButterKnife.bind(this);
        DaggerActivityComponent.builder()
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
        presenter.getAnalysis(username, password, userId, assignmentId);
    }


    @Override
    public void showError() {
    }

    @Override
    public void showList(List<QuestionResult> list) {
        resultMap = new ArrayList<>();
        this.questionResults = list;
        for (QuestionResult item : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("title", "名称：" + item.getQuestionTitle());
            map.put("score", "得分：" + item.getScoreResult().getScore());
            resultMap.add(map);
        }
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                String[] from = {"title", "score"};
                int[] to = {R.id.title, R.id.content};
                SimpleAdapter adapter = new SimpleAdapter(AnalysisActivity.this, resultMap, R.layout.short_student_info, from, to);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(view.getContext(), TestMetricActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("questionResult", questionResults.get(position));
                        bundle.putSerializable("readmeParams", new ReadmeParams(username, password, assignmentId, userId));
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }
        };
        runOnUiThread(myRunnable);
    }
}
