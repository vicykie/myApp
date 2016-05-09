package org.vicykie.framework.myApp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.vicykie.framework.myApp.config.view.IFreemarkerConfig;

/**
 * Created by vicykie on 2016/5/3.
 */
@Configuration
@EnableWebMvc
//只扫描controller
@ComponentScan(basePackages = "org.vicykie.framework.myApp.web",useDefaultFilters = false,includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = {Controller.class})})
//注册视图解析器
@Import({IFreemarkerConfig.class})
public class SpringMVCConfig extends WebMvcConfigurationSupport {

}
