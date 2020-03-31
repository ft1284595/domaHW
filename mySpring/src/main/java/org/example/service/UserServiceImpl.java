package org.example.service;

import org.example.dao.IUserDao;
import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;


    @Override
    @Transactional
    public void saveUser(User user) {
       userDao.saveUser(user);
    }

    @Override
    @Transactional
    public List<User> findUser(User user) {
        return userDao.findUser(user);
    }
}
