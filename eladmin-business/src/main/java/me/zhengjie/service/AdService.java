package me.zhengjie.service;

import me.zhengjie.domain.Ad;
import me.zhengjie.service.dto.AdDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
* @author wbq
* @date 2019-04-10
*/
@CacheConfig(cacheNames = "ad")
public interface AdService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    AdDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    AdDTO create(Ad resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Ad resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);

    /**
     * showAd
     * @return
     */
    @CacheEvict(allEntries = true)
    List<Ad> showAd();
}