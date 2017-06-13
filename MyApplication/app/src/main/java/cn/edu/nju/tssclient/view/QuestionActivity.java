package cn.edu.nju.tssclient.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.nju.tssclient.R;
import cn.edu.nju.tssclient.data.model.Question;

/**
 * Created by tjDu on 2017/6/13.
 */

public class QuestionActivity extends AppCompatActivity {
    private Question question;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.question_id)
    TextView questionId;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.difficulty)
    TextView difficulty;
    @BindView(R.id.git_url)
    TextView gitUrl;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.creator)
    TextView creator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        question = (Question) intent.getSerializableExtra("question");
        setContentView(R.layout.question_layout);
        ButterKnife.bind(this);
        title.setText(question.getTitle());
        questionId.setText("Id:" + question.getId());
        description.setText("描述:" + question.getDescription());
        difficulty.setText("难度：" + question.getDifficulty());
        gitUrl.setText("git url：" + question.getGitUrl());
        type.setText("类型：" + question.getType());
        creator.setText("创建者：" + question.getCreator().getName());
    }
}
