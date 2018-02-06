package com.qg.officialwebsite.service;

import com.qg.officialwebsite.domain.Group;
import com.qg.officialwebsite.dto.Result;

/**
 * @author 小铭
 * Date: 2018/2/6
 * Time: 15:59
 * No struggle, talent how to match the willfulness.
 * Description: 组别管理逻辑层接口
 */
public interface GroupService {
    /**
     * 添加组别
     *
     * @param group 组别实体类
     * @return Result结果
     */
    Result addGroup(Group group);

    /**
     * 删除组别
     *
     * @param groupId 组别ID
     * @return Result结果
     */
    Result deleteGroup(Integer groupId);

    /**
     * 更新组别
     *
     * @param group 组别实体类
     * @return Result结果
     */
    Result updateGroup(Group group);

    /**
     * 展示所有的组别
     *
     * @return Result结果
     */
    Result showGroups();
}
