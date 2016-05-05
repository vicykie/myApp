package org.vicykie.framework.myApp.config;

import org.apache.commons.logging.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.PostConstruct;

/**
 * Created by vicyk on 2016/5/3.
 */
@Configuration
@EnableWebMvc
//只扫描controller
@ComponentScan(basePackages = "org.vicykie.framework.myApp.web",useDefaultFilters = false,includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = {Controller.class})})
public class SpringMVCConfig extends WebMvcConfigurationSupport {
    private static Logger logger = LogManager.getLogger(SpringMVCConfig.class);
    //注册viewResoler
    @Bean
    public ViewResolver viewResolver(){
        logger.info("view Resolver....");
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INFO/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
