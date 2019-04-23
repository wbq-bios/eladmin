package me.zhengjie.repository;

import me.zhengjie.domain.ActivityInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
* @author wbq
* @date 2019-04-10
*/
public interface ActivityInformationRepository extends JpaRepository<ActivityInformation, Long>, JpaSpecificationExecutor {
    @Query(value = "select * from activity_information where is_show=1",nativeQuery =true)
    List<ActivityInformation> showActivity();
}