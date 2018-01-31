package com.qg.officialwebsite.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author 郑俊铭
 * Date: 2017/12/11
 * Time: 21:35
 * No struggle, talent how to match the willfulness.
 * Description: 自定义静态文件目录映射配置
 */
@Configuration
public class SourcesWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/word/**").addResourceLocations("classpath:/word/");
        super.addResourceHandlers(registry);
    }
}
