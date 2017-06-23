package com.temchannat.spring.repository;

import com.sun.org.glassfish.gmbal.IncludeSubclass;
import com.temchannat.spring.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by temchannat on 6/22/17.
 */

@Repository
public interface UserRepository {

    @Select("SELECT id, username, email, gender, phonenumber,  status, user_hash FROM users WHERE STATUS = '1'")
    @Results(value = {
            @Result(property = "phoneNumber", column = "phonenumber"),
            @Result(property = "userHash", column = "user_hash")
    })
   List<User> userList();



    @Insert("INSERT INTO users (username, email, gender, phonenumber,status, user_hash)" +
            "   VALUES (" +
            "   #{user.username}, " +
            "   #{user.email}, " +
            "   #{user.gender}, " +
            "   #{user.phoneNumber}, " +
            "   #{user.status}," +
            "   #{user.userHash} " +
            "   " +
            " )")
    @SelectKey(
            statement = "SELECT last_value FROM users_id_seq",
            keyProperty = "user.id", keyColumn = "last_value",
            before = false,
            resultType = int.class
    )
    boolean save(@Param("user") User user);



    @Update("UPDATE users SET status = '0' WHERE user_hash = #{userHash}")
    boolean delete(@Param("userHash") String userHash);


    @Update("UPDATE users SET " +
            "username = #{user.username}, " +
            "email = #{user.email}, " +
            "gender = #{user.gender}, " +
            "phonenumber = #{user.phoneNumber} " +
            "WHERE user_hash = #{user.userHash}")
    boolean update(@Param("user") User user);










}
