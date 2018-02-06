package com.qg.officialwebsite.exception;

import com.qg.officialwebsite.enums.StateEnum;

/**
 * @author 小铭
 * Date: 2018/2/6
 * Time: 11:01
 * No struggle, talent how to match the willfulness.
 * Description: 参数为空异常类
 */
public class ParamEmptyException extends QGOfficialWebsiteException {

    public ParamEmptyException(StateEnum stateEnum) {
        super(stateEnum);
    }
}
