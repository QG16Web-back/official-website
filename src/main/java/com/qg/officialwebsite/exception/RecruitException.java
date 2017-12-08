package com.qg.officialwebsite.exception;

import com.qg.officialwebsite.enums.StateEnum;

/**
 * @author 郑俊铭
 * Date: 2017/12/1
 * Time: 22:30
 * No struggle, talent how to match the willfulness.
 * Description: QG招新网站自定义异常
 */
public class RecruitException extends QGOfficialWebsiteException {

    public RecruitException(StateEnum stateEnum) {
        super(stateEnum);
    }
}
