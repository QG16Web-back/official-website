package com.qg.officialwebsite.exception;

import com.qg.officialwebsite.enums.StateEnum;

/**
 * Created by K Lin
 * Date: 2018/2/6.
 * Time: 15:19
 * Remember to sow in the spring.
 * Description : official-website
 */
public class WebsiteHonorException extends QGOfficialWebsiteException {

    public WebsiteHonorException(StateEnum stateEnum){
        super(stateEnum);
    }
}
