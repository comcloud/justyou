package com.cloud.justyou.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;

/**
 * @author HP
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class User {
    /** 用户id **/
    private int userId;
    /** 用户qq号码 **/
    private String userQqNumber;
    /** 用户名 **/
    private String userName;
    /** 用户昵称 **/
    private String userNickName;
    /** 用户密码 **/
    private String userPassword;
    /** 用户邮箱 **/
    private String userEmail;
    /** 用户头像 **/
    private byte[] userProfilePhoto;
    /** 用户注册时间 **/
    private Timestamp userRegistrationTime;
    /** 用户生日 **/
    private Timestamp userBirthday;
    /** 用户电话号码 **/
    private String userTelephoneNumber;
    /** 用户个人签名 **/
    private String personalSignature;
}
