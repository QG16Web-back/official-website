package com.qg.officialwebsite.config;

import com.qg.officialwebsite.OfficialWebsiteApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

/**
 * @author 小铭
 */
@Configuration
public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(OfficialWebsiteApplication.class);
    }
}
