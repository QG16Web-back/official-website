package com.qg.officialwebsite.domain;

/**
 * @author 小铭
 * Date: 2018/2/4
 * Time: 22:26
 * No struggle, talent how to match the willfulness.
 * Description: 奖项实体类
 */
public class Prize {

    /**
     * 奖项ID
     */
    private int prizeId;

    /**
     * 奖项名称
     */
    private String prizeName;

    /**
     * 奖项所属成员
     */
    private Member member;

    public Prize() {
    }

    public int getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(int prizeId) {
        this.prizeId = prizeId;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "Prize{" +
                "prizeId=" + prizeId +
                ", prizeName='" + prizeName + '\'' +
                ", member=" + member +
                '}';
    }
}
