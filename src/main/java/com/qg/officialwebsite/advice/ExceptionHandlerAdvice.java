package com.qg.officialwebsite.advice;

import com.qg.officialwebsite.domain.Student;
import com.qg.officialwebsite.dto.Result;
import com.qg.officialwebsite.exception.QGOfficialWebsiteException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 郑俊铭
 * Date: 2017/12/1
 * Time: 22:26
 * No struggle, talent how to match the willfulness.
 * Description: 统一异常处理类
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {

    @ExceptionHandler(QGOfficialWebsiteException.class)
    public Result handleException(QGOfficialWebsiteException e) {
        return new Result<Student>(e.getStateEnum(), null);
    }
}
