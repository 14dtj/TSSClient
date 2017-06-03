package cn.edu.nju.tssclient.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tjDu on 2017/5/31.
 */

public class SimpleUser {
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
