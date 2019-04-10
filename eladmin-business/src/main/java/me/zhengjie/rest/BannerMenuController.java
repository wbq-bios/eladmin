package me.zhengjie.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.domain.BannerMenu;
import me.zhengjie.service.BannerMenuService;
import me.zhengjie.service.dto.BannerMenuDTO;
import me.zhengjie.service.query.BannerMenuQueryService;
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
public class BannerMenuController {

    @Autowired
    private BannerMenuService bannerMenuService;

    @Autowired
    private BannerMenuQueryService bannerMenuQueryService;

    private static final String ENTITY_NAME = "bannerMenu";

    @Log("查询BannerMenu")
    @GetMapping(value = "/bannerMenu")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getBannerMenus(BannerMenuDTO resources, Pageable pageable){
        return new ResponseEntity(bannerMenuQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增BannerMenu")
    @PostMapping(value = "/bannerMenu")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody BannerMenu resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(bannerMenuService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改BannerMenu")
    @PutMapping(value = "/bannerMenu")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody BannerMenu resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        bannerMenuService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除BannerMenu")
    @DeleteMapping(value = "/bannerMenu/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        bannerMenuService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    @Log("展示：获取轮播图处的菜单")
    @GetMapping("/show/getBannerMenu")
    public ResponseEntity showBannerMenu(){
        return new ResponseEntity(bannerMenuService.showBannerMenu(),HttpStatus.OK);
    }
}