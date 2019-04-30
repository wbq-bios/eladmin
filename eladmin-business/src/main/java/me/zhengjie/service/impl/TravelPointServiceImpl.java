package me.zhengjie.service.impl;

import me.zhengjie.domain.Picture;
import me.zhengjie.domain.TravelPoint;
import me.zhengjie.domain.TravelPoint2Images;
import me.zhengjie.repository.PictureRepository;
import me.zhengjie.repository.TravelPoint2ImagesRepository;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.repository.TravelPointRepository;
import me.zhengjie.service.TravelPointService;
import me.zhengjie.service.dto.TravelPointDTO;
import me.zhengjie.service.mapper.TravelPointMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
* @author wbq
* @date 2019-04-16
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TravelPointServiceImpl implements TravelPointService {

    @Autowired
    private TravelPointRepository travelPointRepository;

    @Autowired
    private TravelPointMapper travelPointMapper;

    @Autowired
    private TravelPoint2ImagesRepository travelPoint2ImagesRepository;

    @Autowired
    private PictureRepository pictureRepository;

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
    public TravelPointDTO create(TravelPointDTO resources) {
        TravelPoint point=new TravelPoint();
        point.setDescription(resources.getDescription());
        point.setEnName(resources.getEnName());
        point.setImgUrl(resources.getImgUrl());
        //point.setSortNum(resources.getSortNum());
        point.setName(resources.getName());
        point.setTransportation(resources.getTransportation());
        TravelPointDTO afterDto=travelPointMapper.toDto(travelPointRepository.save(point));

        for (Picture picture:resources.getImages()) {
            TravelPoint2Images point2Images =new TravelPoint2Images();
            point2Images.setPictureId(picture.getId());
            point2Images.setTravelId(afterDto.getId());
            travelPoint2ImagesRepository.save(point2Images);
        }
        return afterDto;
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
    @Transactional(rollbackFor = Exception.class)
    public List<TravelPointDTO> showTravelPoint() {
        List<TravelPointDTO> res=new LinkedList<>();
        List<TravelPoint>getData=travelPointRepository.showTravelPoint();
        for (TravelPoint travelPoint:getData) {
            TravelPointDTO travelPointDTO=new TravelPointDTO();
            travelPointDTO.setId(travelPoint.getId());
            travelPointDTO.setImgUrl(travelPoint.getImgUrl());
            travelPointDTO.setName(travelPoint.getName());
            travelPointDTO.setSortNum(travelPoint.getSortNum());
            travelPointDTO.setDescription(travelPoint.getDescription());
            travelPointDTO.setTransportation(travelPoint.getTransportation());
            travelPointDTO.setEnName(travelPoint.getEnName());
            List<TravelPoint2Images> imgs=travelPoint2ImagesRepository.getImgs(travelPoint.getId());
            List<Picture> pictures=new LinkedList<>();
            for (TravelPoint2Images temp:imgs) {
                Picture picture=pictureRepository.getById(temp.getPictureId());
                pictures.add(picture);
            }
            travelPointDTO.setImages(pictures);
            res.add(travelPointDTO);
        }
        return res;

    }

    @Override
    public List<Picture> getImages(Long id) {
        List<TravelPoint2Images> imgs=travelPoint2ImagesRepository.getImgs(id);
        List<Picture> pictures=new LinkedList<>();
        for (TravelPoint2Images temp:imgs) {
            Picture picture=pictureRepository.getById(temp.getPictureId());
            pictures.add(picture);
        }
        return pictures;
    }

    @Override
    public void update(TravelPointDTO resources) {
        Optional<TravelPoint> optionalTravelPoint = travelPointRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalTravelPoint,"TravelPoint","id",resources.getId());

        TravelPoint travelPoint = optionalTravelPoint.get();
        TravelPoint afterPoint =new TravelPoint();

        afterPoint.setTransportation(resources.getTransportation());
        afterPoint.setName(resources.getName());
        afterPoint.setImgUrl(resources.getImgUrl());
        afterPoint.setEnName(resources.getEnName());
        afterPoint.setDescription(resources.getDescription());
        afterPoint.setId(travelPoint.getId());

        resources.setId(travelPoint.getId());
        travelPointRepository.save(afterPoint);
        travelPoint2ImagesRepository.del(resources.getId());
        for (Picture p:resources.getImages()) {
            TravelPoint2Images point2Images=new TravelPoint2Images();
            point2Images.setTravelId(resources.getId());
            point2Images.setPictureId(p.getId());
            travelPoint2ImagesRepository.save(point2Images);
        }
    }

    @Override
    public void bashDel(List<Long> idList) {

        List<TravelPoint2Images>temp;
        for (Long id:idList) {
            temp=travelPoint2ImagesRepository.getImgs(id);
            List<Long>picIdList=new LinkedList<>();
            for (TravelPoint2Images tem:temp) {
                picIdList.add(tem.getId());
            }
            if (!picIdList.isEmpty()){
                travelPoint2ImagesRepository.bashDel(picIdList);
            }
        }
        travelPointRepository.bashDel(idList);
    }
}