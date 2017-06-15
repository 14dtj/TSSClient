package cn.edu.nju.tssclient.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tjDu on 2017/6/14.
 */

public class Analysis{
    @SerializedName("studentId")
    private int studentId;
    @SerializedName("assignmentId")
    private int assignmentId;
    @SerializedName("questionResults")
    private List<QuestionResult> questionResults;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public List<QuestionResult> getQuestionResults() {
        return questionResults;
    }

    public void setQuestionResults(List<QuestionResult> questionResults) {
        this.questionResults = questionResults;
    }
}
