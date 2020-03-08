package com.miaoshaproject.service;

import com.miaoshaproject.service.modle.UserModel;

public interface UserService {
    //通过用户id获取用户对象
    UserModel getUserById(Integer id);
}
