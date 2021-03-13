package com.str.mapper;

import com.str.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Select("select * from user where id = #{id}")
    User getById(@Param("id") long id);

    @Select("select * from user limit #{offset}, #{maxResults}")
    List<User> getAll(@Param("offset") int offset, @Param("maxResults") int maxResults);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO user (email, password, name, createdAt)" +
            " VALUES (#{user.email}, #{user.password}, #{user.name}, #{user.createdAt})")
    void insert(@Param("user") User user);

    @Update("UPDATE user SET name = #{user.name} WHERE id = #{user.id}")
    void update(@Param("user") User user);

    @Delete("DELETE FROM user WHERE id = #{id}")
    void deleteById(@Param("id") long id);

}
