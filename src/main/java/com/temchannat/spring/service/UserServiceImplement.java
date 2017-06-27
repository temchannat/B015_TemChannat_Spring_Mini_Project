package com.temchannat.spring.service;

import com.temchannat.spring.model.User;
import com.temchannat.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * Created by temchannat on 6/22/17.
 */

@Service
public class UserServiceImplement implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImplement(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> userList() {
        return userRepository.userList();
    }

    @Override
    public boolean save(User user) {

        String userHash = UUID.randomUUID().toString();
        String userStatus = "1";
        String createdDate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());


        user.setUserHash(userHash);
        user.setStatus(userStatus);
        user.setCreatedDate(createdDate);

        boolean status = userRepository.save(user);
        if (status) {
            System.out.print("USER ID " + user.getId());
            System.out.print("Inserted ");
        } else {
            System.out.print("Insert fail");
        }
        return status;
    }

    @Override
    public boolean updateByUserHash(User user) {
        return userRepository.update(user);
    }

    @Override
    public boolean deleteByUserHash(String userHash) {
        return userRepository.delete(userHash);
    }

    @Override
    public User findOneUser(String userHash) {
        User user = userRepository.findOneUser(userHash);
        return user;
    }

    @Override
    public int countTotalUsers() {
        return userRepository.countTotalUsers();
    }

    @Override
    public int countMale() {
        return userRepository.countMale();
    }

    @Override
    public int countFemale() {
        return userRepository.countFemale();
    }


}
