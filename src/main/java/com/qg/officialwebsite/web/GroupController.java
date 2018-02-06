package com.qg.officialwebsite.web;

import com.qg.officialwebsite.domain.Group;
import com.qg.officialwebsite.dto.Result;
import com.qg.officialwebsite.enums.StateEnum;
import com.qg.officialwebsite.exception.ParamException;
import com.qg.officialwebsite.service.impl.GroupServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 小铭
 * Date: 2018/2/6
 * Time: 15:58
 * No struggle, talent how to match the willfulness.
 * Description: 组别管理控制器
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/group")
public class GroupController {
    private static final Logger logger = LoggerFactory.getLogger(GroupController.class);

    private final GroupServiceImpl groupService;

    @Autowired
    public GroupController(GroupServiceImpl groupService) {
        this.groupService = groupService;
    }

    /**
     * 添加组别控制器
     *
     * @param group 组别实体类
     * @return Result结果
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public Result addGroup(@RequestBody Group group) {
        System.out.println(group);
        return groupService.addGroup(group);
    }

    /**
     * 删除组别
     *
     * @param map 含有groupId
     * @return Result结果
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json")
    public Result deleteGroup(@RequestBody Map<String, Integer> map) {
        System.out.println(map);
        if (!map.containsKey("groupId")) {
            logger.error("缺失参数");
            throw new ParamException(StateEnum.PARAM_IS_LOST);
        }
        return groupService.deleteGroup(map.get("groupId"));
    }

    /**
     * 更新组别
     *
     * @param group 组别实体类
     * @return Result结果
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json")
    public Result updateGroup(@RequestBody Group group) {
        System.out.println(group);
        return groupService.updateGroup(group);
    }

    @RequestMapping(value = "/show", method = RequestMethod.POST, produces = "application/json")
    public Result showGroups() {
        return groupService.showGroups();
    }
}
