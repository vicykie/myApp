package org.vicykie.framework.myApp.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;

/**
 * Created by vicykie on 2016/5/3.
 */
//@Configuration
@EnableWebMvc //===>same as <mvc:annotation-driven/>
//只扫描controller ==>same as <context:component-scan base-package=”com.luckyryan.sample”/>
@ComponentScan(basePackages = "org.vicykie.framework.myApp.web", useDefaultFilters = false, includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class})})
//注册视图解析器
//@Import({IFreemarkerConfig.class})
public class SpringMVCConfig extends WebMvcConfigurerAdapter {
    /**
     * WebMvcConfigurerAdapter和 WebMvcConfigurationSupport区别，
     * WebMvcConfigurerAdapter需要@EnableWebMvc，WebMvcConfigurationSupport不需要EnableWebMvc
     * http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/servlet/config/annotation/EnableWebMvc.html
     */

    private static Logger logger = LogManager.getLogger(SpringMVCConfig.class);
//    private final String[] PATH_PATTERNS= {"/static","/assets"};
//    private final String[] RESOURCES_PATH= {"/static/**","/assets/**"};
    @PostConstruct
    public void initSpringMVC() {
        logger.info("springMVC init....");
    }



    // equivalent for <mvc:default-servlet-handler/> tag
    // same as <mvc:default-servlet-handler/>
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
