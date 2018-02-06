package com.qg.officialwebsite.web;

import com.qg.officialwebsite.domain.Award;
import com.qg.officialwebsite.dto.Result;
import com.qg.officialwebsite.enums.StateEnum;
import com.qg.officialwebsite.exception.WebsiteHonorException;
import com.qg.officialwebsite.service.impl.AwardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by K Lin
 * Date: 2018/2/2.
 * Time: 21:06
 * Remember to sow in the spring.
 * Description : official-website
 */
@RestController
@CrossOrigin
@RequestMapping("/award")
public class AwardController {
    private final AwardServiceImpl awardService;

    @Autowired
    public AwardController(AwardServiceImpl awardService){this.awardService = awardService;}
    /**
     * 添加奖项
     * @param award
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST, produces = "application/json")
    public Result addAward(@RequestBody Award award){
        System.out.println(award);
        return awardService.addAward(award);
    }

    /**
     * 分页展示奖项
     *
     * @param map 当前所在页数，pageSize一页的数量
     * @return Result结果
     */
    @RequestMapping(value = "/show", method = RequestMethod.POST, produces = "application/json")
    public Result showPagingAward(@RequestBody Map<String, Integer> map) {
        System.out.println(map);
        String page = "page";
        String pageSize = "pageSize";
        if (!map.containsKey(page) || !map.containsKey(pageSize)) {
            // 参数缺失
            throw new WebsiteHonorException(StateEnum.PARAM_IS_LOST);
        }
        return awardService.showPagingAward(map.get("page") - 1, map.get("pageSize"));
    }

    /**
     * 根据主键id删除奖项
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
        return awardService.deleteAwardById( map.get(id));
    }
}
