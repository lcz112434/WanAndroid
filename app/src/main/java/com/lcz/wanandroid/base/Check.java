package com.lcz.wanandroid.base;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 李承泽 on 2019/5/7.
 */
@Entity
public class Check {
    @Id(autoincrement = true)
    Long id;
    boolean checkle;
    @Generated(hash = 1989657858)
    public Check(Long id, boolean checkle) {
        this.id = id;
        this.checkle = checkle;
    }
    @Generated(hash = 1080183208)
    public Check() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public boolean getCheckle() {
        return this.checkle;
    }
    public void setCheckle(boolean checkle) {
        this.checkle = checkle;
    }


}
