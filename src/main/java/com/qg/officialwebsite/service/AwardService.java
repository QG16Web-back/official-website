package com.qg.officialwebsite.service;

import com.qg.officialwebsite.domain.Award;
import com.qg.officialwebsite.dto.Result;

/**
 * Created by K Lin
 * Date: 2018/2/2.
 * Time: 21:29
 * Remember to sow in the spring.
 * Description : official-website
 */
public interface AwardService {

    /**
     * 添加奖项
     * @param award
     * @return
     */
    Result addAward(Award award);

    /**
     * 分页展示奖项
     *
     * @param page 页数
     * @param pageSize 一页的数量
     * @return Result结果
     */
    Result showPagingAward(Integer page, Integer pageSize);

    /**
     * 根据主键id删除奖项的逻辑层接口
     *
     * @param id
     * @return Result结果
     */
    Result deleteAwardById(Integer id);
}
