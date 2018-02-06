package com.qg.officialwebsite.exception;

import com.qg.officialwebsite.enums.StateEnum;

/**
 * @author 小铭
 * Date: 2018/2/5
 * Time: 19:12
 * No struggle, talent how to match the willfulness.
 * Description: 参数缺失异常
 */
public class ParamLostException extends QGOfficialWebsiteException {

    public ParamLostException(StateEnum stateEnum) {
        super(stateEnum);
    }
}
