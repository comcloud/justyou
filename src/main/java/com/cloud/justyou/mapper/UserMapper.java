package com.cloud.justyou.mapper;

import com.cloud.justyou.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author HP
 */
@Mapper
public interface UserMapper {
    /**
     * 更新一个用户
     * @param user 插入用户
     */
    void updateOne(@Param("user") User user);

    /**
     * @param userId 用户id
     * @return 用户信息
     */
    User selectOne(@Param("userId") int userId);
}
