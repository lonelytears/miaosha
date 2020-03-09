package com.miaoshaproject.controller;


import com.miaoshaproject.controller.viewobject.UserVO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.CommonError;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.modle.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller("User")
@RequestMapping("/user")
public class UserController extends BaseController{
    @Resource
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping("/getotp")
    @ResponseBody
    public CommonReturnType getOpt(@RequestParam(name= "telphone") String telphone)
    {
        //生成验证码
        Random random = new Random();
        Integer randomInt = random.nextInt(999999);

        randomInt += 10000;

        //返回一个int参数类型的字符串形式
        String otpCode = String.valueOf(randomInt);

        //将opt的验证码与对应用户的手机号相关联
        //使用httpsession的方式绑定手机号和otp
        httpServletRequest.getSession().setAttribute(telphone, otpCode);

        System.out.println(otpCode);

        return CommonReturnType.create(null);
    }


    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BusinessException {
        //调取service服务获取对应id的用胡对象并返回给前端
        UserModel userModel = userService.getUserById(id);

        if (userModel == null) {
            throw new BusinessException(EmBusinessError.USER_NOT_EXIT);
        }

        UserVO userVO = converFromDataObject(userModel);
        return CommonReturnType.create(userVO);
    }

    /**
     * 将核心领域模型转化为可供ui使用的viewobject
     *
     * @param userModel
     * @return
     */
    public UserVO converFromDataObject(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }
}
