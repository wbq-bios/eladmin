package me.zhengjie.rest;

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

/**
* @author wbq
* @date 2019-04-10
*/
@RestController
@RequestMapping("api")
public class AdController {

    @Autowired
    private AdService adService;

    @Autowired
    private AdQueryService adQueryService;

    private static final String ENTITY_NAME = "ad";

    @Log("查询Ad")
    @GetMapping(value = "/ad")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getAds(AdDTO resources, Pageable pageable){
        return new ResponseEntity(adQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Ad")
    @PostMapping(value = "/ad")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Ad resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(adService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Ad")
    @PutMapping(value = "/ad")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Ad resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        adService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Ad")
    @DeleteMapping(value = "/ad/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        adService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}