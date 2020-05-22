package com.cloud.justyou.service;

import com.cloud.justyou.model.User;

/**
 * @author HP
 */
public interface UserService {
    /**
     * 保存用户信息
     * @param user 更新的用户信息
     */
    void update(User user);

    /**
     * 根据用户id获取用户信息
     * @param userId 用户Id
     * @return 用户信息
     */
    User get(int userId);
}
