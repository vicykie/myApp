package org.vicykie.framework.myApp.config;

import org.apache.logging.log4j.LogManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

/**
 * Created by vicyk on 2016/5/3.
 */
@Configuration
//不扫描controller注解  controller交给springMVCconfig管理
@ComponentScan(basePackages = "org.vicykie.framework", excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class})})
public class ApplicationContextConfig {
    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(ApplicationContextConfig.class);

    @PostConstruct
    public void initApp() {
        logger.info("Spring applicationContext init.....");
    }
}
