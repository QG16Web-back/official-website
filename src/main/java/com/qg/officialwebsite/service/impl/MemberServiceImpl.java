package com.qg.officialwebsite.service.impl;

import com.qg.officialwebsite.domain.Member;
import com.qg.officialwebsite.domain.MemberMapper;
import com.qg.officialwebsite.domain.Prize;
import com.qg.officialwebsite.domain.PrizeMapper;
import com.qg.officialwebsite.dto.Result;
import com.qg.officialwebsite.enums.StateEnum;
import com.qg.officialwebsite.service.MemberService;
import com.qg.officialwebsite.utils.GradeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper, PrizeMapper prizeMapper) {
        this.memberMapper = memberMapper;
        this.prizeMapper = prizeMapper;
    }

    /**
     * 添加成员
     *
     * @param member 成员实体类
     * @param prizeName 奖项字符串
     * @return Result结果
     */
    @Override
    public Result addMember(Member member, String prizeName) {
        // 获取年级
        member.setMemberGrade(GradeUtil.getGrade(member.getStudentId()));
        // 添加成员
        memberMapper.addMember(member);
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

    @Override
    public Result showMember(String memberGrade, String groupName) {
        return null;
    }
}
