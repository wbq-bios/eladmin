package me.zhengjie.service.impl;

import me.zhengjie.domain.ActivityInformation;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.repository.ActivityInformationRepository;
import me.zhengjie.service.ActivityInformationService;
import me.zhengjie.service.dto.ActivityInformationDTO;
import me.zhengjie.service.mapper.ActivityInformationMapper;
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
public class ActivityInformationServiceImpl implements ActivityInformationService {

    @Autowired
    private ActivityInformationRepository activityInformationRepository;

    @Autowired
    private ActivityInformationMapper activityInformationMapper;

    @Override
    public ActivityInformationDTO findById(Long id) {
        Optional<ActivityInformation> activityInformation = activityInformationRepository.findById(id);
        ValidationUtil.isNull(activityInformation,"ActivityInformation","id",id);
        return activityInformationMapper.toDto(activityInformation.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ActivityInformationDTO create(ActivityInformation resources) {
        return activityInformationMapper.toDto(activityInformationRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ActivityInformation resources) {
        Optional<ActivityInformation> optionalActivityInformation = activityInformationRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalActivityInformation,"ActivityInformation","id",resources.getId());

        ActivityInformation activityInformation = optionalActivityInformation.get();
        // 此处需自己修改
        resources.setId(activityInformation.getId());
        activityInformationRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        activityInformationRepository.deleteById(id);
    }

    @Override
    public List<ActivityInformation> showActivity() {
        return activityInformationRepository.showActivity();
    }
}