package com.liutengda.service;

import com.liutengda.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liutengda.utils.Result;

/**
* @author LiuTengDa
* @description 针对表【news_user】的数据库操作Service
* @createDate 2024-03-14 09:35:49
*/
public interface UserService extends IService<User> {


    /**
     * 登陆业务
     * @param user 客户端传入的数据
     * @return 带有客户uid的result对象
     */
    Result login(User user);

    /**
     * 根据token查询用户数据
     * @param token
     * @return
     */
    Result getUserInfo(String token);

    Result checkUserName(String username);

    Result regist(User user);



}
