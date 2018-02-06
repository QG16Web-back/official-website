package com.qg.officialwebsite.service.impl;

import com.qg.officialwebsite.domain.Project;
import com.qg.officialwebsite.domain.ProjectRepository;
import com.qg.officialwebsite.dto.Result;
import com.qg.officialwebsite.enums.StateEnum;
import com.qg.officialwebsite.exception.WebsiteHonorException;
import com.qg.officialwebsite.service.ProjectService;
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
 * Date: 2018/2/6.
 * Time: 16:04
 * Remember to sow in the spring.
 * Description : official-website
 */
@Service
public class ProjectServiceImpl implements ProjectService{
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Result addProject(Project project){

        // 最小id
        int minimumId = 0;
        // 最大id
        int maximumId = 10000;
        // 项目名称的最大长度
        int maximumNameLength = 200;

        if ("".equals(project.getProjectid()) || "".equals(project.getName())  ||
                project.getProjectid() == null || project.getName() == null) {
            // 参数为空
            throw new WebsiteHonorException(StateEnum.PARAM_IS_EMPTY);
        }else if (project.getProjectid() < minimumId || project.getProjectid() > maximumId) {
            // 项目id不在范围之内
            throw new WebsiteHonorException(StateEnum.ID_OUT_OF_SIZE);
        } else if (project.getName().length() > maximumNameLength) {
            // 项目名称过长，200个字为最大长度
            throw new WebsiteHonorException(StateEnum.PROJECT_LENGTH_IS_TO_LONG);
        }
        else {
            // 一切正常
            projectRepository.save(project);
            return new Result(StateEnum.OK);
        }
    }

    @Override
    public Result showPagingProject(Integer page, Integer pageSize){
        Page<Project> projects = projectRepository.findAll(new PageRequest(page,pageSize));
        // 存放返回数据data
        Map<String, Object> map = new HashMap<>();
        // 存放所有Project的数据
        List<Map<String, Object>> list = new ArrayList<>();
        for (Project project : projects.getContent()) {
            // 存放单个Award需要展示展示的部分
            Map<String, Object> infoMap = new HashMap<>();
            infoMap.put("id", project.getId());
            infoMap.put("projectid", project.getProjectid());
            infoMap.put("name", project.getName());
            list.add(infoMap);
        }
        map.put("projects", list);
        // 总条数
        map.put("totalElements", projects.getTotalElements());
        // 总页数
        map.put("totalPages", projects.getTotalPages());
        return new Result<>(StateEnum.OK, map);
    }

    @Override
    public Result deleteProjectById(Integer id) {
        if (id == null) {
            throw new WebsiteHonorException(StateEnum.PARAM_IS_EMPTY);
        }
        if (projectRepository.exists(id) == true) {
            projectRepository.delete(id);
            return new Result(StateEnum.OK);
        } else {
            throw new WebsiteHonorException(StateEnum.PROJECT_IS_NOT_EXIST);
        }
    }
}
