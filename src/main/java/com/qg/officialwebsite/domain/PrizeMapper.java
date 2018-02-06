package com.qg.officialwebsite.domain;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 小铭
 * Date: 2018/2/6
 * Time: 21:47
 * No struggle, talent how to match the willfulness.
 * Description: 奖项操作Mapper
 */
@Mapper
@Component
public interface PrizeMapper {

    /**
     * 插入奖项信息
     *
     * @param prizes 奖项集合
     * @param memberId 成员ID
     */
    void addPrize(@Param("prizes") List<Prize> prizes, @Param("memberId") int memberId);
}
