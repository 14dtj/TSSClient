package cn.edu.nju.tssclient.view.util;

import java.io.Serializable;

/**
 * Created by tjDu on 2017/6/21.
 */

public class ReadmeParams implements Serializable{

    public String username;
    public String password;
    public int assignmentId;
    public int studentId;
    public int questionId;

    public ReadmeParams(String username, String password, int assignmentId, int studentId) {
        this.username = username;
        this.password = password;
        this.assignmentId = assignmentId;
        this.studentId = studentId;
    }
}
