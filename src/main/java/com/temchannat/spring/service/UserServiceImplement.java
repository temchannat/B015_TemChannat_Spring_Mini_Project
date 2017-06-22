package com.temchannat.spring.service;

import com.temchannat.spring.model.User;
import com.temchannat.spring.repository.UserRepositoryImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by temchannat on 6/22/17.
 */

@Service
public class UserServiceImplement implements UserService {

    UserRepositoryImplement userRepositoryImplement;

    @Autowired
    public UserServiceImplement(UserRepositoryImplement userRepositoryImplement) {
        this.userRepositoryImplement = userRepositoryImplement;
    }

    @Override
    public List<User> userList() {
        return userRepositoryImplement.userList();
    }

    @Override
    public boolean addUser(User user) {
        return userRepositoryImplement.addUser(user);
    }

}
