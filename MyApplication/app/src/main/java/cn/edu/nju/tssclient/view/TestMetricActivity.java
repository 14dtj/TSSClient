package cn.edu.nju.tssclient.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.nju.tssclient.R;
import cn.edu.nju.tssclient.data.model.MetricData;
import cn.edu.nju.tssclient.data.model.QuestionResult;
import cn.edu.nju.tssclient.data.model.ScoreResult;
import cn.edu.nju.tssclient.data.model.TestResult;
import cn.edu.nju.tssclient.data.model.Testcase;

/**
 * Created by tjDu on 2017/6/14.
 */

public class TestMetricActivity extends AppCompatActivity {
    @BindView(R.id.question_title)
    TextView title;
    @BindView(R.id.score)
    TextView score;
    @BindView(R.id.git_url)
    TextView gitUrl;
    @BindView(R.id.compile_succeeded)
    TextView compile;
    @BindView(R.id.total_line_count)
    TextView totalCount;
    @BindView(R.id.comment_line_count)
    TextView commentCount;
    @BindView(R.id.field_count)
    TextView fieldCount;
    @BindView(R.id.method_count)
    TextView methodCount;
    @BindView(R.id.max_coc)
    TextView maxCoc;
    @BindView(R.id.test_cases)
    ListView listView;


    private MetricData metricData;
    private ScoreResult scoreResult;
    private TestResult testResult;
    private QuestionResult questionResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        questionResult = (QuestionResult) intent.getSerializableExtra("questionResult");
        metricData = questionResult.getMetricData();
        scoreResult = questionResult.getScoreResult();
        testResult = questionResult.getTestResult();
        setContentView(R.layout.test_metric);
        ButterKnife.bind(this);
        setInfo();
        showList();
    }

    private void setInfo() {
        title.setText(questionResult.getQuestionTitle());
        score.setText(scoreResult.getScore() + "");
        gitUrl.setText(metricData.getGitUrl());
        compile.setText(testResult.isCompileSucceed() + "");
        totalCount.setText(metricData.getTotalLineCount()+"");
        commentCount.setText(metricData.getCommentLineCount()+"");
        fieldCount.setText(metricData.getFieldCount()+"");
        methodCount.setText(metricData.getMethodCount()+"");
        maxCoc.setText(metricData.getMaxCoc()+"");
    }

    private void showList() {
        List<Map<String, Object>> testMap = new ArrayList<>();
        List<Testcase> testcaseList = testResult.getTestcases();
        for (Testcase item : testcaseList) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", "名称：" + item.getName());
            map.put("passed", "是否通过：" + item.isPassed());
            testMap.add(map);
        }
        String[] from = {"name", "passed"};
        int[] to = {R.id.title, R.id.content};
        SimpleAdapter adapter = new SimpleAdapter(this, testMap, R.layout.short_student_info, from, to);
        listView.setAdapter(adapter);
    }
}
