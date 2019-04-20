package me.zhengjie.repository;

import me.zhengjie.domain.TravelPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author wbq
* @date 2019-04-16
*/
public interface TravelPointRepository extends JpaRepository<TravelPoint, Long>, JpaSpecificationExecutor {
}