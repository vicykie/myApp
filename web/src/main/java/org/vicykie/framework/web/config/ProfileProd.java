package org.vicykie.framework.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

/**
 * Created by d on 2016/5/4.
 */
@Profile("prod")
@PropertySource("classpath:config.prod.properties")
@Configuration
public class ProfileProd {
    @PostConstruct
    public void initProfile(){
        System.out.println("prod   profile init...");

    }
}
