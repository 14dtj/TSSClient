package cn.edu.nju.tssclient.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tjDu on 2017/6/14.
 */

public class Score {
    @SerializedName("assignmentId")
    private int assignmentId;
    @SerializedName("questions")
    private List<QuestionScore> questions;

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public List<QuestionScore> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionScore> questions) {
        this.questions = questions;
    }
}
