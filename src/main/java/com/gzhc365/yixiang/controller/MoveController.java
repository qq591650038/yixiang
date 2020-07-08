package com.gzhc365.yixiang.controller;

import com.alibaba.fastjson.JSONObject;
import com.gzhc365.yixiang.service.MoveService;
import com.gzhc365.yixiang.vo.BaseResult;
import com.gzhc365.yixiang.vo.HospitalVo;
import com.gzhc365.yixiang.vo.PatientVo;
import com.gzhc365.yixiang.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: qq895
 * @date: 2020/7/1 15:58
 * @description:
 */
@RestController
@RequestMapping("api/move")
public class MoveController {
    @Autowired
    private MoveService moveService;
    
    @RequestMapping("/user/findAll")
    public BaseResult<List<PatientVo>> userSwitchFindAll(HospitalVo hospitalVo){
        return new BaseResult<>(moveService.findAll());
    }
    @RequestMapping("/user/findByHospitalId")
    public BaseResult<UserVo> userSwitchByHospitalId(HospitalVo hospitalVo){
        System.out.println(JSONObject.toJSONString(moveService.findUserByHospitalId(hospitalVo)));
        return new BaseResult<>();
    }

    @RequestMapping("/pat/findByHospitalId")
    public BaseResult<PatientVo> patSwitchByHospitalId(HospitalVo hospitalVo){
        System.out.println(JSONObject.toJSONString(moveService.findPatByHospitalId(hospitalVo)));
        return new BaseResult<>();
    }
}
