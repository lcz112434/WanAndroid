package com.lcz.wanandroid.fragment.Class.Bean;

import java.io.Serializable;

/**
 * Created by 李承泽 on 2019/5/4.
 */
public class User implements Serializable{
    String name;
    String tab;
    int id;

    public User() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(String name, String tab, int id) {
        this.name = name;
        this.tab = tab;
        this.id = id;
    }
}
