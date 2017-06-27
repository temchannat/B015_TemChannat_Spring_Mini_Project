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

    /**
     * Getting all Users to put in a list
     *
     * @return
     */
    @Select("SELECT id, username, email, gender, phonenumber,  status, user_hash FROM users WHERE STATUS = '1' ORDER BY id")
    @Results(value = {
            @Result(property = "phoneNumber", column = "phonenumber"),
            @Result(property = "userHash", column = "user_hash")
    })
    List<User> userList();


    /**
     * Select only one user by userHash
     *
     * @param userHash
     * @return
     */
    @Select("SELECT id, username, email, gender, phonenumber, status, user_hash as userHash FROM users WHERE user_hash = #{userHash}")
    User findOneUser(@Param("userHash") String userHash);


    /**
     * Insert/Save to database
     *
     * @param user
     * @return
     */

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


    /**
     * Delete user, but actually only set status to ZERO(0) so it doesn't select
     *
     * @param userHash
     * @return
     */
    @Update("UPDATE users SET status = '0' WHERE user_hash = #{userHash}")
    boolean delete(@Param("userHash") String userHash);


    /**
     * Update users by userHash
     *
     * @param user
     * @return
     */
    @Update("UPDATE users SET " +
            "username = #{user.username}, " +
            "email = #{user.email}, " +
            "gender = #{user.gender}, " +
            "phonenumber = #{user.phoneNumber} " +
            "WHERE user_hash = #{user.userHash}")
    boolean update(@Param("user") User user);


    /**
     * Count total users
     *
     * @return
     */
    @Select("SELECT COUNT(*) as total_user FROM users WHERE status = '1'")
    int countTotalUsers();

    /**
     * Count Total Male
     *
     * @return
     */
    @Select("SELECT COUNT(gender) FROM users WHERE gender = 'M' AND status = '1'")
    int countMale();

    /**
     * Count total Female
     *
     * @return
     */
    @Select("SELECT COUNT(gender) FROM users WHERE gender = 'F' AND status = '1'")
    int countFemale();


}
