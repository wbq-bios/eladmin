package me.zhengjie.rest;

import io.swagger.annotations.ApiOperation;
import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.service.TravelPointService;
import me.zhengjie.service.dto.TravelPointDTO;
import me.zhengjie.service.query.TravelPointQueryService;
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
* @date 2019-04-16
*/
@RestController
@RequestMapping("api")
public class TravelPointController {

    @Autowired
    private TravelPointService travelPointService;

    @Autowired
    private TravelPointQueryService travelPointQueryService;

    private static final String ENTITY_NAME = "travelPoint";

    @Log("查询TravelPoint")
    @GetMapping(value = "/travelPoint")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getTravelPoints(TravelPointDTO resources, Pageable pageable){
        return new ResponseEntity(travelPointQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增TravelPoint")
    @PostMapping(value = "/travelPoint")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody TravelPointDTO resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }

        return new ResponseEntity(travelPointService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改TravelPoint")
    @PutMapping(value = "/travelPoint")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody TravelPointDTO resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        travelPointService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除TravelPoint")
    @DeleteMapping(value = "/travelPoint/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        travelPointService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("展示：获取旅游景点")
    @ApiOperation(value = "首页展示中的旅游景点")
    @GetMapping("/show/getTravelPoint")
    public  ResponseEntity showTravelPoint(){
        return new ResponseEntity(travelPointService.showTravelPoint(),HttpStatus.OK);
    }
    @Log("获取具体的旅游景点详情")
    @GetMapping(value = "/travelPointDetail/{id}")
    public ResponseEntity getTravelPointDetail(@PathVariable Long id){
        TravelPointDTO dto=travelPointService.findById(id);
        dto.setImages(travelPointService.getImages(id));
        return new ResponseEntity(dto,HttpStatus.OK);
    }
    @Log("批量删除旅游景点")
    @DeleteMapping(value = "/travelPointDetail/bashDel")
    @ApiOperation(value = "批量删除旅游景点")
    public ResponseEntity bashDel(@RequestBody List<Long>idList){
        travelPointService.bashDel(idList);
        return new  ResponseEntity(HttpStatus.OK);
    }

}