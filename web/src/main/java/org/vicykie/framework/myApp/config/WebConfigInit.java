package org.vicykie.framework.myApp.config;

import org.apache.logging.log4j.web.Log4jServletContextListener;
import org.apache.logging.log4j.web.Log4jServletFilter;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

/**
 * Created by vicyk on 2016/5/3.
 */
@Order(1)
public class WebConfigInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{ApplicationContextConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{SpringMVCConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);

        //注册log4j 2
        logger.info("注册 log4j ....");
        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD,
                DispatcherType.INCLUDE, DispatcherType.ERROR);
        servletContext.addListener(Log4jServletContextListener.class);
        servletContext.addFilter("log4jServletFilter", Log4jServletFilter.class)
                .addMappingForUrlPatterns(dispatcherTypes, false, "/*");
        servletContext.setInitParameter("log4jConfigLocation", "classpath:log4j2.xml");
        servletContext.setInitParameter("isLog4jContextSelectorNamed", "true");
        servletContext.setInitParameter("log4jContextName", "myApplication");

        //设置初始化参数,环境设置
        servletContext.setInitParameter("spring.profiles.active", getActiveProfile());
    }

    /**
     * 根据系统环境变量设置当前应用环境，可以自定义一个系统的环境变量区分
     */

    private String getActiveProfile() {
        String os = System.getenv("OS").toLowerCase();
        if (os.indexOf("win") != -1) {
            return "dev";
        } else {
            return "prod";
        }
    }

    @Override
    protected Filter[] getServletFilters() {
        logger.info("注册字符编码过滤器。");
        CharacterEncodingFilter filter = new CharacterEncodingFilter("utf-8", true);
        return new Filter[]{filter};
    }


}
