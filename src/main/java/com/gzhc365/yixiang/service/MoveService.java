package com.gzhc365.yixiang.service;


import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.gzhc365.yixiang.dao.PatientDao;
import com.gzhc365.yixiang.dao.UserDao;
import com.gzhc365.yixiang.util.FileUtils;
import com.gzhc365.yixiang.vo.HospitalVo;
import com.gzhc365.yixiang.vo.PatientVo;

import com.gzhc365.yixiang.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * @author: qq895
 * @date: 2020/7/1 17:03
 * @description:
 */
@Service
public class MoveService {
    Logger logger = LoggerFactory.getLogger(MoveService.class);
    @Autowired
    private UserDao userDao;

    @Autowired
    private PatientDao patientDao;
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    public List<PatientVo> findAll(){
        return patientDao.findAll();
    }

    public List<PatientVo> findUserByHospitalId(HospitalVo hospitalVo){
        logger.info("入参:{}",JSONObject.toJSONString(hospitalVo));
        int count = userDao.countGroupByHospitalId(hospitalVo.getHospitalId());
        int pageNum = 5000;
        int pageCount = (count / pageNum) + 1;
        if(count < pageNum){
            pageCount = 1;
        }
        BufferedWriter writer = null;
        logger.info("总行数:{},总页数:{},单页记录数:{}",count, pageCount,pageNum);
        for (int i = 0; i < pageCount; i++) {
            int beginIndex = i * pageNum;
            //获取到用户数据
            List<UserVo> patientVos = userDao.findGroupByHospitalId(hospitalVo.getHospitalId(), beginIndex, pageNum);
            patientVos.forEach(n -> {
                n.setCount(Integer.parseInt(String.valueOf(redisTemplate.opsForValue().increment("id"))));
                n.setPre(hospitalVo.getPre());
                n.setPlatformId(Integer.parseInt(hospitalVo.getHisId()));
                redisTemplate.opsForValue().set(n.getOpenId(), n.getId(), 100, TimeUnit.MINUTES);
            });

            try {
                boolean isFirst = false;
                int mod = i % 10;
                int div = i / 10;
                if (mod == 0) {
                    isFirst = true;
                    File file = new File(  "t_uc_user-" + (div + 1) + ".txt");
                    writer = new BufferedWriter(new FileWriter(file, true));
                }
                List<Map<String,Object>> dataMap = getDataMapUser(patientVos);
                FileUtils.writeFile(dataMap, writer, isFirst);
                writer.flush();
            } catch (Exception e) {
                logger.error("生成数据文件异常：");
            }
        }
        return new ArrayList<>();
    }



    public List<PatientVo> findPatByHospitalId(HospitalVo hospitalVo){
        logger.info("入参:{}",JSONObject.toJSONString(hospitalVo));
        int count = patientDao.countByHospitalIdAndState(hospitalVo.getHospitalId(), "1");
        int pageNum = 5000;
        int pageCount = (count / pageNum) + 1;
        logger.info("总行数:{},总页数:{},单页记录数:{}",count, pageCount,pageNum);
        BufferedWriter writer = null;
        for (int i = 0; i < pageCount; i++) {
            int beginIndex = i * pageNum;
            //获取到用户数据
            System.out.println("beginIndex="+beginIndex  + "----pageNum="+pageNum);
            List<PatientVo> patientVos = patientDao.findByHospitalIdAndStateOrderByCreateTime(hospitalVo.getHospitalId(),"1", PageRequest.of(i, pageNum)).toList();
            patientVos.forEach(n -> {
                n.setCount(Integer.parseInt(String.valueOf(redisTemplate.opsForValue().increment("id"))));
                n.setPre(hospitalVo.getPre());
                n.setHisName(hospitalVo.getHospitalName());
                n.setSaltValue(hospitalVo.getSaltValue());
                n.setPlatformId(Integer.parseInt(hospitalVo.getHisId()));
                if(null != redisTemplate.opsForValue().get(n.getOpenId())){
                    n.setUserId(String.valueOf(redisTemplate.opsForValue().get(n.getOpenId())));
                }
            });

            try {
                boolean isFirst = false;
                int mod = i % 10;
                int div = i / 10;
                if (mod == 0) {
                    isFirst = true;
                    File file = new File(  "t_uc_patients-" + (div + 1) + ".txt");
                    writer = new BufferedWriter(new FileWriter(file, true));
                }
                List<Map<String,Object>> dataMap = getDataMapPat(patientVos);
                FileUtils.writeFile(dataMap, writer, isFirst);
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }


    public List<Map<String,Object>> getDataMapUser(List<UserVo> oldList){
        List<Map<String,Object>> list = new ArrayList<>();
        if(!CollectionUtils.isEmpty(oldList)) {
            Map<String,Object> tmpMap;
            StringBuffer sb = new StringBuffer();
            for(UserVo user : oldList) {
                try {
                    tmpMap = new LinkedHashMap<String,Object>();
                    tmpMap.put("id", user.getId());
                    tmpMap.put("platform_id", user.getPlatformId());
                    tmpMap.put("channel_id", user.getOpenId());
                    tmpMap.put("platform_source", user.getPlatformSource());
                    tmpMap.put("sex", user.getSex());
                    tmpMap.put("mobile", user.getMobile());
                    //tmpMap.put("is_real_name", user.getIsRealName());
                    //tmpMap.put("real_name", user.getRealName());
                    //tmpMap.put("id_type", user.getIdType());
                    //tmpMap.put("id_number", user.getIdNumber());
                    tmpMap.put("bind_card_num", null == user.getBindCardNum() ? 0 : user.getBindCardNum());
                    //tmpMap.put("password", user.getPassword());
                    tmpMap.put("attention_status", "1");
                    tmpMap.put("create_time", DateUtil.now());
                    tmpMap.put("modify_time", DateUtil.now());
                    tmpMap.put("nick_name", user.getName());
                    list.add(tmpMap);
                }catch(Exception e) {
                    sb.append(user.getId());
                    continue;
                }
            }
            System.out.println("生成数据文件失败id有：" + sb.toString());
        }
        return list;
    }






    public List<Map<String,Object>> getDataMapPat(List<PatientVo> oldList){
        List<Map<String,Object>> list = new ArrayList<>();
        if(!CollectionUtils.isEmpty(oldList)) {
            Map<String,Object> tmpMap;
            StringBuffer sb = new StringBuffer();
            for(PatientVo patient : oldList) {
                try {
                    tmpMap = new LinkedHashMap<String,Object>();
                    tmpMap.put("id", patient.getId());
                    tmpMap.put("name", patient.getName());
                    tmpMap.put("his_id", patient.getPlatformId());
                    tmpMap.put("his_name", patient.getHisName());
                    tmpMap.put("user_id", patient.getUserId());
                    tmpMap.put("channel_type", "001");
                    tmpMap.put("patient_type", "0");
                    tmpMap.put("relation_type", "5");
                    tmpMap.put("id_type", null == patient.getIdType() ? 1:patient.getIdType());
                    tmpMap.put("id_no", patient.getIdNo());
                    tmpMap.put("pat_his_no", patient.getPatHisNo());
                    tmpMap.put("sex", StringUtils.equalsAny(patient.getSex(), "M","F") ? "":patient.getSex());
                    tmpMap.put("birthday", patient.getBirth());
                    tmpMap.put("mobile", patient.getMobile());
                    tmpMap.put("address", patient.getAddress());
                    tmpMap.put("bind_status", "1");
                    tmpMap.put("parent_name", patient.getGuardName());
                    tmpMap.put("parent_id_type", null == patient.getGuardIdType() ? 1:patient.getGuardIdType());
                    tmpMap.put("parent_id_no", patient.getGuardIdNo());
                    tmpMap.put("pat_card_type", "21");
                    tmpMap.put("pat_card_no", patient.getCardNo());
                    tmpMap.put("is_defalut", null == patient.getIsDefalut() ? 0:patient.getIsDefalut());
                    //tmpMap.put("pat_his_no", patient.getPatHisNo());
                    tmpMap.put("create_time", DateUtil.now());
                    tmpMap.put("update_time", DateUtil.now());

                    tmpMap.put("name_cipher", patient.getNameCipher());
                    tmpMap.put("id_no_cipher", patient.getIdNoCipher());
                    tmpMap.put("mobile_cipher", patient.getMobileCipher());
                    tmpMap.put("address_cipher", patient.getAddressCipher());
                    tmpMap.put("parent_name_cipher", patient.getParentNameCipher());
                    tmpMap.put("parent_id_no_cipher", patient.getParentIdNoCipher());
                    list.add(tmpMap);
                }catch(Exception e) {
                    continue;
                }
            }
            System.out.println("生成数据文件失败id有：" + sb.toString());
        }
        return list;
    }
    
    
}
