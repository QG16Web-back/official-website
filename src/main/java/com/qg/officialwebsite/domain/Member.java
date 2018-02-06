package com.qg.officialwebsite.domain;

import java.util.List;

/**
 * @author 小铭
 * Date: 2018/2/1
 * Time: 20:29
 * No struggle, talent how to match the willfulness.
 * Description: 成员实体类
 */
public class Member {

    /**
     * 成员ID
     */
    private int memberId;

    /**
     * 成员姓名
     */
    private String memberName;

    /**
     * 成员学号
     */
    private String studentId;

    /**
     * 成员照片路径
     */
    private String memberPhotoPath;

    /**
     * 成员班级
     */
    private String memberClass;

    /**
     * 成员年级
     */
    private String memberGrade;

    /**
     * 成员座右铭
     */
    private String motto;

    /**
     * 是否已毕业
     */
    private String haveGraduated;

    /**
     * 毕业去向
     */
    private String afterGraduation;

    /**
     * 成员组别
     */
    private Group group;

    /**
     * 成员获得的奖项
     */
    private List<Prize> prizes;

    public Member() {
    }

    public Member(String memberName, String studentId, String memberClass, String motto, Group group, String haveGraduated,
                  String afterGraduation) {
        this.memberName = memberName;
        this.studentId = studentId;
        this.memberClass = memberClass;
        this.motto = motto;
        this.group = group;
        this.haveGraduated = haveGraduated;
        this.afterGraduation = afterGraduation;
    }

    public Member(String memberName, String studentId, String memberPhotoPath, String memberClass, String motto, Group group, String haveGraduated,
                  String afterGraduation) {
        this(memberName, studentId, memberClass, motto, group, haveGraduated, afterGraduation);
        this.memberPhotoPath = memberPhotoPath;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getMemberPhotoPath() {
        return memberPhotoPath;
    }

    public void setMemberPhotoPath(String memberPhotoPath) {
        this.memberPhotoPath = memberPhotoPath;
    }

    public String getMemberClass() {
        return memberClass;
    }

    public void setMemberClass(String memberClass) {
        this.memberClass = memberClass;
    }

    public String getMemberGrade() {
        return memberGrade;
    }

    public void setMemberGrade(String memberGrade) {
        this.memberGrade = memberGrade;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getHaveGraduated() {
        return haveGraduated;
    }

    public void setHaveGraduated(String haveGraduated) {
        this.haveGraduated = haveGraduated;
    }

    public String getAfterGraduation() {
        return afterGraduation;
    }

    public void setAfterGraduation(String afterGraduation) {
        this.afterGraduation = afterGraduation;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Prize> getPrizes() {
        return prizes;
    }

    public void setPrizes(List<Prize> prizes) {
        this.prizes = prizes;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", memberName='" + memberName + '\'' +
                ", studentId='" + studentId + '\'' +
                ", memberPhotoPath='" + memberPhotoPath + '\'' +
                ", memberClass='" + memberClass + '\'' +
                ", memberGrade='" + memberGrade + '\'' +
                ", motto='" + motto + '\'' +
                ", haveGraduated='" + haveGraduated + '\'' +
                ", afterGraduation='" + afterGraduation + '\'' +
                ", group=" + group +
                ", prizes=" + prizes +
                '}';
    }
}
