package org.vicykie.framework.myApp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.vicykie.framework.myApp.common.entity.authority.User;
import org.vicykie.framework.myApp.common.util.UserTokenUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by vicykie on 2016/6/14.
 */

@Service("tokenService")
public class TokenAuthenticationService {
    private final static String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
    @Autowired
    private UserTokenUtil tokenHandler;


    public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
        final User user = (User) authentication.getDetails();
        response.addHeader(AUTH_HEADER_NAME, tokenHandler.generateToken(user));
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
            final User user = tokenHandler.parseToken(token);
            if (user != null) {
                return new UserAuthentication(user);
            }
        }
        return null;
    }
}
