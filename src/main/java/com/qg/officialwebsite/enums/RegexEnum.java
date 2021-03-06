package com.qg.officialwebsite.enums;

/**
 * @author 郑俊铭
 * Date: 2017/12/2
 * Time: 10:16
 * No struggle, talent how to match the willfulness.
 * Description: 正则表达式枚举类
 */
public enum RegexEnum {

    /**
     * 招新报名的学号的正则表达式
     */
    STUDENT_ID_REGEX_FOR_RECRUIT("^(3[1|2]1[6|7]00)\\d{4}$"),

    /**
     * 成员学号正则表达式
     */
    STUDENT_ID_REGEX("^(3[1|2][0|1][0-9]00)\\d{4}$"),

    /**
     * 手机号码正则表达式
     */
    PHONE_REGEX("^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\\d{8}$"),

    /**
     * 绩点正则表达式，允许整数，一个小数，两个小数
     */
    GPA_REGEX("^[0-9]+([.]{1}[0-9]{1,2})?$")
    ;

    /**
     * 正则表达式
     */
    private String regex;

    RegexEnum(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }
}
