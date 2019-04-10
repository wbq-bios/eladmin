package me.zhengjie.service.impl;

import me.zhengjie.domain.TravelPoint;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.repository.TravelPointRepository;
import me.zhengjie.service.TravelPointService;
import me.zhengjie.service.dto.TravelPointDTO;
import me.zhengjie.service.mapper.TravelPointMapper;
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
public class TravelPointServiceImpl implements TravelPointService {

    @Autowired
    private TravelPointRepository travelPointRepository;

    @Autowired
    private TravelPointMapper travelPointMapper;

    @Override
    public TravelPointDTO findById(Long id) {
        Optional<TravelPoint> travelPoint = travelPointRepository.findById(id);
        ValidationUtil.isNull(travelPoint,"TravelPoint","id",id);
        return travelPointMapper.toDto(travelPoint.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TravelPointDTO create(TravelPoint resources) {
        return travelPointMapper.toDto(travelPointRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(TravelPoint resources) {
        Optional<TravelPoint> optionalTravelPoint = travelPointRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalTravelPoint,"TravelPoint","id",resources.getId());

        TravelPoint travelPoint = optionalTravelPoint.get();
        // 此处需自己修改
        resources.setId(travelPoint.getId());
        travelPointRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        travelPointRepository.deleteById(id);
    }

    @Override
    public List<TravelPoint> showTravelPoint() {
        return travelPointRepository.showTravelPoint();
    }
}