package org.vicykie.framework.myApp.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.vicykie.framework.myApp.auth.TokenAuthenticationService;
import org.vicykie.framework.myApp.auth.UserAuthentication;
import org.vicykie.framework.myApp.common.entity.authority.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vicykie on 2016/6/15.
 */
public class TokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
    private final UserDetailsService userDetailsService;
    private final TokenAuthenticationService tokenAuthenticationService;
    private final AuthenticationManager authenticationManager;
//    private final LoginFailureHandler loginFailureHandler;

    public TokenAuthenticationProcessingFilter(String defaultFilterProcessesUrl,
                                               UserDetailsService userDetailsService,
                                               TokenAuthenticationService tokenAuthenticationService
            , AuthenticationManager authenticationManager
    ) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        this.userDetailsService = userDetailsService;
        this.tokenAuthenticationService = tokenAuthenticationService;
        this.authenticationManager = authenticationManager;
//        this.loginFailureHandler = loginFailureHandler;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

//        BufferedReader in = new BufferedReader(new InputStreamReader(
//              request.getInputStream()));
//        String line = null;
//        StringBuilder sb = new StringBuilder();
//        while ((line = in.readLine()) != null) {
//            sb.append(line);
//        }
//        in.close();
//
//
        final User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
        final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword());
//        AuthenticationManager m  = getAuthenticationManager();
        return authenticationManager.authenticate(loginToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authentication) throws IOException, ServletException {

        // Lookup the complete User object from the database and create an Authentication for it
        final User authenticatedUser = (User) userDetailsService.loadUserByUsername(authentication.getName());
        final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);

        // Add the custom token as HTTP header to the response
        tokenAuthenticationService.addAuthentication(response, userAuthentication);

        // Add the authentication to the Security context
        SecurityContextHolder.getContext().setAuthentication(userAuthentication);

    }


}
