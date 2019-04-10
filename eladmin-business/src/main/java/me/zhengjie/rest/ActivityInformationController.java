package me.zhengjie.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.domain.ActivityInformation;
import me.zhengjie.service.ActivityInformationService;
import me.zhengjie.service.dto.ActivityInformationDTO;
import me.zhengjie.service.query.ActivityInformationQueryService;
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
public class ActivityInformationController {

    @Autowired
    private ActivityInformationService activityInformationService;

    @Autowired
    private ActivityInformationQueryService activityInformationQueryService;

    private static final String ENTITY_NAME = "activityInformation";

    @Log("查询ActivityInformation")
    @GetMapping(value = "/activityInformation")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getActivityInformations(ActivityInformationDTO resources, Pageable pageable){
        return new ResponseEntity(activityInformationQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增ActivityInformation")
    @PostMapping(value = "/activityInformation")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody ActivityInformation resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(activityInformationService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改ActivityInformation")
    @PutMapping(value = "/activityInformation")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody ActivityInformation resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        activityInformationService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除ActivityInformation")
    @DeleteMapping(value = "/activityInformation/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        activityInformationService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}