package com.qg.officialwebsite.service.impl;

import com.qg.officialwebsite.domain.GroupMapper;
import com.qg.officialwebsite.domain.Member;
import com.qg.officialwebsite.domain.MemberMapper;
import com.qg.officialwebsite.domain.Prize;
import com.qg.officialwebsite.domain.PrizeMapper;
import com.qg.officialwebsite.dto.Result;
import com.qg.officialwebsite.enums.StateEnum;
import com.qg.officialwebsite.exception.GroupException;
import com.qg.officialwebsite.exception.MemberException;
import com.qg.officialwebsite.exception.ParamException;
import com.qg.officialwebsite.service.MemberService;
import com.qg.officialwebsite.utils.GradeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 小铭
 * Date: 2018/2/5
 * Time: 15:41
 * No struggle, talent how to match the willfulness.
 * Description: 成员展示逻辑层实现类
 */
@Service
public class MemberServiceImpl implements MemberService {

    private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    private final MemberMapper memberMapper;
    private final PrizeMapper prizeMapper;
    private final GroupMapper groupMapper;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper, PrizeMapper prizeMapper, GroupMapper groupMapper) {
        this.memberMapper = memberMapper;
        this.prizeMapper = prizeMapper;
        this.groupMapper = groupMapper;
    }

    /**
     * 修改或添加成员
     *
     * @param member 成员实体类
     * @param prizeName 奖项字符串
     * @param add 是否为添加新成员
     * @return Result结果
     */
    @Override
    public Result addOrUpdateMember(Member member, String prizeName, boolean add) {
        // 获取年级
        member.setMemberGrade(GradeUtil.getGrade(member.getStudentId()));
        if (add) {
            // 添加成员
            memberMapper.addMember(member);
        } else {
            // 修改成员
            memberMapper.updateMember(member);
        }

        System.out.println(member);
        // 添加成员奖项
        if (prizeName != null) {
            String[] prizeArray = prizeName.split("#");
            List<Prize> prizes = new ArrayList<>();
            for (String prizeString : prizeArray) {
                Prize prize = new Prize();
                prize.setPrizeName(prizeString);
                prizes.add(prize);
            }
            prizeMapper.addPrize(prizes, member.getMemberId());
        }
        return new Result(StateEnum.OK);
    }

    /**
     * 删除成员
     *
     * @param memberId 成员ID
     * @return Result
     */
    @Override
    public Result deleteMember(Integer memberId) {

        if ("".equals(String.valueOf(memberId))) {
            logger.warn("参数为空");
            throw new ParamException(StateEnum.PARAM_IS_EMPTY);
        }

        if (memberMapper.selectMemberById(memberId) == null) {
            logger.warn("不存在该成员");
            throw new MemberException(StateEnum.MEMBER_NOT_EXIST);
        }
        memberMapper.deleteMember(memberId);
        return new Result(StateEnum.OK);
    }

    /**
     * 搜索成员
     *
     * @param studentId 学生ID
     * @return Result结果
     */
    @Override
    public Result selectMember(String studentId) {
        if (studentId == null || "".equals(studentId)) {
            logger.warn("参数为空");
            throw new ParamException(StateEnum.PARAM_IS_EMPTY);
        }
        Member member = memberMapper.selectMemberByStudentId(studentId);
        if (null == member) {
            logger.warn("不存在该成员");
            throw new MemberException(StateEnum.MEMBER_NOT_EXIST);
        }
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>(10);
        map.put("memberName", member.getMemberName());
        map.put("memberClass", member.getMemberClass());
        map.put("memberGrade", member.getMemberGrade());
        map.put("motto", member.getMotto());
        map.put("memberPhotoPath", member.getMemberPhotoPath());
        map.put("afterGraduation", member.getAfterGraduation());
        map.put("studentId", member.getStudentId());
        map.put("haveGraduated", member.getHaveGraduated());
        map.put("memberId", member.getMemberId());
        List<String> prizeList = new ArrayList<>();
        for (Prize prize : member.getPrizes()) {
            prizeList.add(prize.getPrizeName());
        }
        map.put("prizes", prizeList);
        list.add(map);
        return new Result<>(StateEnum.OK, list);
    }

    /**
     * 显示成员
     *
     * @param memberGrade 成员年级
     * @param groupName 组别名称
     * @param isFrontEnd 是否是前端显示
     * @return Result结果
     */
    @Override
    public Result showMember(String memberGrade, String groupName, boolean isFrontEnd) {
        if (groupMapper.selectGroupByGroupName(groupName) == null) {
            logger.warn("组别不存在");
            throw new GroupException(StateEnum.GROUP_NOT_EXIST);
        }

        int grade;
        try {
            grade = Integer.parseInt(memberGrade.substring(0, memberGrade.length() - 1));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.warn("年级错误");
            throw new MemberException(StateEnum.GRADE_ERROR);
        }
        if (!"级".equals(memberGrade.substring(memberGrade.length() - 1)) || grade < 2004 || grade > 2017) {
            logger.warn("年级错误");
            throw new MemberException(StateEnum.GRADE_ERROR);
        }

        List<Member> members = memberMapper.showMember(memberGrade, groupName);

        Map<String, List<Map<String, Object>>> memberMap = new HashMap<>(1);
        List<Map<String, Object>> list = new ArrayList<>();

        for (Member member : members) {
            Map<String, Object> map = new HashMap<>(8);
            map.put("memberName", member.getMemberName());
            map.put("memberClass", member.getMemberClass());
            map.put("motto", member.getMotto());
            map.put("memberPhotoPath", member.getMemberPhotoPath());
            map.put("afterGraduation", member.getAfterGraduation());
            List<String> prizeList = new ArrayList<>();
            for (Prize prize : member.getPrizes()) {
                prizeList.add(prize.getPrizeName());
            }
            map.put("prizes", prizeList);
            if (!isFrontEnd) {
                map.put("studentId", member.getStudentId());
                map.put("haveGraduated", member.getHaveGraduated());
                map.put("memberId", member.getMemberId());
            }
            list.add(map);
        }
        memberMap.put("members", list);
        return new Result<>(StateEnum.OK, memberMap);
    }

}
