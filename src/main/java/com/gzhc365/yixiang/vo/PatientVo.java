package com.gzhc365.yixiang.vo;

import com.gzhc365.web.util.PlainTextEncryptUtil;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: qq895
 * @date: 2020/7/1 16:08
 * @description:
 */

@Entity
@Table(name = "BIZ_MEDICAL_CARD")
public class PatientVo implements Serializable {
    /**
     * 从属的用户编号
     */
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "USER_ID")
    private String userId;

    /**
     * 所属医院Id
     */
    @Column(name = "HOSPITAL_ID")
    private String hospitalId;

    /**
     * 所属医院代码
     */
    @Column(name = "HOSPITAL_CODE")
    private String hospitalCode;

    /**
     * 所属分院Id
     */
    @Column(name = "BRANCH_ID")
    private String branchId;

    /**
     * 所属分院代码
     */
    @Column(name = "BRANCH_CODE")
    private String branchCode;

    /**
     * 平台
     */
    @Column(name = "PLATFORM")
    private Integer platform;

    /**
     * 用户名
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 性别
     */
    @Column(name = "SEX")
    private Integer sex;

    /**
     * 年龄
     */
    @Column(name = "AGE")
    private Integer age;

    /**
     * 出生日期
     */
    @Column(name = "BIRTH")
    private String birth;

    /**
     * 手机号码
     */
    @Column(name = "MOBILE")
    private String mobile;

    /**
     * 证件类型
     */
    @Column(name = "ID_TYPE")
    private Integer idType;

    /**
     * 证件号码
     */
    @Column(name = "ID_NO")
    private String idNo;

    /**
     * 地址
     */
    @Column(name = "ADDRESS")
    private String address;

    /**
     * 用户标志
     */
    @Column(name = "OPEN_ID")
    private String openId;

    /**
     * 卡
     */
    @Column(name = "CARD_NO")
    private String cardNo;

    /**
     * 监护人姓名
     */
    @Column(name = "GUARD_NAME")
    private String guardName;

    /**
     * 监护人证件类型
     */
    @Column(name = "GUARD_ID_TYPE")
    private Integer guardIdType;

    /**
     * 监护人证件号码
     */
    @Column(name = "GUARD_ID_NO")
    private String guardIdNo;

    /**
     * 监护人手机号码
     */
    @Column(name = "GUARD_MOBILE")
    private String guardMobile;

    @Column(name = "CREATE_TIME")
    private String createTime;

    @Column(name = "UPDATE_TIME")
    private String updateTime;

    @Column(name = "STATE")
    private String state;

    @Column(name = "PATIENT_ID")
    private String patHisNo;

    @Transient
    private String platformSource;
    
    @Transient
    private String pre;
    
    @Transient
    private int count;

    @Transient
    private int platformId;

    @Transient
    private String isDefalut;

    @Transient
    private String nameCipher;

    @Transient
    private String idNoCipher;

    @Transient
    private String mobileCipher;

    @Transient
    private String hisName;

    @Transient
    private String saltValue;

    public String getHisName() {
        return hisName;
    }

    public void setHisName(String hisName) {
        this.hisName = hisName;
    }

    public String getNameCipher() {
        if(StringUtils.isNotBlank(this.name)){
            return PlainTextEncryptUtil.encrypt(this.name, this.saltValue);
        }else {
            return "";
        }
        
    }

    public void setNameCipher(String nameCipher) {
        this.nameCipher = nameCipher;
    }

    public String getIdNoCipher() {
        if(StringUtils.isNotBlank(this.idNo)){
            return PlainTextEncryptUtil.encrypt(this.idNo, this.saltValue);
        }else {
            return "";
        }
    }

    public void setIdNoCipher(String idNoCipher) {
        this.idNoCipher = idNoCipher;
    }

    public String getMobileCipher() {
        if(StringUtils.isNotBlank(this.mobile)){
            return PlainTextEncryptUtil.encrypt(this.mobile, this.saltValue);
        }else {
            return "";
        }
    }

    public void setMobileCipher(String mobileCipher) {
        this.mobileCipher = mobileCipher;
    }

    public String getAddressCipher() {
        if(StringUtils.isNotBlank(this.address)){
            return PlainTextEncryptUtil.encrypt(this.address, this.saltValue);
        }else {
            return "";
        }
    }

    public void setAddressCipher(String addressCipher) {
        this.addressCipher = addressCipher;
    }

    public String getParentNameCipher() {
        if(StringUtils.isNotBlank(this.guardName)){
            return PlainTextEncryptUtil.encrypt(this.guardName, this.saltValue);
        }else {
            return "";
        }
    }

    public void setParentNameCipher(String parentNameCipher) {
        this.parentNameCipher = parentNameCipher;
    }

    public String getParentIdNoCipher() {
        if(StringUtils.isNotBlank(this.guardIdNo)){
            return PlainTextEncryptUtil.encrypt(this.guardIdNo, this.saltValue);
        }else {
            return "";
        }
    }

    public void setParentIdNoCipher(String parentIdNoCipher) {
        this.parentIdNoCipher = parentIdNoCipher;
    }

    @Transient
    private String addressCipher;

    @Transient
    private String parentNameCipher;

    @Transient
    private String parentIdNoCipher;

    public String getId() {
        return String.valueOf(Long.parseLong(pre) + count);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        switch (sex) {
            case 2:
                return "F";
            case 1:
            default:
                return "M";
        }
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getGuardName() {
        return guardName;
    }

    public void setGuardName(String guardName) {
        this.guardName = guardName;
    }

    public Integer getGuardIdType() {
        return guardIdType;
    }

    public void setGuardIdType(Integer guardIdType) {
        this.guardIdType = guardIdType;
    }

    public String getGuardIdNo() {
        return guardIdNo;
    }

    public void setGuardIdNo(String guardIdNo) {
        this.guardIdNo = guardIdNo;
    }

    public String getGuardMobile() {
        return guardMobile;
    }

    public void setGuardMobile(String guardMobile) {
        this.guardMobile = guardMobile;
    }

    public String getPre() {
        return pre;
    }

    public void setPre(String pre) {
        this.pre = pre;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPlatformSource() {
        return platform + "";
    }

    public void setPlatformSource(String platformSource) {
        this.platformSource = platformSource;
    }

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsDefalut() {
        return isDefalut;
    }

    public void setIsDefalut(String isDefalut) {
        this.isDefalut = isDefalut;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSaltValue() {
        return saltValue;
    }

    public void setSaltValue(String saltValue) {
        this.saltValue = saltValue;
    }

    public String getPatHisNo() {
        return patHisNo;
    }

    public void setPatHisNo(String patHisNo) {
        this.patHisNo = patHisNo;
    }
}
