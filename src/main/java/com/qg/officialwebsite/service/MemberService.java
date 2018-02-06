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
     * 添加成员
     *
     * @param member 成员实体类
     * @param prizeName 奖项字符串
     * @return Result结果
     */
    Result addMember(Member member, String prizeName);

    /**
     * 显示成员（前端展示）
     *
     * @param memberGrade 成员年级
     * @param groupName 组别名称
     * @return Result结果
     */
    Result showMember(String memberGrade, String groupName);
}
