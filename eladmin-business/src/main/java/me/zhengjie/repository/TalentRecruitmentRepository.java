package me.zhengjie.repository;

import me.zhengjie.domain.TalentRecruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
* @author wbq
* @date 2019-04-15
*/
public interface TalentRecruitmentRepository extends JpaRepository<TalentRecruitment, Long>, JpaSpecificationExecutor {
    @Query(value = "select * from  talent_recruitment",nativeQuery = true)
    List<TalentRecruitment> getTalentRecruitment();
}