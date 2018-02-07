package com.qg.officialwebsite.service;

import com.qg.officialwebsite.domain.Group;
import com.qg.officialwebsite.domain.Member;
import com.qg.officialwebsite.dto.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author 小铭
 * Date: 2018/2/5
 * Time: 15:40
 * No struggle, talent how to match the willfulness.
 * Description: 成员展示逻辑层接口
 */
public interface MemberService {

    /**
     * 显示成员
     *
     * @param memberGrade 成员年级
     * @param groupName 组别名称
     * @param isFrontEnd 是否是前端显示
     * @return Result结果
     */
    Result showMember(String memberGrade, String groupName, boolean isFrontEnd);

    /**
     * 修改或添加成员
     *
     * @param member 成员实体类
     * @param prizeName 奖项字符串
     * @param add 是否为添加新成员
     * @return Result结果
     */
    Result addOrUpdateMember(Member member, String prizeName, boolean add);

    /**
     * 删除成员
     *
     * @param memberId 成员ID
     * @return Result
     */
    Result deleteMember(Integer memberId);

    /**
     * 搜索成员
     *
     * @param studentId 学生ID
     * @return Result结果
     */
    Result selectMember(String studentId);
}
