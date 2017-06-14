package cn.edu.nju.tssclient.view.contract;

import lecho.lib.hellocharts.model.LineChartData;

/**
 * Created by tjDu on 2017/6/14.
 */

public interface MyLineChartView {
    void showError();

    void showLineChart(LineChartData data);
}
