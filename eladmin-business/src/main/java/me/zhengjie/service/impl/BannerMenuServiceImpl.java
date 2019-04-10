package me.zhengjie.service.impl;

import me.zhengjie.domain.BannerMenu;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.repository.BannerMenuRepository;
import me.zhengjie.service.BannerMenuService;
import me.zhengjie.service.dto.BannerMenuDTO;
import me.zhengjie.service.mapper.BannerMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
* @author wbq
* @date 2019-04-10
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BannerMenuServiceImpl implements BannerMenuService {

    @Autowired
    private BannerMenuRepository bannerMenuRepository;

    @Autowired
    private BannerMenuMapper bannerMenuMapper;

    @Override
    public BannerMenuDTO findById(Long id) {
        Optional<BannerMenu> bannerMenu = bannerMenuRepository.findById(id);
        ValidationUtil.isNull(bannerMenu,"BannerMenu","id",id);
        return bannerMenuMapper.toDto(bannerMenu.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BannerMenuDTO create(BannerMenu resources) {
        return bannerMenuMapper.toDto(bannerMenuRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BannerMenu resources) {
        Optional<BannerMenu> optionalBannerMenu = bannerMenuRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalBannerMenu,"BannerMenu","id",resources.getId());

        BannerMenu bannerMenu = optionalBannerMenu.get();
        // 此处需自己修改
        resources.setId(bannerMenu.getId());
        bannerMenuRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        bannerMenuRepository.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<BannerMenu> showBannerMenu() {
        return bannerMenuRepository.showBannerMenu();
    }


}