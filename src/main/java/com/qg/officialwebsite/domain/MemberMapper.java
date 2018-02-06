package com.qg.officialwebsite.domain;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author 小铭
 * Date: 2018/2/6
 * Time: 18:16
 * No struggle, talent how to match the willfulness.
 * Description: 成员操作mapper
 */
@Mapper
@Component
public interface MemberMapper {

    /**
     * 根据学号查找成员
     *
     * @param studentId 学号
     * @return 成员实体类
     */
    Member selectMemberByStudentId(@Param("studentId") String studentId);

    /**
     * 添加成员
     *
     * @param member 成员实体类
     */
    void addMember(@Param("member") Member member);

    /**
     * 显示成员（前端展示）
     *
     * @param memberGrade 成员年级
     * @param groupName 组别名称
     */
    void showMember(@Param("memberGrade") String memberGrade, @Param("groupName") String groupName);
}
