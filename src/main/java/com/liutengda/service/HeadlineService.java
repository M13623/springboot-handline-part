package com.liutengda.service;

import com.liutengda.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liutengda.pojo.vo.PortalVo;
import com.liutengda.utils.Result;

/**
* @author LiuTengDa
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-03-14 09:35:49
*/
public interface HeadlineService extends IService<Headline> {

    Result findNewsPage(PortalVo portalVo);

    Result showHeadlineDetail(Integer hid);

    Result publish(String token,Headline headline);

    Result findHeadlineByHid(Integer hid);

    Result update(Headline headline);

    Result removeByHid(Integer hid);
}
