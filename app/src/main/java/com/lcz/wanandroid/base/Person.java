package com.lcz.wanandroid.base;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 李承泽 on 2019/5/6.
 */
@Entity
public class Person {
    @Id(autoincrement = true)
    Long id;
    @Property
    String name;
    @Property
    String url;
    @Property
    String autor;
    @Property
    String Fenlei;
    @Property
    String time;

    @Generated(hash = 680152704)
    public Person(Long id, String name, String url, String autor, String Fenlei,
                  String time) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.autor = autor;
        this.Fenlei = Fenlei;
        this.time = time;
    }

    @Generated(hash = 1024547259)
    public Person() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAutor() {
        return this.autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFenlei() {
        return this.Fenlei;
    }

    public void setFenlei(String Fenlei) {
        this.Fenlei = Fenlei;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
