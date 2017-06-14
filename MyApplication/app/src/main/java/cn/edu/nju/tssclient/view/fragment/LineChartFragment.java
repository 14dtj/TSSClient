package cn.edu.nju.tssclient.view.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.nju.tssclient.R;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by tjDu on 2017/6/13.
 */

public class LineChartFragment extends Fragment {
    @BindView(R.id.line_chart)
    LineChartView lineChart;
    protected String username;
    protected String password;

    public void setUserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.line_chart, container, false);
        ButterKnife.bind(this, view);
        drawChart();
        return view;
    }

    private void drawChart() {
        List<PointValue> values = new ArrayList<>();
        values.add(new PointValue(0, 2));
        values.add(new PointValue(1, 4));
        values.add(new PointValue(2, 3));
        values.add(new PointValue(3, 4));

        Line line = new Line(values).setColor(Color.BLUE).setCubic(true);
        List<Line> lines = new ArrayList<>();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);
        Axis axisX = new Axis();
        Axis axisY = new Axis().setHasLines(true);
        axisX.setName("分数");
        axisY.setName("人数");

        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);
        lineChart.setLineChartData(data);
        lineChart.setInteractive(true);
        lineChart.setContainerScrollEnabled(true, ContainerScrollType.VERTICAL);
    }
}
