package org.vicykie.framework.myApp.config.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.vicykie.framework.myApp.auth.TokenAuthenticationService;
import org.vicykie.framework.myApp.auth.UnAuthenticationEntryPoint;
import org.vicykie.framework.myApp.auth.UserDetailsServiceImpl;
import org.vicykie.framework.myApp.auth.filter.StatelessAuthenticationFilter;
import org.vicykie.framework.myApp.auth.handler.LoginFailureHandler;
import org.vicykie.framework.myApp.auth.handler.LoginSuccessHandler;
import org.vicykie.framework.myApp.auth.handler.TokenAuthenticationProcessingFilter;
import org.vicykie.framework.myApp.common.util.AppPasswordEncoder;

import javax.annotation.PostConstruct;

/**
 * Created by vicykie on 2016/5/10.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static Logger logger = LogManager.getLogger(SecurityConfig.class);


    @Autowired
    private LoginFailureHandler loginFailureHandler;
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UnAuthenticationEntryPoint unAuthenticationEntryPoint;
    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @PostConstruct
    public void initSecurity() {
        logger.info("security init .....");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // unAuthentication
        http.exceptionHandling().authenticationEntryPoint(unAuthenticationEntryPoint);
        // session stateless
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // resources matcher auth
        http
                .authorizeRequests()
                .antMatchers("/index.jsp").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/pub/**").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .anyRequest().authenticated();
        //csrf disabled
        http.csrf().disable().httpBasic();
        // custom JSON based authentication by POST of
        // {"username":"<name>","password":"<password>"} which sets the token header upon authentication
        TokenAuthenticationProcessingFilter filter = new TokenAuthenticationProcessingFilter("/auth/login", userDetailsService,
                tokenAuthenticationService, authenticationManagerBean());
        filter.setAuthenticationFailureHandler(loginFailureHandler);
        filter.setAuthenticationSuccessHandler(loginSuccessHandler);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        // custom Token based authentication based on the header previously given to the client
        http.addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService),
                UsernamePasswordAuthenticationFilter.class);

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.authenticationProvider(authenticationProviderBean());
    }

    @Bean
    public AuthenticationProvider authenticationProviderBean() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(new AppPasswordEncoder("md5"));
        ReflectionSaltSource saltSource = new ReflectionSaltSource();
        saltSource.setUserPropertyToUse("salt");
        authenticationProvider.setSaltSource(saltSource);
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return userDetailsService;
    }
}
