package com.lcz.wanandroid.utils;

import com.lcz.wanandroid.base.BaseApp;
import com.lcz.wanandroid.base.Person;
import com.lcz.wanandroid.base.User;
import com.lcz.wanandroid.dao.DaoMaster;
import com.lcz.wanandroid.dao.DaoSession;
import com.lcz.wanandroid.dao.PersonDao;
import com.lcz.wanandroid.dao.UserDao;

import java.util.List;

/**
 * Created by 李承泽 on 2019/5/6.
 */
public class MyUtils {
    private static volatile MyUtils mMyUtils;
    private final PersonDao mUserDao;

    private MyUtils() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(BaseApp.getInstance(), "user");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        mUserDao = daoSession.getPersonDao();
    }

    public static MyUtils getmMyUtils() {
        if (mMyUtils == null) {
            synchronized (MyUtils.class) {
                if (mMyUtils == null) {
                    mMyUtils = new MyUtils();

                }
            }
        }
        return mMyUtils;
    }

    public void insert(Person person) {
        if (!has(person)) {
            mUserDao.insertOrReplace(person);
        }
    }

    public void Delete(Person person) {
        if (has(person)) {
            mUserDao.delete(person);
        }
    }

    public List<Person> QueryAll() {
        return mUserDao.queryBuilder().list();
    }

    public boolean has(Person person) {
        List<Person> list = mUserDao.queryBuilder().where(PersonDao.Properties.Name.eq(person.getName())).list();
        if (list.size() > 0 && list != null) {
            return true;
        } else {
            return false;
        }
    }
}
