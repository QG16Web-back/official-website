package com.qg.officialwebsite.exception;

import com.qg.officialwebsite.enums.StateEnum;

/**
 * @author 小铭
 * Date: 2018/2/5
 * Time: 20:38
 * No struggle, talent how to match the willfulness.
 * Description: 文件格式异常
 */
public class FileFormatException extends QGOfficialWebsiteException {

    public FileFormatException(StateEnum stateEnum) {
        super(stateEnum);
    }
}
