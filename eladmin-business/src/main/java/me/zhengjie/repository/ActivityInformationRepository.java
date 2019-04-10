package me.zhengjie.repository;

import me.zhengjie.domain.ActivityInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author wbq
* @date 2019-04-10
*/
public interface ActivityInformationRepository extends JpaRepository<ActivityInformation, Long>, JpaSpecificationExecutor {
}