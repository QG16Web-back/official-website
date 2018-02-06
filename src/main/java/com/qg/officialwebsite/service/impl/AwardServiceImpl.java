package com.qg.officialwebsite.service.impl;

import com.qg.officialwebsite.domain.Award;
import com.qg.officialwebsite.domain.AwardRepository;
import com.qg.officialwebsite.dto.Result;
import com.qg.officialwebsite.enums.StateEnum;
import com.qg.officialwebsite.exception.WebsiteHonorException;
import com.qg.officialwebsite.service.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by K Lin
 * Date: 2018/2/2.
 * Time: 21:33
 * Remember to sow in the spring.
 * Description : official-website
 */
@Service
public class AwardServiceImpl implements AwardService{
    @Autowired
    private AwardRepository awardRepository;

    @Override
    public Result addAward(Award award){

        // 最小id
        int minimumId = 0;
        // 最大id
        int maximumId = 10000;
        // 奖项名称的最大长度
        int maximumNameLength = 200;
        // 获奖项目名最大长度
        int minimumProjectLength = 200;

        if ("".equals(award.getTime()) || "".equals(award.getName()) || "".equals(award.getProject())
                || "".equals(award.getAwardId()) || award.getTime() == null || award.getName() == null
                || award.getProject() == null || award.getAwardId() == null) {
            // 参数为空
            throw new WebsiteHonorException(StateEnum.PARAM_IS_EMPTY);
        }else if (award.getAwardId() < minimumId || award.getAwardId() > maximumId) {
            // 奖项id不在范围之内
            throw new WebsiteHonorException(StateEnum.ID_OUT_OF_SIZE);
        } else if (award.getName().length() > maximumNameLength) {
            // 奖项名称过长，200个字为最大长度
            throw new WebsiteHonorException(StateEnum.AWARD_LENGTH_IS_TOO_LONG);
        } else if (award.getProject().length() > minimumProjectLength) {
            // 项目名称过长，200个字为最大长度
            throw new WebsiteHonorException(StateEnum.PROJECT_LENGTH_IS_TO_LONG);
        }
        else {
            // 一切正常
            awardRepository.save(award);
            return new Result(StateEnum.OK);
        }
    }

    @Override
    public Result showPagingAward(Integer page, Integer pageSize){
        Page<Award> awards = awardRepository.findAll(new PageRequest(page,pageSize));
        // 存放返回数据data
        Map<String, Object> map = new HashMap<>();
        // 存放所有Award的部分数据
        List<Map<String, Object>> list = new ArrayList<>();
        for (Award award : awards.getContent()) {
            // 存放单个Award需要展示展示的部分
            Map<String, Object> infoMap = new HashMap<>();
            infoMap.put("id", award.getId());
            infoMap.put("awardId", award.getAwardId());
            infoMap.put("name", award.getName());
            infoMap.put("project", award.getProject());
            infoMap.put("time", award.getTime());
            list.add(infoMap);
        }
        map.put("awards", list);
        // 总条数
        map.put("totalElements", awards.getTotalElements());
        // 总页数
        map.put("totalPages", awards.getTotalPages());
        return new Result<>(StateEnum.OK, map);
    }

    @Override
    public Result deleteAwardById(Integer id) {
        if (id == null) {
            throw new WebsiteHonorException(StateEnum.PARAM_IS_EMPTY);
        }
        if (awardRepository.exists(id) == true) {
            awardRepository.delete(id);
            return new Result(StateEnum.OK);
        }else {
            throw new WebsiteHonorException(StateEnum.PROJECT_IS_NOT_EXIST);
        }
    }
}
