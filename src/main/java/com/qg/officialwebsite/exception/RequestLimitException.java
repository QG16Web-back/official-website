package com.qg.officialwebsite.exception;

import com.qg.officialwebsite.enums.StateEnum;

/**
 * @author 郑俊铭
 * Date: 2018/1/22
 * Time: 19:42
 * No struggle, talent how to match the willfulness.
 * Description:
 */
public class RequestLimitException extends RecruitException {

    public RequestLimitException(StateEnum stateEnum) {
        super(stateEnum);
    }
}
