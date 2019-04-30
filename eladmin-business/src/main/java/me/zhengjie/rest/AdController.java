package me.zhengjie.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.domain.Ad;
import me.zhengjie.service.AdService;
import me.zhengjie.service.dto.AdDTO;
import me.zhengjie.service.query.AdQueryService;
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
@Api(description = "广告的api")
public class AdController {

    @Autowired
    private AdService adService;

    @Autowired
    private AdQueryService adQueryService;

    private static final String ENTITY_NAME = "ad";

    @Log("查询Ad")
    @GetMapping(value = "/ad")
    @PreAuthorize("hasAnyRole('ADMIN','AD_ALL','AD_SELECT')")
    @ApiOperation(value = "获取所有的数据库中的广告的列")

    public ResponseEntity getAds(AdDTO resources, Pageable pageable){
        return new ResponseEntity(adQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Ad")
    @PostMapping(value = "/ad")
    @PreAuthorize("hasAnyRole('ADMIN','AD_ALL','AD_ADD')")
    @ApiOperation(value = "创建一个新的广告")
    public ResponseEntity create(@Validated @RequestBody Ad resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(adService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Ad")
    @PutMapping(value = "/ad")
    @PreAuthorize("hasAnyRole('ADMIN','AD_ALL','AD_UPDATE')")
    @ApiOperation(value = "修改广告列",notes = "id不能为空")
    public ResponseEntity update(@Validated @RequestBody Ad resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        adService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Ad")
    @ApiOperation(value = "删除广告列")
    @DeleteMapping(value = "/ad/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','AD_ALL','AD_DEL')")
    public ResponseEntity delete(@PathVariable Long id){
        adService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("展示 获取广告")
    @GetMapping("/show/getAd")
    @ApiOperation(value = "首页展示时的获取广告",notes = "有可能以后会变成多个，所以传的是list")
    public  ResponseEntity showAd(){
        return new ResponseEntity(adService.showAd(),HttpStatus.OK);
    }

    @Log("批量删除广告")
    @PreAuthorize("hasAnyRole('ADMIN','AD_ALL','AD_BASH_DEL')")
    @DeleteMapping(value = "/ads/bashDelAll")
    public ResponseEntity bashDelAll(@RequestBody List<Long>idList){
        adService.bashDel(idList);
        return new ResponseEntity(HttpStatus.OK);
    }
}