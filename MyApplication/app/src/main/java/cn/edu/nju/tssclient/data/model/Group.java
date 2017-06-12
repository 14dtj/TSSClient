package cn.edu.nju.tssclient.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tjDu on 2017/6/7.
 */

public class Group {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
