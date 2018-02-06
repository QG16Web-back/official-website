package com.qg.officialwebsite.utils;

/**
 * @author 小铭
 * Date: 2018/2/6
 * Time: 19:55
 * No struggle, talent how to match the willfulness.
 * Description: 根据学号获取相关年级的工具类
 */
public class GradeUtil {

    /**
     * 根据学号获取年级
     *
     * @param studentId 学号
     * @return 年级
     */
    public static String getGrade(String studentId) {
        String grade = null;
        if (studentId.startsWith("3104") || studentId.startsWith("3204")) {
            grade = "2004级";
        } else if (studentId.startsWith("3105") || studentId.startsWith("3205")) {
            grade = "2005级";
        } else if (studentId.startsWith("3106") || studentId.startsWith("3206")) {
            grade = "2006级";
        } else if (studentId.startsWith("3107") || studentId.startsWith("3207")) {
            grade = "2007级";
        } else if (studentId.startsWith("3108") || studentId.startsWith("3208")) {
            grade = "2008级";
        } else if (studentId.startsWith("3109") || studentId.startsWith("3209")) {
            grade = "2009级";
        } else if (studentId.startsWith("3110") || studentId.startsWith("3210")) {
            grade = "2010级";
        } else if (studentId.startsWith("3111") || studentId.startsWith("3211")) {
            grade = "2011级";
        } else if (studentId.startsWith("3112") || studentId.startsWith("3212")) {
            grade = "2012级";
        } else if (studentId.startsWith("3113") || studentId.startsWith("3213")) {
            grade = "2013级";
        } else if (studentId.startsWith("3114") || studentId.startsWith("3214")) {
            grade = "2014级";
        } else if (studentId.startsWith("3115") || studentId.startsWith("3215")) {
            grade = "2015级";
        } else if (studentId.startsWith("3116") || studentId.startsWith("3216")) {
            grade = "2016级";
        } else if (studentId.startsWith("3117") || studentId.startsWith("3217")) {
            grade = "2017级";
        }
        return grade;
    }
}
