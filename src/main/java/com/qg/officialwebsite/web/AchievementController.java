package com.qg.officialwebsite.web;

import com.qg.officialwebsite.domain.Achievement;
import com.qg.officialwebsite.dto.Result;
import com.qg.officialwebsite.enums.StateEnum;
import com.qg.officialwebsite.exception.WebsiteHonorException;
import com.qg.officialwebsite.service.AchievementService;
import com.qg.officialwebsite.service.impl.AchievementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * Created by K Lin
 * Date: 2018/2/6.
 * Time: 19:56
 * Remember to sow in the spring.
 * Description : official-website
 */
@RestController
@CrossOrigin
@RequestMapping("/achievement")
public class AchievementController {
    private final AchievementService achievementService;

    @Autowired
    public AchievementController(AchievementServiceImpl achievementService){this.achievementService = achievementService;}

    @RequestMapping(value = "/add",method = RequestMethod.POST, produces = "application/json")
    public Result addAchievement(@RequestBody Achievement achievement){
        System.out.println(achievement);
        return achievementService.addAchievement(achievement);
    }

    @RequestMapping(value = "/show",method = RequestMethod.POST, produces = "application/json")
    public Result showAchievement(@RequestBody Map<String,Integer> map){
        System.out.println(map);
        String page = "page";
        if (!map.containsKey(page)){
            throw new WebsiteHonorException(StateEnum.PARAM_IS_EMPTY);
        }
        return achievementService.showPagingAchievement(map.get(page));
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST, produces = "application/json")
    public Result deleteAchievementById(@RequestBody Map<String, Integer> map){
        String id ="id";
        if (!map.containsKey(id) || map.get(id) == null) {
            // 参数缺失
            throw new WebsiteHonorException(StateEnum.PARAM_IS_LOST);
        }
        return achievementService.deleteAchievementById(map.get(id));
    }
}
