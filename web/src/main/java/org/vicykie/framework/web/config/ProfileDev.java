package org.vicykie.framework.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

/**
 * Created by d on 2016/5/4.
 */
@Profile("dev")
@PropertySource("classpath:config.dev.properties")
@Configuration
public class ProfileDev {
    @PostConstruct
    public void initProfile(){
        System.out.println("dev   profile init...");

    }
}
