package org.vicykie.framework.web.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static Logger logger = LogManager.getLogger(ProfileDev.class);
    @PostConstruct
    public void initProfile(){
        logger.info("dev   profile init...");

    }
}
