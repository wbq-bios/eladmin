package me.zhengjie.service.impl;

import me.zhengjie.domain.Ad;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.repository.AdRepository;
import me.zhengjie.service.AdService;
import me.zhengjie.service.dto.AdDTO;
import me.zhengjie.service.mapper.AdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author wbq
* @date 2019-04-10
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AdServiceImpl implements AdService {

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private AdMapper adMapper;

    @Override
    public AdDTO findById(Long id) {
        Optional<Ad> ad = adRepository.findById(id);
        ValidationUtil.isNull(ad,"Ad","id",id);
        return adMapper.toDto(ad.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AdDTO create(Ad resources) {
        return adMapper.toDto(adRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Ad resources) {
        Optional<Ad> optionalAd = adRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalAd,"Ad","id",resources.getId());

        Ad ad = optionalAd.get();
        // 此处需自己修改
        resources.setId(ad.getId());
        adRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        adRepository.deleteById(id);
    }
}