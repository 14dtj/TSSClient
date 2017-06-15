package cn.edu.nju.tssclient.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tjDu on 2017/6/14.
 */

public class MetricData implements Serializable{
    @SerializedName("git_url")
    private String gitUrl;
    @SerializedName("measured")
    private boolean measured;
    @SerializedName("total_line_count")
    private int totalLineCount;
    @SerializedName("comment_line_count")
    private int commentLineCount;
    @SerializedName("field_count")
    private int fieldCount;
    @SerializedName("method_count")
    private int methodCount;
    @SerializedName("max_coc")
    private int maxCoc;

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public boolean isMeasured() {
        return measured;
    }

    public void setMeasured(boolean measured) {
        this.measured = measured;
    }

    public int getTotalLineCount() {
        return totalLineCount;
    }

    public void setTotalLineCount(int totalLineCount) {
        this.totalLineCount = totalLineCount;
    }

    public int getCommentLineCount() {
        return commentLineCount;
    }

    public void setCommentLineCount(int commentLineCount) {
        this.commentLineCount = commentLineCount;
    }

    public int getFieldCount() {
        return fieldCount;
    }

    public void setFieldCount(int fieldCount) {
        this.fieldCount = fieldCount;
    }

    public int getMethodCount() {
        return methodCount;
    }

    public void setMethodCount(int methodCount) {
        this.methodCount = methodCount;
    }

    public int getMaxCoc() {
        return maxCoc;
    }

    public void setMaxCoc(int maxCoc) {
        this.maxCoc = maxCoc;
    }
}
