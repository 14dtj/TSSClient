package cn.edu.nju.tssclient.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tjDu on 2017/6/14.
 */

public class ScoreResult implements Serializable{
    @SerializedName("git_url")
    private String gitUrl;
    @SerializedName("score")
    private double score;
    @SerializedName("scored")
    private boolean scored;

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
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
