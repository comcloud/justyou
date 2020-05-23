package com.cloud.justyou.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户图片类，用于存放用户喜欢的图片
 * @author HP
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserPictures {
    private int userId;
    private byte[] userPictures;
}
