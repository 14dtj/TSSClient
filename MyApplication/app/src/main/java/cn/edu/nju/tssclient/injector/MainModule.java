package cn.edu.nju.tssclient.injector;

import cn.edu.nju.tssclient.data.StudentRepository;
import cn.edu.nju.tssclient.data.TeacherRepository;
import cn.edu.nju.tssclient.data.UserRepository;
import cn.edu.nju.tssclient.presenter.AnalysisPresenter;
import cn.edu.nju.tssclient.presenter.ExamListPresenter;
import cn.edu.nju.tssclient.presenter.ExerciseListPresenter;
import cn.edu.nju.tssclient.presenter.HomeListPresenter;
import cn.edu.nju.tssclient.presenter.MainPresenter;
import cn.edu.nju.tssclient.presenter.ReadmePresenter;
import cn.edu.nju.tssclient.presenter.ScorePresenter;
import cn.edu.nju.tssclient.presenter.StudentListPresenter;
import cn.edu.nju.tssclient.view.AnalysisActivity;
import cn.edu.nju.tssclient.view.MainActivity;
import cn.edu.nju.tssclient.view.TestMetricActivity;
import cn.edu.nju.tssclient.view.fragment.ExamListFragment;
import cn.edu.nju.tssclient.view.fragment.ExerciseListFragment;
import cn.edu.nju.tssclient.view.fragment.HomeworkListFragment;
import cn.edu.nju.tssclient.view.fragment.LineChartFragment;
import cn.edu.nju.tssclient.view.fragment.StudentsListFragment;
import dagger.Module;
import dagger.Provides;

/**
 * Created by tjDu on 2017/6/1.
 */
@Module
public class MainModule {
    private MainActivity activity;
    private StudentsListFragment fragment;
    private ExamListFragment examListFragment;
    private ExerciseListFragment exerciseListFragment;
    private HomeworkListFragment homeworkListFragment;
    private LineChartFragment lineChartFragment;
    private AnalysisActivity analysisActivity;
    private TestMetricActivity testMetricActivity;

    public MainModule(MainActivity activity) {
        this.activity = activity;
    }

    public MainModule(StudentsListFragment fragment) {
        this.fragment = fragment;
    }

    public MainModule(ExamListFragment fragment) {
        this.examListFragment = fragment;
    }

    public MainModule(ExerciseListFragment fragment) {
        this.exerciseListFragment = fragment;
    }

    public MainModule(HomeworkListFragment fragment) {
        this.homeworkListFragment = fragment;
    }

    public MainModule(LineChartFragment fragment) {
        this.lineChartFragment = fragment;
    }

    public MainModule(AnalysisActivity activity) {
        this.analysisActivity = activity;
    }

    public MainModule(TestMetricActivity activity) {
        this.testMetricActivity = activity;
    }

    @Provides
    public MainActivity provideActivity() {
        return activity;
    }

    @Provides
    public AnalysisActivity provideAnalysisActivity() {
        return analysisActivity;
    }

    @Provides
    public TestMetricActivity provideTestActivity() {
        return testMetricActivity;
    }

    @Provides
    public UserRepository provideRepository() {
        return UserRepository.getInstance();
    }

    @Provides
    public MainPresenter provideMainPresenter(MainActivity activity, UserRepository repository) {
        return new MainPresenter(activity, repository);
    }

    @Provides
    public StudentsListFragment provideFragment() {
        return fragment;
    }

    @Provides
    public TeacherRepository provideTeacherRepository() {
        return TeacherRepository.getInstance();
    }

    @Provides
    public StudentRepository provideStudentRepository() {
        return StudentRepository.getInstance();
    }

    @Provides
    public ExerciseListFragment provideExerciseListFragment() {
        return exerciseListFragment;
    }

    @Provides
    public ExamListFragment provideExamListFragment() {
        return examListFragment;
    }

    @Provides
    public HomeworkListFragment provideHomeListFragment() {
        return homeworkListFragment;
    }

    @Provides
    public LineChartFragment provideLineChartFragment() {
        return lineChartFragment;
    }

    @Provides
    public StudentListPresenter getStuListPresenter(StudentsListFragment fragment, TeacherRepository repository) {
        return new StudentListPresenter(fragment, repository);
    }

    @Provides
    public ExamListPresenter getExamListPresenter(ExamListFragment fragment, TeacherRepository repository) {
        return new ExamListPresenter(fragment, repository);
    }

    @Provides
    public ExerciseListPresenter getExerciseListPresenter(ExerciseListFragment fragment, TeacherRepository repository) {
        return new ExerciseListPresenter(fragment, repository);
    }

    @Provides
    public HomeListPresenter getHomeListPresenter(HomeworkListFragment fragment, TeacherRepository repository) {
        return new HomeListPresenter(fragment, repository);
    }

    @Provides
    public ScorePresenter getScorePresenter(LineChartFragment fragment, TeacherRepository repository) {
        return new ScorePresenter(repository, fragment);
    }

    @Provides
    public AnalysisPresenter getAnalysisPresenter(AnalysisActivity activity, StudentRepository repository) {
        return new AnalysisPresenter(repository, activity);
    }


    @Provides
    public ReadmePresenter getReadmePresenter(TestMetricActivity activity,StudentRepository repository){
        return new ReadmePresenter(repository,activity);
    }
}
