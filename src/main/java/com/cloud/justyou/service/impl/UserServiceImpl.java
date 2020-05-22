package com.cloud.justyou.service.impl;

import com.cloud.justyou.mapper.UserMapper;
import com.cloud.justyou.model.User;
import com.cloud.justyou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HP
 * @noinspection ALL
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void update(User user) {
        userMapper.updateOne(user);
    }

    @Override
    public User get(int userId) {
        return userMapper.selectOne(userId);
    }
}
