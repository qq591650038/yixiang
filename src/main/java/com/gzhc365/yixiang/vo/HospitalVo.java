package com.gzhc365.yixiang.vo;

import java.io.Serializable;

/**
 * @author: qq895
 * @date: 2020/7/1 16:01
 * @description:
 */
public class HospitalVo implements Serializable {
    /**
     * 医享网医院唯一id
     */
    private String pre;
    
    private String hospitalId;

    private String hospitalCode;
    
    private String hospitalName;
    
    private String saltValue;
    
    private String hisId;

    public String getHisId() {
        return hisId;
    }

    public void setHisId(String hisId) {
        this.hisId = hisId;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getPre() {
        return pre;
    }

    public void setPre(String pre) {
        this.pre = pre;
    }

    public String getSaltValue() {
        return saltValue;
    }

    public void setSaltValue(String saltValue) {
        this.saltValue = saltValue;
    }
}
