package com.qg.officialwebsite.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by K Lin
 * Date: 2018/2/6.
 * Time: 17:19
 * Remember to sow in the spring.
 * Description : official-website
 */
public interface AchievementRepository extends JpaRepository<Achievement,Integer> {
    /**
     * 根据页码查找成就
     *
     * @param page 页码
     * @return 成就实体
     */
    Achievement findAchievementByPage(@Param("page") Integer page);
}
