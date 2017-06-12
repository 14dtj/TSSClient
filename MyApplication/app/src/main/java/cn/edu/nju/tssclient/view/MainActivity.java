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
    public static final String TAG = "MainActivity";
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.password)
    TextView password;
    @BindView(R.id.button)
    Button button;
    @Inject
    MainPresenter presenter;

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
                presenter.login(username.getText().toString(), password.getText().toString());
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void showError() {
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, R.string.login_error, Toast.LENGTH_LONG).show();
            }
        };
        runOnUiThread(myRunnable);
    }

    @Override
    public void changeView(User user, String password) {
        Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        intent.putExtras(bundle);
        intent.putExtra("password", password);
        startActivity(intent);
    }

}
