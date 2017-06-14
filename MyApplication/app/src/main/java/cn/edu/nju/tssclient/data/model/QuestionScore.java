package cn.edu.nju.tssclient.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tjDu on 2017/6/14.
 */

public class QuestionScore {
    @SerializedName("questionInfo")
    private QuestionInfo questionInfo;
    @SerializedName("students")
    private List<Student> students;

    public QuestionInfo getQuestionInfo() {
        return questionInfo;
    }

    public void setQuestionInfo(QuestionInfo questionInfo) {
        this.questionInfo = questionInfo;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
