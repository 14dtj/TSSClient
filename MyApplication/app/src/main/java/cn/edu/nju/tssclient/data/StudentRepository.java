package cn.edu.nju.tssclient.data;

import android.util.Base64;

import cn.edu.nju.tssclient.data.model.Analysis;
import cn.edu.nju.tssclient.data.model.Readme;
import cn.edu.nju.tssclient.view.util.ReadmeParams;
import rx.Observable;

/**
 * Created by tjDu on 2017/6/14.
 */

public class StudentRepository extends BaseRepository {
    private static StudentRepository instance;

    private StudentRepository() {
        super();
    }

    public static StudentRepository getInstance() {
        if (instance == null)
            instance = new StudentRepository();
        return instance;
    }

    public Observable<Analysis> getAnalysis(String username, String password, int assignmentId, int studentId) {
        return apiService.getAnalysis(getToken(username, password), 38, 256);
    }

    public Observable<Readme> getReadme(ReadmeParams params) {
        return apiService.getReadme(getToken(params.username, params.password), 98, 227,
                26);
    }

    private String getToken(String username, String password) {
        String temp = username + ":" + password;
        String token = "Basic " + Base64.encodeToString(temp.getBytes(), Base64.NO_WRAP);
        return token;
    }
}
