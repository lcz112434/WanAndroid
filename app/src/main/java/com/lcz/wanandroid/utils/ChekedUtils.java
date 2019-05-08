package com.lcz.wanandroid.utils;

import com.lcz.wanandroid.base.BaseApp;
import com.lcz.wanandroid.base.Check;
import com.lcz.wanandroid.base.Person;
import com.lcz.wanandroid.dao.CheckDao;
import com.lcz.wanandroid.dao.DaoMaster;
import com.lcz.wanandroid.dao.DaoSession;
import com.lcz.wanandroid.dao.PersonDao;

import java.util.List;

/**
 * Created by 李承泽 on 2019/5/6.
 */
public class ChekedUtils {
    private static volatile ChekedUtils mMyUtils;
    private final CheckDao mUserDao;

    private ChekedUtils() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(BaseApp.getInstance(), "check");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        mUserDao = daoSession.getCheckDao();
    }

    public static ChekedUtils getmMyUtils() {
        if (mMyUtils == null) {
            synchronized (ChekedUtils.class) {
                if (mMyUtils == null) {
                    mMyUtils = new ChekedUtils();

                }
            }
        }
        return mMyUtils;
    }

    public void insert(Check person) {
        if (!has(person)) {
            mUserDao.insertOrReplace(person);
        }
    }

    public void UpData(Check person) {
        if (has(person)) {
            mUserDao.update(person);
        }
    }

    public Check Query() {
        return mUserDao.queryBuilder().unique();
    }

    public boolean has(Check person) {
        List<Check> list = mUserDao.queryBuilder().where(CheckDao.Properties.Checkle.eq(person.getCheckle())).list();
        if (list.size() > 0 && list != null) {
            return true;
        } else {
            return false;
        }
    }
}
