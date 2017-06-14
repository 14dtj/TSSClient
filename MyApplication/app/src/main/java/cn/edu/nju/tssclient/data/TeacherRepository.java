package cn.edu.nju.tssclient.data;

import android.util.Base64;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.nju.tssclient.data.model.Exam;
import cn.edu.nju.tssclient.data.model.Group;
import cn.edu.nju.tssclient.data.model.Score;
import cn.edu.nju.tssclient.data.model.User;
import rx.Observable;
import retrofit2.Call;

/**
 * Created by tjDu on 2017/6/7.
 */

public class TeacherRepository extends BaseRepository {
    private static TeacherRepository instance;

    private TeacherRepository() {
        super();
    }

    public static TeacherRepository getInstance() {
        if (instance == null)
            instance = new TeacherRepository();
        return instance;
    }

    public Observable<List<Group>> getGroups(String username, String password) {
        return apiService.getGroups(getToken(username, password));
    }

    public Observable<List<User>> getStudents(String username, String password, int groupId) {
        return apiService.getStudents(getToken(username, password), groupId);
    }

    public Observable<List<Exam>> getExams(String username, String password, int courseId) {
        return apiService.getExams(getToken(username, password), courseId);
    }

    public Observable<List<Exam>> getHomework(String username, String password, int courseId) {
        return apiService.getHomework(getToken(username, password), courseId);
    }

    public Observable<List<Exam>> getExercise(String username, String password, int courseId) {
        return apiService.getExercise(getToken(username, password), courseId);
    }

    public Observable<Score> getScore(String username, String password, int assignmentId) {
        return apiService.getScore(getToken(username, password), assignmentId);
    }

    private String getToken(String username, String password) {
        String temp = username + ":" + password;
        String token = "Basic " + Base64.encodeToString(temp.getBytes(), Base64.NO_WRAP);
        return token;
    }
}
