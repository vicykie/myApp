package org.vicykie.framework.myApp.config.profile;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
        private static Logger logger = LogManager.getLogger(ProfileProd.class);
    @PostConstruct
        public void initProfile(){
            logger.info(" prod  env init ..");
        }
}
