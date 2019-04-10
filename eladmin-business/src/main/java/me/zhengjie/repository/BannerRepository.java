package me.zhengjie.repository;

import me.zhengjie.domain.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author wbq
* @date 2019-04-10
*/
public interface BannerRepository extends JpaRepository<Banner, Long>, JpaSpecificationExecutor {
}