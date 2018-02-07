package com.qg.officialwebsite.enums;

/**
 * @author 郑俊铭
 * Date: 2017/12/1
 * Time: 22:20
 * No struggle, talent how to match the willfulness.
 * Description: 状态码及状态信息枚举类
 */
public enum StateEnum {

    /**
     * 状态码、状态信息
     */
    STUDENT_HAS_SIGN_UP(1, "学生已经报名过"),

    STUDENT_ID_FORMAT_ERROR(3, "学号格式错误"),

    PHONE_FORMAT_ERROR(4, "你的手机号码肯定是外星的"),

    NAME_LENGTH_IS_TOO_LONG(5, "姓名太长了，超过15个字了"),

    CLASS_LENGTH_IS_TOO_LONG(7, "你的专业班级名称是我见过最长的"),

    SCORE_OUT_OF_SIZE(8, "成绩不在规定范围内"),

    C_TEST_SCORE_IS_TOO_LONG(9, "我相信你写好这个实验成绩的话，我们还能做朋友，最长五个字符哦"),

    PARAM_IS_EMPTY(10, "参数为空"),

    FAIL_ERROR(12, "你做出了挂科与不挂科之外的选择"),

    WISH_ERROR(13, "你要选择的方向这里没有哦"),

    SWAP_ERROR(14, "你的选择是是还是不是呢"),

    GPA_FORMAT_ERROR(15, "绩点格式错误"),

    DO_NOT_FIND_STUDENT(16, "没有找到该学生"),

    IP_IS_ILLEGAL(17, "IP不合法"),

    PARAM_IS_LOST(18, "缺失参数"),

    FILE_FORMAT_ERROR(30, "文件格式错误"),

    GROUP_NOT_EXIST(31, "组别不存在"),

    GROUP_HAS_EXISTED(32, "组别已存在"),

    MEMBER_HAS_EXISTED(33, "成员已存在"),

    GRADUATED_STATUS_ERROR(34, "是否毕业状态错误，应为\'未毕业\'或\'已毕业\'"),

    GRADE_ERROR(35, "年级错误"),

    MEMBER_NOT_EXIST(36, "成员不存在"),

    SILHOUETTE_TYPE_HAS_EXISTED(37, "剪影类别已存在"),

    SILHOUETTE_TYPE_NOT_EXIST(38, "剪影类别不存在"),

    SILHOUETTE_HAS_EXISTED(39, "剪影已存在"),

    SILHOUETTE_NOT_EXIST(40, "剪影不存在"),

    OK(200, "一切正常")
    ;

    /**
     * 状态码
     */
    private int state;

    /**
     * 状态信息
     */
    private String info;

    StateEnum(int state, String info) {
        this.state = state;
        this.info = info;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
