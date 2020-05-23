package com.cloud.justyou.controller.fore;

import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSONObject;
import com.cloud.justyou.model.User;
import com.cloud.justyou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author HP
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user/personalPage")
    public String goToPersonalPage() {
        return "/fore/personalPage";
    }

    /**
     * @param editJson 用户编辑的个人信息json串
     * @return 个人主页
     */
    @ResponseBody
    @RequestMapping(value = "/user/edit/save", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String saveUserInfo(@RequestBody String editJson, HttpServletRequest request) {
        Optional<String> optional = Optional.ofNullable(editJson);
        JSONObject json = new JSONObject();
        if (!optional.isPresent()) {
            json.put("success", false);
            return json.toJSONString();
        }
        JSONObject jsonObject = JSONObject.parseObject(optional.get());
        int userId = (int) request.getSession().getAttribute("userId");
        String userQqNumber = jsonObject.getString("userQqNumber");
        String userName = jsonObject.getString("userName");
        String userNickName = jsonObject.getString("userNickName");
        String userPassword = jsonObject.getString("userPassword");
        String userEmail = jsonObject.getString("userEmail");
        Timestamp userRegistrationTime = Timestamp.valueOf(LocalDateTime.now());
        Timestamp userBirthday = (Timestamp) jsonObject.get("userBirthday");
        String userTelephoneNumber = jsonObject.getString("userTelephoneNumber");
        String personalSignature = jsonObject.getString("personalSignature");
        User user = new User(userId, userQqNumber, userName, userNickName, userPassword,
                userEmail, null, userRegistrationTime, userBirthday,
                userTelephoneNumber, personalSignature);
        userService.update(user);
        return json.toJSONString();
    }

    /**
     * @param file 用户上传的图片
     * @param session 用户获取登录者id
     * @return 附带有图片的json数据
     */
    @ResponseBody
    @RequestMapping(value = "/user/uploadUserHeadImage", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String uploadUserHeadImage(MultipartFile file, HttpSession session) {
        JSONObject json = new JSONObject();
        try {
            byte[] bytes = file.getBytes();
            Optional<Object> userId = Optional.ofNullable(session.getAttribute("userId"));
            if (userId.isPresent()) {
                User user = userService.get((int)userId.get());
                user.setUserProfilePhoto(bytes);
                json.put("success", userService.update(user));
                //这里把图片字节数组转换为base64数据，然后在页面中显示
                json.put("picture", Base64.encode(bytes));
            }else {
                json.put("success",false);
                json.put("picture", null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toJSONString();
    }

    /**
     * @param request 获取会话请求
     * @return 发布成功页面
     */
    @ResponseBody
    @RequestMapping(value = "/user/publish", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String publish(HttpServletRequest request) {
        Optional<Object> userId = Optional.ofNullable(request.getSession().getAttribute("userId"));
        JSONObject jsonObject = new JSONObject();
        if (!userId.isPresent()) {
            jsonObject.put("success", false);
            return jsonObject.toJSONString();
        }
        jsonObject.put("success", true);
        return jsonObject.toJSONString();
    }
}
