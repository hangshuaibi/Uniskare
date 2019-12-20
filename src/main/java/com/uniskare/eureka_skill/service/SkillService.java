package com.uniskare.eureka_skill.service;

import com.uniskare.eureka_skill.controller.Response.BaseResponse;
import com.uniskare.eureka_skill.entity.Skill;

import java.util.Optional;
/**
 * @author : Bhy
 * @description ：
 */
public interface SkillService {
    public BaseResponse save(Skill skill);
    public BaseResponse findAll(int page);
    public Optional<Skill> findById(int skillId);
    public BaseResponse deleteById(int skillId);
    public BaseResponse updateSkill(Skill skill);
    public BaseResponse findByFullType(String fullType, int page);
    public BaseResponse findByFullTypeAndSubtype(String fullType, String subtype, int page);
}
