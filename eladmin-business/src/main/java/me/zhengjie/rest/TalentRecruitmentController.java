package me.zhengjie.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.domain.TalentRecruitment;
import me.zhengjie.service.TalentRecruitmentService;
import me.zhengjie.service.dto.TalentRecruitmentDTO;
import me.zhengjie.service.query.TalentRecruitmentQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author wbq
* @date 2019-04-15
*/
@RestController
@RequestMapping("api")
public class TalentRecruitmentController {

    @Autowired
    private TalentRecruitmentService talentRecruitmentService;

    @Autowired
    private TalentRecruitmentQueryService talentRecruitmentQueryService;

    private static final String ENTITY_NAME = "talentRecruitment";

    @Log("查询TalentRecruitment")
    @GetMapping(value = "/talentRecruitment")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getTalentRecruitments(TalentRecruitmentDTO resources, Pageable pageable){
        return new ResponseEntity(talentRecruitmentQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增TalentRecruitment")
    @PostMapping(value = "/talentRecruitment")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody TalentRecruitment resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(talentRecruitmentService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改TalentRecruitment")
    @PutMapping(value = "/talentRecruitment")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody TalentRecruitment resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        talentRecruitmentService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除TalentRecruitment")
    @DeleteMapping(value = "/talentRecruitment/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        talentRecruitmentService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("获取TalentRecruitment")
    @GetMapping(value = "/show/getTalentRecruitment")
    public  ResponseEntity getTalentRecruitment(){
        return new ResponseEntity(talentRecruitmentService.getTalentRecruitment(),HttpStatus.OK);
    }
    @Log("获取具体的人才招聘信息通过id")
    @GetMapping(value = "/talentDetail/{id}")
    public ResponseEntity getTalentDetail(@PathVariable Long id){
        return new ResponseEntity(talentRecruitmentService.findById(id),HttpStatus.OK);
    }
}