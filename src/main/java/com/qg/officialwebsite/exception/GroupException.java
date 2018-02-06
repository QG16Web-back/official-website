package com.qg.officialwebsite.exception;

import com.qg.officialwebsite.enums.StateEnum;

/**
 * @author 小铭
 * Date: 2018/2/6
 * Time: 16:14
 * No struggle, talent how to match the willfulness.
 * Description: 组别异常
 */
public class GroupException extends QGOfficialWebsiteException {

    public GroupException(StateEnum stateEnum) {
        super(stateEnum);
    }
}
