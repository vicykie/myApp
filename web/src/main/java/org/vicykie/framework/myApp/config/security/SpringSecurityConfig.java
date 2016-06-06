package org.vicykie.framework.myApp.config.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.vicykie.framework.myApp.handler.LoginFailureHandler;

import javax.annotation.PostConstruct;

/**
 * Created by vicykie on 2016/5/10.
 */
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    private static Logger logger = LogManager.getLogger(SpringSecurityConfig.class);

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @PostConstruct
    public void initSecurity() {
        logger.info("security init .....");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("a").password("a").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*        http
                .authorizeRequests()
                .anyRequest().authenticated()
//                .antMatchers("/login").permitAll()
                .antMatchers("/user*//**").hasRole("USER");

         http.formLogin().loginPage("/index.jsp").permitAll();
         http.logout().logoutUrl("/logout").permitAll();
         http.csrf().disable();
         http.httpBasic();*/


        http
                .authorizeRequests()
//                .anyRequest().authenticated()
                .antMatchers("/index.jsp").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .and()
                .formLogin().loginPage("/index.jsp").successForwardUrl("/user/22").failureHandler(loginFailureHandler)
//                .permitAll()
                .and().csrf().disable();

//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


}
