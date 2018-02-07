package com.qg.officialwebsite.exception;

import com.qg.officialwebsite.enums.StateEnum;

/**
 * @author 小铭
 * Date: 2018/2/7
 * Time: 20:05
 * No struggle, talent how to match the willfulness.
 * Description: 剪影相关异常
 */
public class SilhouetteException extends QGOfficialWebsiteException {

    public SilhouetteException(StateEnum stateEnum) {
        super(stateEnum);
    }
}
