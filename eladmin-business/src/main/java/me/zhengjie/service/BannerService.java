package me.zhengjie.service;

import me.zhengjie.domain.Banner;
import me.zhengjie.service.dto.BannerDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
* @author wbq
* @date 2019-04-10
*/
@CacheConfig(cacheNames = "banner")
public interface BannerService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    BannerDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    BannerDTO create(Banner resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Banner resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);

    @CacheEvict(allEntries = true)
    List<Banner> showBanner();

    void bashDel(List<Long> idList);
}