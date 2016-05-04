package org.vicykie.framework.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by vicyk on 2016/5/3.
 */
@Configuration
@EnableWebMvc
//只扫描controller
@ComponentScan(basePackages = "org.vicykie.framework.web",includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = {Controller.class})})
public class SpringMVCConfig extends WebMvcConfigurationSupport {
    //注册viewResoler
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INFO/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
