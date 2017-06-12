package cn.edu.nju.tssclient.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.nju.tssclient.R;
import cn.edu.nju.tssclient.data.model.Exam;
import cn.edu.nju.tssclient.data.model.Question;

/**
 * Created by tjDu on 2017/6/12.
 */

public class HomeworkActivity extends AppCompatActivity {
    private Exam exam;
    List<Map<String, Object>> questionMap;
    @BindView(R.id.question_list)
    ListView listView;
    @BindView(R.id.title)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        exam = (Exam) intent.getSerializableExtra("exam");
        setContentView(R.layout.homework_layout);
        ButterKnife.bind(this);
        title.setText(exam.getTitle());
        showList();
    }

    private void showList() {
        questionMap = new ArrayList<>();
        List<Question> list = exam.getQuestions();
        for (Question question : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("title", question.getTitle());
            map.put("content", question.getDescription());
            questionMap.add(map);
        }
        String[] from = {"title", "content"};
        int[] to = {R.id.title, R.id.content};
        SimpleAdapter adapter = new SimpleAdapter(this, questionMap, R.layout.short_student_info, from, to);
        listView.setAdapter(adapter);
    }
}
