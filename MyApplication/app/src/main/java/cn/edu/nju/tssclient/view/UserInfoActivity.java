package cn.edu.nju.tssclient.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.nju.tssclient.R;
import cn.edu.nju.tssclient.data.model.User;

/**
 * Created by tjDu on 2017/6/3.
 */

public class UserInfoActivity extends AppCompatActivity {
    private User user;

    @BindView(R.id.email)
    TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        user = (User) intent.getSerializableExtra("user");
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        email.setText(user.getEmail());
    }
}
