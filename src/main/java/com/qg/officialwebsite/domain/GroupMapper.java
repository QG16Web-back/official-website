package com.qg.officialwebsite.domain;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 小铭
 * Date: 2018/2/5
 * Time: 12:48
 * No struggle, talent how to match the willfulness.
 * Description: 组别操作mapper
 */

@Mapper
@Component
public interface GroupMapper {
    /**
     * 根据组别查找组别
     *
     * @param groupName 组名
     * @return 组别实体类
     */
    Group selectGroupByGroupName(@Param("groupName") String groupName);

    /**
     * 添加组别
     *
     * @param group 组别实体类
     */
    void addGroup(@Param("group") Group group);

    /**
     * 根据ID删除组别
     *
     * @param groupId 组别ID
     */
    void deleteGroupById(@Param("groupId") Integer groupId);

    /**
     * 根据ID查找组别
     *
     * @param groupId 组别ID
     * @return 组别实体类
     */
    Group selectGroupById(@Param("groupId") Integer groupId);

    /**
     * 更新组别
     *
     * @param group 组别实体类
     */
    void updateGroup(@Param("group") Group group);

    /**
     * 展示所有组别
     *
     * @return 组别集合
     */
    List<Group> showGroups();
}
