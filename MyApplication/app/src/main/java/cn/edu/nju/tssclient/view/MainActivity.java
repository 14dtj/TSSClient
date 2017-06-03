package cn.edu.nju.tssclient.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.nju.tssclient.R;
import cn.edu.nju.tssclient.data.model.User;
import cn.edu.nju.tssclient.injector.DaggerActivityComponent;
import cn.edu.nju.tssclient.injector.MainModule;
import cn.edu.nju.tssclient.presenter.MainPresenter;
import cn.edu.nju.tssclient.view.contract.BasicView;

public class MainActivity extends AppCompatActivity implements BasicView {
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.password)
    TextView password;
    @BindView(R.id.button)
    Button button;
    @Inject
    MainPresenter presenter;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DaggerActivityComponent.builder()
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = presenter.login(username.getText().toString(), password.getText().toString());
               // changeView();
            }
        });
    }

    @Override
    public void showError() {
        Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
    }


    private void changeView() {
        Intent intent = new Intent(this, UserInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
