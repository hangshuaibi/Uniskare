package com.uniskare.eureka_skill.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.uniskare.eureka_skill.controller.Response.BaseResponse;
import com.uniskare.eureka_skill.controller.Response.Code;
import com.uniskare.eureka_skill.controller.Response.ResponseMessage;
import com.uniskare.eureka_skill.dto.SkillDTO;
import com.uniskare.eureka_skill.entity.Skill;
import com.uniskare.eureka_skill.repository.SkillRepo;
import com.uniskare.eureka_skill.service.SkillService;
import com.uniskare.eureka_skill.service.StarSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
/**
 * @author : Bhy
 * @description ：
 */
@RestController
@RequestMapping(path="/skill")
public class SkillController {


    @Autowired
    private SkillService skillService;

    @Autowired
    private StarSkillService starSkillService;

    @GetMapping(path="/all")
    public BaseResponse getAllSkills(@RequestParam("page") int page) {

        return skillService.findAll(page);
    }

    @PostMapping(path="/insert")
    public BaseResponse insertSkill(@RequestBody Skill skill) {
        return skillService.save(skill);
    }
    //原api未指明id，保持一样。就是说此时提交的skill必须有id
    @PutMapping(path="/update")
    public BaseResponse updateSkill(@RequestBody Skill skill) {
        return skillService.updateSkill(skill);
    }
    @DeleteMapping("/delete/{skillId}")
    public BaseResponse deleteSkill(@PathVariable(value = "skillId") int skillId) {
        return skillService.deleteById(skillId);
    }


    @GetMapping("all/{fullType}")
    public BaseResponse getSkillByFullType(@PathVariable("fullType") String fullType,@RequestParam("page") int page) {
        return skillService.findByFullType(fullType,page);
    }

    @GetMapping("all/{fullType}/{subType})")
    public BaseResponse getSkillBySubType(@PathVariable("fullType") String fullType, @PathVariable("subType") String subtype,@RequestParam("page") int page) {
        return skillService.findByFullTypeAndSubtype(fullType,subtype,page);
    }
    //未使用dto
    @GetMapping("/{skillId}")
    public BaseResponse getSkillByskillId(@PathVariable("skillId") int skillId) {
        return skillService.findById(skillId);
    }

    //保持和原来接口一样
    @GetMapping("/search")
    public BaseResponse searchSkills(@RequestParam("page") int page,@RequestParam("name") String name) {
        return skillService.searchSkillByTitle(name,page);
    }

    //以上是对技能的增删改查，以下是对收藏技能的增删改查
    @PostMapping("/{userId}/star/{skillId}")
    public BaseResponse starSkill(@PathVariable("userId") String userId,@PathVariable("skillId") int skillId) {
        return starSkillService.starSkill(userId,skillId);
    }
    @PostMapping("/{userId}/unstar/{skillId}")
    public BaseResponse unstarSkill(@PathVariable("userId") String userId,@PathVariable("skillId") int skillId) {
        return starSkillService.unstarSkill(userId,skillId);
    }
    //未使用dto，以及这个接口很奇怪
    @GetMapping("/star/skill/{id}")
    public BaseResponse getStarSkill(@RequestParam("page") int page,@PathVariable("id") String userId) {
        return starSkillService.getStarSkill(page,userId);
    }
    //也是原来user系统下的，
    //这个接口路径也很奇怪，用了dto
    @GetMapping("/information/{id}/skills")
    public BaseResponse getSkillsByUserId(@PathVariable("id") String userId,@RequestParam("page") int page) {
        return skillService.findByUserId(userId,page);
    }


}
