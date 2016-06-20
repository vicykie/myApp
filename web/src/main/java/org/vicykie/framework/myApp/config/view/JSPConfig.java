package org.vicykie.framework.myApp.config.view;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by vicykie on 2016/5/9.
 * JSP VIEW RESOLVER CONFIG
 */
public class JSPConfig extends ViewConfig {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix(JSP_RESOLVER_PREFIX);
        viewResolver.setSuffix(JSP_RESOLVER_SUFFIX);
        return viewResolver;
    }
}
