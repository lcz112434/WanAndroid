package com.lcz.wanandroid.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.lcz.wanandroid.base.Check;
import com.lcz.wanandroid.base.Person;
import com.lcz.wanandroid.base.User;

import com.lcz.wanandroid.dao.CheckDao;
import com.lcz.wanandroid.dao.PersonDao;
import com.lcz.wanandroid.dao.UserDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig checkDaoConfig;
    private final DaoConfig personDaoConfig;
    private final DaoConfig userDaoConfig;

    private final CheckDao checkDao;
    private final PersonDao personDao;
    private final UserDao userDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        checkDaoConfig = daoConfigMap.get(CheckDao.class).clone();
        checkDaoConfig.initIdentityScope(type);

        personDaoConfig = daoConfigMap.get(PersonDao.class).clone();
        personDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        checkDao = new CheckDao(checkDaoConfig, this);
        personDao = new PersonDao(personDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);

        registerDao(Check.class, checkDao);
        registerDao(Person.class, personDao);
        registerDao(User.class, userDao);
    }
    
    public void clear() {
        checkDaoConfig.clearIdentityScope();
        personDaoConfig.clearIdentityScope();
        userDaoConfig.clearIdentityScope();
    }

    public CheckDao getCheckDao() {
        return checkDao;
    }

    public PersonDao getPersonDao() {
        return personDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

}
