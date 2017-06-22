package com.temchannat.spring.service;

import com.temchannat.spring.model.User;

import java.util.List;

/**
 * Created by temchannat on 6/22/17.
 */
public interface UserService {

    List<User> userList();
    boolean addUser(User user);

}
