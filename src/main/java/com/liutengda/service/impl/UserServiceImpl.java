package com.liutengda.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liutengda.mapper.TypeMapper;
import com.liutengda.pojo.Type;
import com.liutengda.pojo.User;
import com.liutengda.service.UserService;
import com.liutengda.mapper.UserMapper;
import com.liutengda.utils.JwtHelper;
import com.liutengda.utils.MD5Util;
import com.liutengda.utils.Result;
import com.liutengda.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author LiuTengDa
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2024-03-14 09:35:49
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtHelper jwtHelper;
    @Override
    public Result login(User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername,user.getUsername());
        User user1 = userMapper.selectOne(lambdaQueryWrapper);
        if (user1==null){
            return Result.build(null,ResultCodeEnum.USERNAME_ERROR);
        }
        if (!StringUtils.isEmpty(user.getUserPwd()) &&
                MD5Util.encrypt(user.getUserPwd()).equals(user1.getUserPwd())){
            String token = jwtHelper.createToken(Long.valueOf(user1.getUid()));
            Map map = new HashMap<>();
            map.put("token",token);
            return Result.ok(map);
        }
        return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
    }

    @Override
    public Result getUserInfo(String token) {
        boolean expiration = jwtHelper.isExpiration(token);
        if (expiration){
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        }
        int id = jwtHelper.getUserId(token).intValue();
        User loginUser = userMapper.selectById(id);
        loginUser.setUserPwd("");
        Map data = new HashMap<>();
        data.put("loginUser",loginUser);
        return Result.ok(data);
    }

    @Override
    public Result checkUserName(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,username);
        Long count = userMapper.selectCount(queryWrapper);
        if(count==0){
            return Result.ok(null);
        }
        return Result.build(null,ResultCodeEnum.USERNAME_USED);
    }

    @Override
    public Result regist(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,user.getUsername());
        Long count = userMapper.selectCount(queryWrapper);
        if (count != 0){
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }
        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        userMapper.insert(user);
        return Result.ok(null);
    }





}




