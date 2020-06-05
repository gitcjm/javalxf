package com.str.spring_batis.mapper;

import com.str.spring_batis.entity.BaUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 通过@MapperScan，让MyBatis自动扫描指定包的所有Mapper并创建实现类
 * */
public interface UserMapper {

    @Select("SELECT * FROM ba_user WHERE id = #{id}")
    BaUser getById(@Param("id") long id);

    @Select("SELECT * FROM ba_user LIMIT #{offset}, #{maxResults}")
    List<BaUser> getAll(@Param("offset") int offset, @Param("maxResults") int maxResults);

    @Options(useGeneratedKeys=true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO ba_user (email, password, name, createAt) VALUES " +
            "(#{user.email}, #{user.password}, #{user.name}, #{user.createAt})")
    void insert(@Param("user") BaUser user);

    @Update("UPDATE ba_user SET name = #{user.name}, createAt = #{user.createAt} " +
            "WHERE id = #{user.id}")
    void update(@Param("user") BaUser user);

    @Delete("DELETE FROM ba_user WHERE id = #{id}")
    void deleteById(@Param("id") long id);
}
