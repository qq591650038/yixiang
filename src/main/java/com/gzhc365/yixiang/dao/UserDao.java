package com.gzhc365.yixiang.dao;

import com.gzhc365.yixiang.vo.UserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserDao extends JpaRepository<UserVo, String> {
    
    List<UserVo> findByHospitalId(String id);

    Page<UserVo> findByHospitalId(String id, Pageable pageable);

    Page<UserVo> findByHospitalIdOrderByCreateTime(String id, Pageable pageable);
    
    Page<UserVo> findDistinctByHospitalId(String id, Pageable pageable);
    
    int countByHospitalId(String id);

    @Query(value = "select *, count(1) as bind_card_num from BIZ_MEDICAL_CARD b where b.HOSPITAL_ID = ?1 and b.STATE = 1 group by b.OPEN_ID order by CREATE_TIME limit ?2 ,?3", nativeQuery = true)
    List<UserVo> findGroupByHospitalId(String id, int index, int size);


    @Query(value = "select count(1) from (select count(1) from BIZ_MEDICAL_CARD b where b.HOSPITAL_ID = ?1 and b.STATE = 1 group by b.OPEN_ID order by CREATE_TIME ) a", nativeQuery = true)
    int countGroupByHospitalId(String id);
    
    
    
}
