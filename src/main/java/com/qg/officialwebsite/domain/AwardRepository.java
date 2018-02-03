package com.qg.officialwebsite.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by K Lin
 * Date: 2018/2/2.
 * Time: 21:10
 * Remember to sow in the spring.
 * Description : official-website
 */
public interface AwardRepository extends JpaRepository<Award,Integer>{
    /**
     * 根据主键id查找奖项
     *
     * @param Id 奖项主键id
     * @return 奖项实体
     */
    Award findById(@Param("id") Integer Id);
}
