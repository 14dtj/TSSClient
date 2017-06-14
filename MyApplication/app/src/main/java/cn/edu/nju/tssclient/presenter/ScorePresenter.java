package cn.edu.nju.tssclient.presenter;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nju.tssclient.data.TeacherRepository;
import cn.edu.nju.tssclient.data.model.Score;
import cn.edu.nju.tssclient.view.contract.MyLineChartView;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by tjDu on 2017/6/14.
 */

public class ScorePresenter implements BasePresenter<MyLineChartView> {
    private TeacherRepository repository;
    private MyLineChartView view;
    private Subscription subscription;

    public ScorePresenter(TeacherRepository repository, MyLineChartView view) {
        this.repository = repository;
        this.view = view;
    }

    public void getLineChartData(String username, String password, int assignmentId) {
        subscription = repository.getScore(username, password, assignmentId)
                .subscribeOn(Schedulers.io())
                .onErrorReturn(new Func1<Throwable, Score>() {
                    @Override
                    public Score call(Throwable throwable) {
                        throwable.printStackTrace();
                        view.showError();
                        return null;
                    }
                })
                .subscribe(new Action1<Score>() {
                    @Override
                    public void call(Score score) {

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
                        view.showLineChart(data);
                    }

                });
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void onPause() {

    }
}
