package me.zhengjie.service.impl;

import me.zhengjie.domain.TalentRecruitment;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.repository.TalentRecruitmentRepository;
import me.zhengjie.service.TalentRecruitmentService;
import me.zhengjie.service.dto.TalentRecruitmentDTO;
import me.zhengjie.service.mapper.TalentRecruitmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
* @author wbq
* @date 2019-04-15
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TalentRecruitmentServiceImpl implements TalentRecruitmentService {

    @Autowired
    private TalentRecruitmentRepository talentRecruitmentRepository;

    @Autowired
    private TalentRecruitmentMapper talentRecruitmentMapper;

    @Override
    public TalentRecruitmentDTO findById(Long id) {
        Optional<TalentRecruitment> talentRecruitment = talentRecruitmentRepository.findById(id);
        ValidationUtil.isNull(talentRecruitment,"TalentRecruitment","id",id);
        return talentRecruitmentMapper.toDto(talentRecruitment.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TalentRecruitmentDTO create(TalentRecruitment resources) {
        Timestamp tempTime = new Timestamp(System.currentTimeMillis());
        resources.setUpdateTime(tempTime);
        return talentRecruitmentMapper.toDto(talentRecruitmentRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(TalentRecruitment resources) {
        Optional<TalentRecruitment> optionalTalentRecruitment = talentRecruitmentRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalTalentRecruitment,"TalentRecruitment","id",resources.getId());
        Timestamp tempTime = new Timestamp(System.currentTimeMillis());
        resources.setUpdateTime(tempTime);
        TalentRecruitment talentRecruitment = optionalTalentRecruitment.get();
        // 此处需自己修改
        resources.setId(talentRecruitment.getId());
        talentRecruitmentRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        talentRecruitmentRepository.deleteById(id);
    }

    @Override
    public List<TalentRecruitment> getTalentRecruitment() {
        return talentRecruitmentRepository.getTalentRecruitment();
    }
}