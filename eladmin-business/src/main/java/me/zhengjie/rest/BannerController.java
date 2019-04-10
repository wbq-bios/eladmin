package me.zhengjie.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.domain.Banner;
import me.zhengjie.service.BannerService;
import me.zhengjie.service.dto.BannerDTO;
import me.zhengjie.service.query.BannerQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author wbq
* @date 2019-04-10
*/
@RestController
@RequestMapping("api")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @Autowired
    private BannerQueryService bannerQueryService;

    private static final String ENTITY_NAME = "banner";

    @Log("查询Banner")
    @GetMapping(value = "/banner")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getBanners(BannerDTO resources, Pageable pageable){
        return new ResponseEntity(bannerQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Banner")
    @PostMapping(value = "/banner")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Banner resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(bannerService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Banner")
    @PutMapping(value = "/banner")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Banner resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        bannerService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Banner")
    @DeleteMapping(value = "/banner/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        bannerService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("展示：获取轮播图")
    @GetMapping(value = "/show/getBanner")
    public ResponseEntity getShowBanner(){
        return new ResponseEntity(bannerService.showBanner(),HttpStatus.OK);
    }
}