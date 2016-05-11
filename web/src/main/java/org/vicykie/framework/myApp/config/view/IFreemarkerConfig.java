package org.vicykie.framework.myApp.config.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Properties;

/**
 * Created by vicykie on 2016/5/9.
 * <p>
 * freemarker解析器配置
 */

public class IFreemarkerConfig implements ViewConfig {
    private static Logger logger = LogManager.getLogger(IFreemarkerConfig.class);

    //注册试图解析器
    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setSuffix(FREEMARKER_TEMPLATE_SUFIX);
        viewResolver.setCache(true);
        viewResolver.setContentType("text/html;charset=UTF-8");
        logger.info("freemarker init...");
        return viewResolver;
    }

    //注册freemarkerconfig
    @Bean
    public FreeMarkerConfig freeMarkerConfig() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setDefaultEncoding("utf-8");
        configurer.setTemplateLoaderPath(FREEMARKER_TEMPLATE_LOADER_PATH);
        Properties settings = new Properties();
        settings.setProperty("template_update_delay", "100");
        settings.setProperty("locale", "zh_CN");
//        settings.setProperty("template_update_delay","100");
        configurer.setFreemarkerSettings(settings);
        return configurer;
    }


}
