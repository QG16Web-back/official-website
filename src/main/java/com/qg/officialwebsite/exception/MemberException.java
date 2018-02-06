package com.qg.officialwebsite.exception;

import com.qg.officialwebsite.enums.StateEnum;

/**
 * @author 小铭
 * Date: 2018/2/6
 * Time: 19:52
 * No struggle, talent how to match the willfulness.
 * Description: 成员相关异常类
 */
public class MemberException extends QGOfficialWebsiteException {

    public MemberException(StateEnum stateEnum) {
        super(stateEnum);
    }
}
