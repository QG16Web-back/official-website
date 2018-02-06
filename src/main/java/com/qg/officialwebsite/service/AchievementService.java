package com.qg.officialwebsite.service;

import com.qg.officialwebsite.domain.Achievement;
import com.qg.officialwebsite.dto.Result;

/**
 * Created by K Lin
 * Date: 2018/2/6.
 * Time: 17:21
 * Remember to sow in the spring.
 * Description : official-website
 */
public interface AchievementService {
    /**
     * 添加成就
     * @param achievement
     * @return
     */
    Result addAchievement(Achievement achievement);

    /**
     * 展示成就
     *
     * @param page 页数
     * @return Result结果
     */
    Result showPagingAchievement(Integer page);

    /**
     * 根据主键id删除成就的逻辑层接口
     *
     * @param id
     * @return Result结果
     */
    Result deleteAchievementById(Integer id);
}
