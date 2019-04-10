package me.zhengjie.service.impl;

import me.zhengjie.domain.Banner;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.repository.BannerRepository;
import me.zhengjie.service.BannerService;
import me.zhengjie.service.dto.BannerDTO;
import me.zhengjie.service.mapper.BannerMapper;
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
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public BannerDTO findById(Long id) {
        Optional<Banner> banner = bannerRepository.findById(id);
        ValidationUtil.isNull(banner,"Banner","id",id);
        return bannerMapper.toDto(banner.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BannerDTO create(Banner resources) {
        return bannerMapper.toDto(bannerRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Banner resources) {
        Optional<Banner> optionalBanner = bannerRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalBanner,"Banner","id",resources.getId());

        Banner banner = optionalBanner.get();
        // 此处需自己修改
        resources.setId(banner.getId());
        bannerRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        bannerRepository.deleteById(id);
    }
}