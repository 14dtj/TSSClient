package cn.edu.nju.tssclient.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tjDu on 2017/6/14.
 */

public class QuestionResult implements Serializable{
    @SerializedName("questionId")
    private int questionId;
    @SerializedName("questionTitle")
    private String questionTitle;
    @SerializedName("scoreResult")
    private ScoreResult scoreResult;
    @SerializedName("testResult")
    private TestResult testResult;
    @SerializedName("metricData")
    private MetricData metricData;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public ScoreResult getScoreResult() {
        return scoreResult;
    }

    public void setScoreResult(ScoreResult scoreResult) {
        this.scoreResult = scoreResult;
    }

    public TestResult getTestResult() {
        return testResult;
    }

    public void setTestResult(TestResult testResult) {
        this.testResult = testResult;
    }

    public MetricData getMetricData() {
        return metricData;
    }

    public void setMetricData(MetricData metricData) {
        this.metricData = metricData;
    }
}
