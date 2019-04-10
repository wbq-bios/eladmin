package me.zhengjie.repository;

import me.zhengjie.domain.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
* @author wbq
* @date 2019-04-10
*/
public interface BannerRepository extends JpaRepository<Banner, Long>, JpaSpecificationExecutor {

    @Query(value = "select * from banner where isuse=1",nativeQuery = true)
    List<Banner> showBanner();
}