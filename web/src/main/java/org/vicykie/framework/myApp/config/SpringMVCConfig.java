package org.vicykie.framework.myApp.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.vicykie.framework.myApp.config.view.FreemarkerResolverConfig;

import javax.annotation.PostConstruct;

/**
 * @linkto http://stackoverflow.com/questions/17898606/difference-between-webmvcconfigurationsupport-and-webmvcconfigureradapter
 * Created by vicykie on 2016/5/3.
 *
 */
//@Configuration
@EnableWebMvc
//只扫描controller
@ComponentScan(basePackages = "org.vicykie.framework.myApp.*.web", useDefaultFilters = false, includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class})})
//注册视图解析器

//
@Import({FreemarkerResolverConfig.class})
public class SpringMVCConfig extends WebMvcConfigurerAdapter {
    private static Logger logger = LogManager.getLogger(SpringMVCConfig.class);

    @PostConstruct
    public void initSpringMVC() {
        logger.info("springMVC init....");
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        logger.info("resourceHandler ! -->  ! -->");
//       // super.addResourceHandlers(registry);
//        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
//    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        //default handler
        logger.info("enable default servlet handling ... excluding  static file requests ");
        configurer.enable();
    }


//    @Bean /* The "${props} can now be parsed before runtime with this bean declaration */
//    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }
}
