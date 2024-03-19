package com.liutengda.controller;

import com.liutengda.mapper.HeadlineMapper;
import com.liutengda.pojo.vo.PortalVo;
import com.liutengda.service.HeadlineService;
import com.liutengda.service.TypeService;
import com.liutengda.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("portal")
@CrossOrigin
public class PortalController {
    @Autowired
    private HeadlineService headlineService;
    @Autowired
    private TypeService typeService;
    @GetMapping("findAllTypes")
    public Result findAllTypes(){
        Result result = typeService.findAllTypes();
        return result;
    }
    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo){
        Result result = headlineService.findNewsPage(portalVo);
        return result;
    }
    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(Integer hid){
        Result result = headlineService.showHeadlineDetail(hid);
        return result;
    }
}
