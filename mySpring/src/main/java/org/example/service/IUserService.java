package org.example.service;

import org.example.entity.User;

import java.util.List;

public interface IUserService {

    public void saveUser(User user);

    public List<User> findUser(User user);
}
