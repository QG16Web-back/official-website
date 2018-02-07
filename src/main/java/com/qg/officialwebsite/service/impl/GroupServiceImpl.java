package com.qg.officialwebsite.service.impl;

import com.qg.officialwebsite.domain.Group;
import com.qg.officialwebsite.domain.GroupMapper;
import com.qg.officialwebsite.dto.Result;
import com.qg.officialwebsite.enums.StateEnum;
import com.qg.officialwebsite.exception.GroupException;
import com.qg.officialwebsite.exception.ParamException;
import com.qg.officialwebsite.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 小铭
 * Date: 2018/2/6
 * Time: 16:00
 * No struggle, talent how to match the willfulness.
 * Description: 组别管理逻辑层实现类
 */
@Service
public class GroupServiceImpl implements GroupService {
    private static final Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);

    private final GroupMapper groupMapper;

    @Autowired
    public GroupServiceImpl(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

    /**
     * 添加组别
     *
     * @param group 组别实体类
     * @return Result结果
     */
    @Override
    public Result addGroup(Group group) {
        if (group.getGroupName() == null || "".equals(group.getGroupName())) {
            logger.warn("组别名参数为空");
            throw new ParamException(StateEnum.PARAM_IS_EMPTY);
        } else if (groupMapper.selectGroupByGroupName(group.getGroupName()) != null) {
            logger.warn("组别名已经存在");
            throw new GroupException(StateEnum.GROUP_HAS_EXISTED);
        } else  {
            groupMapper.addGroup(group);
            logger.info("一切正常");
        }
        return new Result(StateEnum.OK);
    }

    /**
     * 删除组别
     *
     * @param groupId 组别ID
     * @return Result结果
     */
    @Override
    public Result deleteGroup(Integer groupId) {
        if (null == groupMapper.selectGroupById(groupId)) {
            logger.warn("组别不存在");
            throw new GroupException(StateEnum.GROUP_NOT_EXIST);
        }
        groupMapper.deleteGroupById(groupId);
        return new Result(StateEnum.OK);
    }

    /**
     * 更新组别
     *
     * @param group 组别实体类
     * @return Result结果
     */
    @Override
    public Result updateGroup(Group group) {
        if (null == groupMapper.selectGroupById(group.getGroupId())) {
            logger.warn("组别不存在");
            throw new GroupException(StateEnum.GROUP_NOT_EXIST);
        }
        if (group.getGroupName() == null || "".equals(group.getGroupName())) {
            logger.warn("组别名参数为空");
            throw new ParamException(StateEnum.PARAM_IS_EMPTY);
        }
        groupMapper.updateGroup(group);
        return new Result(StateEnum.OK);
    }

    /**
     * 展示所有的组别
     *
     * @return Result结果
     */
    @Override
    public Result showGroups() {
        List<Group> groups = groupMapper.showGroups();
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, List<Map<String, Object>>> groupMap = new HashMap<>(1);
        for (Group group : groups) {
            Map<String, Object> map = new HashMap<>(3);
            map.put("groupDescription", group.getGroupDescription());
            map.put("groupName", group.getGroupName());
            map.put("groupId", group.getGroupId());
            list.add(map);
        }
        groupMap.put("groups", list);
        return new Result<>(StateEnum.OK, groupMap);
    }
}
