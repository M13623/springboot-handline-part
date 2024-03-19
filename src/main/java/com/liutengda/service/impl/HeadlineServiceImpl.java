package com.liutengda.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liutengda.pojo.Headline;
import com.liutengda.pojo.vo.PortalVo;
import com.liutengda.service.HeadlineService;
import com.liutengda.mapper.HeadlineMapper;
import com.liutengda.utils.JwtHelper;
import com.liutengda.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author LiuTengDa
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2024-03-14 09:35:49
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private HeadlineMapper headlineMapper;

    @Override
    public Result findNewsPage(PortalVo portalVo) {
        IPage<Map> page = new Page<>(portalVo.getPageNum(),portalVo.getPageSize());
        headlineMapper.selectMypage(page,portalVo);
        Map pageInfo = new HashMap<>();
        pageInfo.put("pageData",page.getRecords());
        pageInfo.put("pageNum",page.getCurrent());
        pageInfo.put("pageSize",page.getSize());
        pageInfo.put("totalPage",page.getPages());
        pageInfo.put("totalSize",page.getTotal());
        Map data = new HashMap<>();
        data.put("pageInfo",pageInfo);
        return Result.ok(data);

    }

    @Override
    public Result showHeadlineDetail(Integer hid) {
        Map headLineDetail = headlineMapper.selectDetailMap(hid);
        Headline headline = new Headline();
        headline.setHid((Integer) headLineDetail.get("hid"));
        headline.setPageViews((Integer) headLineDetail.get("pageViews")+1);
        headline.setVersion((Integer) headLineDetail.get("pageVersion"));

        headlineMapper.updateById(headline);
        Map data = new HashMap<>();
        data.put("headline",headLineDetail);
        return Result.ok(data);

    }

    @Override
    public Result publish(String token,Headline headline) {
        int publishId = jwtHelper.getUserId(token).intValue();
        headline.setPublisher(publishId);
        headline.setPageViews(0);
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());
        headlineMapper.insert(headline);
        return Result.ok(null);
    }

    @Override
    public Result findHeadlineByHid(Integer hid) {
        Headline headline = headlineMapper.selectById(hid);
        Map map = new HashMap<>();
        map.put("headline",headline);
        return Result.ok(map);
    }

    @Override
    public Result update(Headline headline) {
        Integer version = headlineMapper.selectById(headline.getHid()).getVersion();
        headline.setVersion(version);//要有版本 因为有乐观锁，没有版本修改不了
        headline.setUpdateTime(new Date());
        headlineMapper.updateById(headline);
        return Result.ok(null);
    }

    @Override
    public Result removeByHid(Integer hid) {
        headlineMapper.deleteById(hid);
        return Result.ok(null);
    }
}




