package me.zhengjie.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.domain.Banner;
import me.zhengjie.service.dto.BannerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author wbq
* @date 2019-04-10
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BannerMapper extends EntityMapper<BannerDTO, Banner> {

}