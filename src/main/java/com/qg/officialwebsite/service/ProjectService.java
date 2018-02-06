package com.qg.officialwebsite.service;

import com.qg.officialwebsite.domain.Project;
import com.qg.officialwebsite.dto.Result;

/**
 * Created by K Lin
 * Date: 2018/2/6.
 * Time: 16:02
 * Remember to sow in the spring.
 * Description : official-website
 */
public interface ProjectService {
    /**
     * 添加项目
     * @param project
     * @return
     */
    Result addProject(Project project);

    /**
     * 分页展示项目
     *
     * @param page 页数
     * @param pageSize 一页的数量
     * @return Result结果
     */
    Result showPagingProject(Integer page, Integer pageSize);

    /**
     * 根据主键id删除项目的逻辑层接口
     *
     * @param id
     * @return Result结果
     */
    Result deleteProjectById(Integer id);
}
