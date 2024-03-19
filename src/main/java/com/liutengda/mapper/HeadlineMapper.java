package com.liutengda.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.liutengda.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liutengda.pojo.vo.PortalVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author LiuTengDa
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2024-03-14 09:35:49
* @Entity com.liutengda.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {
    IPage<Map> selectMypage(IPage<Map> page,@Param("portalVo") PortalVo portalVo);

    Map selectDetailMap(Integer hid);
}




