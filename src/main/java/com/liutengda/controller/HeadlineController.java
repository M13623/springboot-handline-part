package com.liutengda.controller;

import com.liutengda.pojo.Headline;
import com.liutengda.service.HeadlineService;
import com.liutengda.utils.JwtHelper;
import com.liutengda.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("headline")
@CrossOrigin
public class HeadlineController {

    @Autowired
    private HeadlineService headlineService;
    @RequestMapping("publish")
    public Result publish(@RequestHeader String token, @RequestBody Headline headline){
        return headlineService.publish(token,headline);
    }
    @PostMapping("findHeadlineByHid")
    public Result findHeadlineByHid(@Param("hid") Integer hid){
        return headlineService.findHeadlineByHid(hid);
    }
    @PostMapping("update")
    public Result update(@RequestBody Headline headline){
        return headlineService.update(headline);
    }
    @PostMapping("removeByHid")
    public Result removeByHid(@Param("hid") Integer hid){
        return headlineService.removeByHid(hid);
    }
}
