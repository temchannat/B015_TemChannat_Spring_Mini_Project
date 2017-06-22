package com.temchannat.spring.repository;

import com.temchannat.spring.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by temchannat on 6/22/17.
 */

@Repository
public class UserRepositoryImplement implements UserRepository {
    List<User> users = new ArrayList<>();


    public UserRepositoryImplement() {
        for(int i=5; i<=10; i++) {
            users.add(new User(i, "First ", "Last", "Male", "firstlastname", "email@gmail.com", "Admin"));
        }
    }

    @Override
    public List<User> userList() {

        return users;
    }

    @Override
    public boolean addUser(User user) {
        return users.add(user);
    }
}
