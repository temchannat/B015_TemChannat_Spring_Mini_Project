package com.temchannat.spring.service;

import com.temchannat.spring.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by temchannat on 6/22/17.
 */

@Service
public interface UserService {

    List<User> userList();
    boolean save(User user);
    boolean updateByUserHash(User user);
    boolean deleteByUserHash(String userHash);
    User findOneUser(String userHash);


}
