package org.example.dao;

import org.example.entity.User;

import java.util.List;

public interface IUserDao {
    public void saveUser(User user);

    List<User> findUser(User user);
}
