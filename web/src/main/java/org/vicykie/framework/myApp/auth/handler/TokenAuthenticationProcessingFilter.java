package org.vicykie.framework.myApp.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.vicykie.framework.myApp.common.entity.authority.User;
import org.vicykie.framework.myApp.common.util.HttpUtils;
import org.vicykie.framework.myApp.common.util.UserTokenUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by vicykie on 2016/6/15.
 */
public class TokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationManager authenticationManager;
    //    private final LoginFailureHandler loginFailureHandler;
    private final static String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
    private final UserTokenUtil tokenHandler;

    public TokenAuthenticationProcessingFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager, UserTokenUtil tokenHandler
    ) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));

        this.authenticationManager = authenticationManager;
        this.tokenHandler = tokenHandler;
//        this.loginFailureHandler = loginFailureHandler;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String contentType = request.getContentType();
        if (HttpUtils.isFormRequest(contentType)) {
            final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
                    request.getParameter("username"), request.getParameter("password"));
            return authenticationManager.authenticate(loginToken);
        } else {
            final User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
                    user.getUsername(), user.getPassword());
            return authenticationManager.authenticate(loginToken);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authentication) throws IOException, ServletException {
        String contentType = request.getContentType();
//        // Lookup the complete User object from the database and create an Authentication for it
//        final User authenticatedUser = (User) userDetailsService.loadUserByUsername(authentication.getName());
//        final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);
//
//        // Add the custom token as HTTP header to the response
//        tokenAuthenticationService.addAuthentication(response, userAuthentication);
        final User user = (User) authentication.getPrincipal();
        response.addHeader(AUTH_HEADER_NAME, tokenHandler.generateToken(user));
        // Add the authentication to the Security context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (HttpUtils.isFormRequest(contentType)) {
            response.sendRedirect(request.getContextPath() + "/user/index");
        } else {
            PrintWriter writer = response.getWriter();
            writer.write("success");
            writer.flush();
            writer.close();
        }
    }


}
