package cn.edu.nju.tssclient.presenter;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nju.tssclient.data.TeacherRepository;
import cn.edu.nju.tssclient.data.model.QuestionScore;
import cn.edu.nju.tssclient.data.model.Score;
import cn.edu.nju.tssclient.data.model.Student;
import cn.edu.nju.tssclient.view.contract.MyLineChartView;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
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
    private int[] scoreRange = {0, 60, 70, 80, 90, 100};
    private int[] colors = {Color.RED, Color.BLUE, Color.CYAN, Color.GREEN, Color.GRAY, Color.YELLOW};

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
                        List<Line> lines = new ArrayList<>();
                        List<QuestionScore> questions = score.getQuestions();
                        int color = 0;
                        for (QuestionScore questionScore : questions) {
                            List<PointValue> values = new ArrayList<>();
                            int[] data = new int[5];
                            data[0] = data[1] = data[2] = data[3] = data[4] = 0;
                            for (Student student : questionScore.getStudents()) {
                                double value = student.getScore();
                                for (int i = 0; i < scoreRange.length - 1; i++) {
                                    if (value > scoreRange[i] && value <= scoreRange[i + 1]) {
                                        data[i]++;
                                    }
                                }
                            }
                            for (int i = 0; i < scoreRange.length - 1; i++)
                                values.add(new PointValue(i, data[i]));
                            Line line = new Line(values).setColor(colors[++color]).setCubic(true);
                            lines.add(line);

                        }
                        LineChartData data = new LineChartData();
                        data.setLines(lines);
                        Axis axisX = new Axis();
                        Axis axisY = new Axis().setHasLines(true);
                        axisX.setName("分数");
                        axisY.setName("人数");
                        List<AxisValue> axisValues = new ArrayList<>();
                        for (int i = 0; i < scoreRange.length - 1; i++) {
                            String text = scoreRange[i] + "~" + scoreRange[i + 1];
                            axisValues.add(new AxisValue(i, text.toCharArray()));
                        }
                        axisX.setValues(axisValues);
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
