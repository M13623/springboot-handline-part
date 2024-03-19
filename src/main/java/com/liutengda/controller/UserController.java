package com.liutengda.controller;

import com.alibaba.druid.util.StringUtils;
import com.liutengda.pojo.User;
import com.liutengda.service.UserService;
import com.liutengda.utils.JwtHelper;
import com.liutengda.utils.Result;
import com.liutengda.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private UserService userService;
    @PostMapping("login")
    public Result login(@RequestBody User user){
        Result result = userService.login(user);
        return result;
    }
    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader String token){
        Result result = userService.getUserInfo(token);
        return result;
    }
    @PostMapping("checkUserName")
    public Result checkUserName(String username){
        Result result = userService.checkUserName(username);
        return result;
    }
    @PostMapping("regist")
    public Result regist(@RequestBody User user){
        Result result = userService.regist(user);
        return result;
    }
    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader String token){
        if(StringUtils.isEmpty(token) || jwtHelper.isExpiration(token)){
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }
        return Result.ok(null);
    }

}
