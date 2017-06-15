package cn.edu.nju.tssclient.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tjDu on 2017/6/14.
 */

public class TestResult implements Serializable{
    @SerializedName("git_url")
    private String gitUrl;
    @SerializedName("compile_succeeded")
    private boolean compileSucceed;
    @SerializedName("tested")
    private boolean tested;
    @SerializedName("testcases")
    private List<Testcase> testcases;

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public boolean isCompileSucceed() {
        return compileSucceed;
    }

    public void setCompileSucceed(boolean compileSucceed) {
        this.compileSucceed = compileSucceed;
    }

    public boolean isTested() {
        return tested;
    }

    public void setTested(boolean tested) {
        this.tested = tested;
    }

    public List<Testcase> getTestcases() {
        return testcases;
    }

    public void setTestcases(List<Testcase> testcases) {
        this.testcases = testcases;
    }
}
