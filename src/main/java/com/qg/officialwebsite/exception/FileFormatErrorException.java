package com.qg.officialwebsite.exception;

import com.qg.officialwebsite.enums.StateEnum;

/**
 * @author 小铭
 * Date: 2018/2/5
 * Time: 20:38
 * No struggle, talent how to match the willfulness.
 * Description: 文件格式错误异常
 */
public class FileFormatErrorException extends QGOfficialWebsiteException {

    public FileFormatErrorException(StateEnum stateEnum) {
        super(stateEnum);
    }
}
