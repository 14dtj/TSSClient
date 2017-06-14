package cn.edu.nju.tssclient.view.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.nju.tssclient.R;
import cn.edu.nju.tssclient.injector.DaggerActivityComponent;
import cn.edu.nju.tssclient.injector.MainModule;
import cn.edu.nju.tssclient.presenter.ScorePresenter;
import cn.edu.nju.tssclient.view.contract.MyLineChartView;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by tjDu on 2017/6/13.
 */

public class LineChartFragment extends Fragment implements MyLineChartView {
    @BindView(R.id.line_chart)
    LineChartView lineChart;
    protected String username;
    protected String password;
    private int assignmentId;

    @Inject
    ScorePresenter presenter;

    public void setUserInfo(String username, String password, int assignmentId) {
        this.username = username;
        this.password = password;
        this.assignmentId = assignmentId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.line_chart, container, false);
        ButterKnife.bind(this, view);
        DaggerActivityComponent.builder()
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
        presenter.getLineChartData(username, password, assignmentId);
        return view;
    }

    @Override
    public void showError() {

    }

    @Override
    public void showLineChart(LineChartData data) {
        lineChart.setLineChartData(data);
        lineChart.setInteractive(true);
        lineChart.setContainerScrollEnabled(true, ContainerScrollType.VERTICAL);
    }
}
