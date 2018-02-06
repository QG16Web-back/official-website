package com.qg.officialwebsite.exception;

import com.qg.officialwebsite.enums.StateEnum;

/**
 * @author 小铭
 * Date: 2018/2/6
 * Time: 19:17
 * No struggle, talent how to match the willfulness.
 * Description: 学号格式异常
 */
public class StudentIdFormatException extends QGOfficialWebsiteException {

    public StudentIdFormatException(StateEnum stateEnum) {
        super(stateEnum);
    }
}
