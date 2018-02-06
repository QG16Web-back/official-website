package com.qg.officialwebsite.web;

import com.qg.officialwebsite.domain.Project;
import com.qg.officialwebsite.dto.Result;
import com.qg.officialwebsite.enums.StateEnum;
import com.qg.officialwebsite.exception.WebsiteHonorException;
import com.qg.officialwebsite.service.impl.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by K Lin
 * Date: 2018/2/6.
 * Time: 20:23
 * Remember to sow in the spring.
 * Description : official-website
 */
@RestController
@CrossOrigin
@RequestMapping("/project")
public class ProjectController {
    private final ProjectServiceImpl projectService;

    @Autowired
    public ProjectController(ProjectServiceImpl projectService){this.projectService = projectService;}

    /**
     * 添加项目
     * @param project
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST, produces = "application/json")
    public Result addProject(@RequestBody Project project){
        System.out.println(project);
        return projectService.addProject(project);
    }

    /**
     * 分页展示项目
     *
     * @param map 当前所在页数，pageSize一页的数量
     * @return Result结果
     */
    @RequestMapping(value = "/show", method = RequestMethod.POST, produces = "application/json")
    public Result showPagingAward(@RequestBody Map<String, Integer> map) {
        String page = "page";
        String pageSize = "pageSize";
        if (!map.containsKey(page) || !map.containsKey(pageSize)) {
            // 参数缺失
            throw new WebsiteHonorException(StateEnum.PARAM_IS_LOST);
        }
        return projectService.showPagingProject(map.get("page") - 1, map.get("pageSize"));
    }

    /**
     * 根据主键id删除项目
     *
     * @param map
     * @return Result结果
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json")
    public Result deleteStudentByStudentId(@RequestBody Map<String, Integer> map) {
        String id ="id";
        if (!map.containsKey(id) || map.get(id) == null) {
            // 参数缺失
            throw new WebsiteHonorException(StateEnum.PARAM_IS_LOST);
        }
        return projectService.deleteProjectById( map.get(id));
    }
}
