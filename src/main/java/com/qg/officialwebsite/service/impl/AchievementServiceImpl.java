package com.qg.officialwebsite.service.impl;

import com.qg.officialwebsite.domain.Achievement;
import com.qg.officialwebsite.domain.AchievementRepository;
import com.qg.officialwebsite.dto.Result;
import com.qg.officialwebsite.enums.StateEnum;
import com.qg.officialwebsite.exception.WebsiteHonorException;
import com.qg.officialwebsite.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by K Lin
 * Date: 2018/2/6.
 * Time: 17:23
 * Remember to sow in the spring.
 * Description : official-website
 */
@Service
public class AchievementServiceImpl implements AchievementService {
    @Autowired
    private AchievementRepository achievementRepository;

    @Override
    public Result addAchievement(Achievement achievement){

        // 最小页数
        int minimumPage = 0;
        // 最大页数
        int maximumPage = 20;
        // 文章标题的最大长度
        int maximumTitleLength = 50;
        // 文章主体的最大长度
        int minimumBodyLength = 20000;
        //用于判断该页面是否已有文章，有则做更新操作
        Achievement achievementReplace = achievementRepository.findAchievementByPage(achievement.getPage());

        if ("".equals(achievement.getPage()) || "".equals(achievement.getTitle()) ||
                "".equals(achievement.getBody()) || achievement.getPage() == null ||
                achievement.getTitle() == null || achievement.getBody() == null) {
            // 参数为空
            throw new WebsiteHonorException(StateEnum.PARAM_IS_EMPTY);
        }else if (achievement.getPage() < minimumPage || achievement.getPage() > maximumPage) {
            // 页数 page 不在范围之内
            throw new WebsiteHonorException(StateEnum.PAGE_OUT_OF_SIZE);
        } else if (achievement.getTitle().length() > maximumTitleLength) {
            // 文章标题过长，50个字为最大长度
            throw new WebsiteHonorException(StateEnum.TITLE_LENGTH_IS_TOO_LONG);
        } else if (achievement.getBody().length() > minimumBodyLength) {
            // 项目名称过长，最大长度字串为20000
            throw new WebsiteHonorException(StateEnum.BODY_LENGTH_IS_TO_LONG);
        } else {
            // 一切正常
            if (achievementReplace != null){
                //将已存在的文章的主键 id 赋值给新的文章作主键 id，在 save 时会自动更新
                achievement.setId(achievementReplace.getId());
            }
            achievementRepository.save(achievement);
            return new Result(StateEnum.OK);
        }
    }

    @Override
    public Result showPagingAchievement(Integer page){
        if (page == null){
            throw new WebsiteHonorException(StateEnum.PARAM_IS_EMPTY);
        }
        Achievement achievement;
        achievement = achievementRepository.findAchievementByPage(page);
        Map<String, Object> map = new HashMap<>(3);
        if (achievement != null){
            map.put("id",achievement.getId());
            map.put("title",achievement.getTitle());
            map.put("body",achievement.getBody());
            return new Result<>(StateEnum.OK , map);
        }else {
            throw new WebsiteHonorException(StateEnum.ACHIEVEMENT_NOT_EXIST);
        }
    }

    @Override
    public Result deleteAchievementById(Integer id) {
        if (id == null) {
            throw new WebsiteHonorException(StateEnum.PARAM_IS_EMPTY);
        }
        if (achievementRepository.exists(id) == true) {
            achievementRepository.delete(id);
            return new Result(StateEnum.OK);
        }else {
            throw new WebsiteHonorException(StateEnum.ACHIEVEMENT_NOT_EXIST);
        }
    }
}
