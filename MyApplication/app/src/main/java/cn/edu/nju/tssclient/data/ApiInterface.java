package cn.edu.nju.tssclient.data;

import java.util.List;

import cn.edu.nju.tssclient.data.model.Exam;
import cn.edu.nju.tssclient.data.model.Group;
import cn.edu.nju.tssclient.data.model.Score;
import cn.edu.nju.tssclient.data.model.SimpleUser;
import cn.edu.nju.tssclient.data.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by tjDu on 2017/5/31.
 */

public interface ApiInterface {
    @POST("user/auth")
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Observable<User> getUserInfo(@Body SimpleUser user);

    @GET("group")
    Observable<List<Group>> getGroups(@Header("Authorization") String token);

    @GET("group/{groupId}/students")
    Observable<List<User>> getStudents(@Header("Authorization") String token, @Path("groupId") int groupId);

    @GET("course/{courseId}/exam")
    Observable<List<Exam>> getExams(@Header("Authorization") String token, @Path("courseId") int courseId);

    @GET("course/{courseId}/homework")
    Observable<List<Exam>> getHomework(@Header("Authorization") String token, @Path("courseId") int courseId);

    @GET("course/{courseId}/exercise")
    Observable<List<Exam>> getExercise(@Header("Authorization") String token, @Path("courseId") int courseId);

    @GET("assignment/{assignmentId}/score")
    Observable<Score> getScore(@Header("Authorization") String token, @Path("assignmentId") int assignmentId);
}
