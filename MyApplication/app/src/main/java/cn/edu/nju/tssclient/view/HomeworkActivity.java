package cn.edu.nju.tssclient.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.nju.tssclient.R;
import cn.edu.nju.tssclient.data.model.Exam;
import cn.edu.nju.tssclient.data.model.Question;
import cn.edu.nju.tssclient.view.fragment.LineChartFragment;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;

/**
 * Created by tjDu on 2017/6/12.
 */

public class HomeworkActivity extends AppCompatActivity {
    private Exam exam;
    private List<Map<String, Object>> questionMap;
    private List<Question> questionList;

    @BindView(R.id.question_list)
    ListView listView;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.homework_id)
    TextView homeId;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.start)
    TextView start;
    @BindView(R.id.end)
    TextView end;
    @BindView(R.id.status)
    TextView status;

    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");
        exam = (Exam) intent.getSerializableExtra("exam");
        questionList = exam.getQuestions();
        setContentView(R.layout.homework_layout);
        ButterKnife.bind(this);
        title.setText(exam.getTitle());
        homeId.setText("Id:" + exam.getId());
        description.setText("描述:" + exam.getDescription());
        start.setText("开始日期：" + exam.getStartAt());
        end.setText("结束日期：" + exam.getEndAt());
        status.setText("状态：" + exam.getStatus());
        showList();
        setLineChart();
    }

    private void showList() {
        questionMap = new ArrayList<>();
        List<Question> list = exam.getQuestions();
        for (Question question : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("title", "名称：" + question.getTitle());
            map.put("content", "描述：" + question.getDescription());
            questionMap.add(map);
        }
        String[] from = {"title", "content"};
        int[] to = {R.id.title, R.id.content};
        SimpleAdapter adapter = new SimpleAdapter(this, questionMap, R.layout.short_student_info, from, to);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), QuestionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("question", questionList.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void setLineChart() {
        FragmentManager fm = this.getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        LineChartFragment fragment = new LineChartFragment();
        fragment.setUserInfo(username, password);
        transaction.replace(R.id.fragment_chart, fragment);
        transaction.commit();
    }
}
