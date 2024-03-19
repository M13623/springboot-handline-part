package com.liutengda.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liutengda.pojo.Type;
import com.liutengda.pojo.vo.PortalVo;
import com.liutengda.service.TypeService;
import com.liutengda.mapper.TypeMapper;
import com.liutengda.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author LiuTengDa
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2024-03-14 09:35:49
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{
    @Autowired
    private TypeMapper typeMapper;

    @Override
    public Result findAllTypes() {
        List<Type> types = typeMapper.selectList(null);
        return Result.ok(types);
    }



}




