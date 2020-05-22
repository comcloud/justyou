package com.cloud.justyou.controller;

import com.alibaba.fastjson.JSONObject;
import com.cloud.justyou.model.User;
import com.cloud.justyou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Optional;

/**
 * 具有功能：
 * 1.用户请求编辑个人信息后的保存请求
 * @author HP
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @param editJson 用户编辑的个人信息json串
     * @return 个人主页
     */
    @RequestMapping(value = "/user/edit/save",method = RequestMethod.GET)
    public String saveUserInfo(@RequestBody String editJson, HttpServletRequest request){
        Optional<String> optional = Optional.ofNullable(editJson);
        if (optional.isPresent()) {
            return "/user/edit";
        }
        JSONObject jsonObject = JSONObject.parseObject(optional.get());
        int userId = (int) request.getSession().getAttribute("userId");
        String userQqNumber = jsonObject.getString("userQqNumber");
        String userName = jsonObject.getString("userName");
        String userNickName = jsonObject.getString("userNickName");
        String userPassword = jsonObject.getString("userPassword");
        String userEmail = jsonObject.getString("userEmail");
        byte[] userProfilePhoto = (byte[]) jsonObject.get("userProfilePhoto");
        Timestamp userRegistrationTime = (Timestamp) jsonObject.get("userRegistrationTime");
        Timestamp userBirthday = (Timestamp) jsonObject.get("userBirthday");
        String userTelephoneNumber = jsonObject.getString("userTelephoneNumber");
        String personalSignature = jsonObject.getString("personalSignature");
        User user = new User(userId,userQqNumber,userName,userNickName,userPassword,
                            userEmail,userProfilePhoto,userRegistrationTime,userBirthday,
                            userTelephoneNumber,personalSignature);
        userService.update(user);
        return "user/personalPage";
    }

    /**
     * @param request 获取会话请求
     * @return 发布成功页面
     */
    @RequestMapping(value = "/user/publish",method = RequestMethod.POST)
    public String publish(HttpServletRequest request){
        Optional<Object> userId = Optional.ofNullable(request.getSession().getAttribute("userId"));
        if (userId.isPresent()) {
            return "/user/personalPage";
        }

        return "/user/publishSuccess";
    }
}
