package org.vicykie.framework.myApp.config.view;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by vicykie on 2016/5/9.
 */
public class JSPConfig implements ViewConfig {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix(JSP_VIEW_RESOLER_PREFIX);
        viewResolver.setSuffix(JSP_VIEW_RESOLER_SUFIX);
        return viewResolver;
    }
}
