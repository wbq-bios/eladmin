package me.zhengjie.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

import java.util.List;

/**
* @author wbq
* @date 2019-04-10
*/
@RestController
@RequestMapping("api")
@Api(description = "首页轮播图的api")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @Autowired
    private BannerQueryService bannerQueryService;

    private static final String ENTITY_NAME = "banner";

    @Log("查询Banner")
    @ApiOperation(value = "查询所有的banner")
    @GetMapping(value = "/banner")
    @PreAuthorize("hasAnyRole('ADMIN','BANNER_ALL','BANNER_SELECT')")
    public ResponseEntity getBanners(BannerDTO resources, Pageable pageable){
        return new ResponseEntity(bannerQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Banner")
    @PostMapping(value = "/banner")
    @PreAuthorize("hasAnyRole('ADMIN','BANNER_ALL','BANNER_ADD')")
    @ApiOperation(value = "新增banner")
    public ResponseEntity create(@Validated @RequestBody Banner resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(bannerService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Banner")
    @ApiOperation(value = "修改banner")
    @PutMapping(value = "/banner")
    @PreAuthorize("hasAnyRole('ADMIN','BANNER_ALL','BANNER_UPDATE')")
    public ResponseEntity update(@Validated @RequestBody Banner resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        bannerService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Banner")
    @ApiOperation(value = "删除banner")
    @DeleteMapping(value = "/banner/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','BANNER_ALL','BANNER_DEL')")
    public ResponseEntity delete(@PathVariable Long id){
        bannerService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("展示：获取轮播图")
    @ApiOperation(value = "首页展示中要用的banner图")
    @GetMapping(value = "/show/getBanner")
    public ResponseEntity getShowBanner(){
        return new ResponseEntity(bannerService.showBanner(),HttpStatus.OK);
    }

    @Log("批量删除banner")
    @DeleteMapping(value ="/banner/bashDel")
    @PreAuthorize("hasAnyRole('ADMIN','BANNER_ALL','BANNER_BASH_DEL')")
    public ResponseEntity bashDel(@RequestBody List<Long>idList){
        bannerService.bashDel(idList);
        return new ResponseEntity(HttpStatus.OK);
    }
}