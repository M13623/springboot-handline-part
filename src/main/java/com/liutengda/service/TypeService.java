package com.liutengda.service;

import com.liutengda.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liutengda.pojo.vo.PortalVo;
import com.liutengda.utils.Result;

/**
* @author LiuTengDa
* @description 针对表【news_type】的数据库操作Service
* @createDate 2024-03-14 09:35:49
*/
public interface TypeService extends IService<Type> {
    Result findAllTypes();


}
