package me.zhengjie.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.zhengjie.domain.GenConfig;
import me.zhengjie.service.GenConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author jie
 * @date 2019-01-14
 */
@RestController
@RequestMapping("api")
@Api(description = "代码生成器的配置 查询api")
public class GenConfigController {

    @Autowired
    private GenConfigService genConfigService;

    /**
     * 查询生成器配置
     * @return
     */
    @GetMapping(value = "/genConfig")
    @ApiOperation(value = "查询生成器配置")
    public ResponseEntity get(){
        return new ResponseEntity(genConfigService.find(), HttpStatus.OK);
    }

    @PutMapping(value = "/genConfig")
    @ApiOperation(value = "邮件配置")
    public ResponseEntity emailConfig(@Validated @RequestBody GenConfig genConfig){
        return new ResponseEntity(genConfigService.update(genConfig),HttpStatus.OK);
    }
}
