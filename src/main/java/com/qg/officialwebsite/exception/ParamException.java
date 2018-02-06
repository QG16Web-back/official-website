package com.qg.officialwebsite.exception;

import com.qg.officialwebsite.enums.StateEnum;

/**
 * @author 小铭
 * Date: 2018/2/6
 * Time: 11:01
 * No struggle, talent how to match the willfulness.
 * Description: 参数异常类
 */
public class ParamException extends QGOfficialWebsiteException {

    public ParamException(StateEnum stateEnum) {
        super(stateEnum);
    }
}
