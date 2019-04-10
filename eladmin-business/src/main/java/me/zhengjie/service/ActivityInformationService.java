package me.zhengjie.service;

import me.zhengjie.domain.ActivityInformation;
import me.zhengjie.service.dto.ActivityInformationDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
* @author wbq
* @date 2019-04-10
*/
@CacheConfig(cacheNames = "activityInformation")
public interface ActivityInformationService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    ActivityInformationDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    ActivityInformationDTO create(ActivityInformation resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(ActivityInformation resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);

    /**
     * showActivity
     * @return
     */
    List<ActivityInformation> showActivity();
}