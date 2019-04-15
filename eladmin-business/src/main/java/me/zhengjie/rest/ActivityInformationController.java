package me.zhengjie.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(description = "活动信息api")
public class ActivityInformationController {

    @Autowired
    private ActivityInformationService activityInformationService;

    @Autowired
    private ActivityInformationQueryService activityInformationQueryService;

    private static final String ENTITY_NAME = "activityInformation";

    @Log("查询ActivityInformation")
    @GetMapping(value = "/activityInformation")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "查询ActivityInformation", notes = "会查询数据库中活动信息表的所有列")
    public ResponseEntity getActivityInformations(ActivityInformationDTO resources, Pageable pageable){
        return new ResponseEntity(activityInformationQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增ActivityInformation")
    @PostMapping(value = "/activityInformation")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "新增ActivityInformation")
    public ResponseEntity create(@Validated @RequestBody ActivityInformation resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(activityInformationService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改ActivityInformation")
    @PutMapping(value = "/activityInformation")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "修改ActivityInformation",notes = "id不能为空")
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
    @ApiOperation(value = "删除ActivityInformation具体的信息")
    public ResponseEntity delete(@PathVariable Long id){
        activityInformationService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("展示：获取活动信息")
    @GetMapping("/show/getActivity")
    @ApiOperation(value = "首页展示的活动信息list")
    public ResponseEntity showActivity(){
        return new ResponseEntity(activityInformationService.showActivity(),HttpStatus.OK);
    }
}