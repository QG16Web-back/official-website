package com.qg.officialwebsite.exception;

import com.qg.officialwebsite.enums.StateEnum;

/**
 * @author 郑俊铭
 * Date: 2017/12/8
 * Time: 19:54
 * No struggle, talent how to match the willfulness.
 * Description: QG官网自定义异常，该异常为自定义异常的父类
 */
public class QGOfficialWebsiteException extends RuntimeException {
    private StateEnum stateEnum;

    public QGOfficialWebsiteException(StateEnum stateEnum) {
        super(stateEnum.getInfo());
        this.stateEnum = stateEnum;
    }

    public StateEnum getStateEnum() {
        return stateEnum;
    }
}
