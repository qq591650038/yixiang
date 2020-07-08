package com.gzhc365.yixiang.dao;

import com.gzhc365.yixiang.vo.PatientVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PatientDao extends JpaRepository<PatientVo, String> {
    
    List<PatientVo> findByHospitalId(String id);

    Page<PatientVo> findByHospitalId(String id, Pageable pageable);

    Page<PatientVo> findByHospitalIdAndStateOrderByCreateTime(String id,String state, Pageable pageable);
    
    Page<PatientVo> findDistinctByHospitalId(String id, Pageable pageable);
    
    int countByHospitalId(String id);

    @Query(value = "select *, count(1) as bind_card_num from BIZ_MEDICAL_CARD_1 b where b.HOSPITAL_ID = ?1 group by b.OPEN_ID order by CREATE_TIME limit ?2 ,?3", nativeQuery = true)
    List<PatientVo> findGroupByHospitalId(String id, int index, int size);


    @Query(value = "select count(1) from (select count(1) from BIZ_MEDICAL_CARD_1 b where b.HOSPITAL_ID = ?1 group by b.OPEN_ID order by CREATE_TIME ) a", nativeQuery = true)
    int countGroupByHospitalId(String id);
    
    
    
}
