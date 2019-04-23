package me.zhengjie.repository;

import me.zhengjie.domain.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
* @author wbq
* @date 2019-04-10
*/
public interface AdRepository extends JpaRepository<Ad, Long>, JpaSpecificationExecutor {
    @Query(value = "select * from ad",nativeQuery = true)
    List<Ad> showAd();
}