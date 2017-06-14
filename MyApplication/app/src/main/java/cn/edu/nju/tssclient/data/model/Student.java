package cn.edu.nju.tssclient.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tjDu on 2017/6/14.
 */

public class Student {
    @SerializedName("studentId")
    private int studentId;
    @SerializedName("studentName")
    private String studentName;
    @SerializedName("studentNumber")
    private String studentNumber;
    @SerializedName("score")
    private double score;
    @SerializedName("scored")
    private boolean scored;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public boolean isScored() {
        return scored;
    }

    public void setScored(boolean scored) {
        this.scored = scored;
    }
}
