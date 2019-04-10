package me.zhengjie.repository;

import me.zhengjie.domain.TravelPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
* @author wbq
* @date 2019-04-10
*/
public interface TravelPointRepository extends JpaRepository<TravelPoint, Long>, JpaSpecificationExecutor {
    @Query(value = "select * from travel_point",nativeQuery = true)
    List<TravelPoint> showTravelPoint();
}