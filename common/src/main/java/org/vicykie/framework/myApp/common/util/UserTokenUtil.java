package org.vicykie.framework.myApp.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.vicykie.framework.myApp.common.entity.authority.Role;
import org.vicykie.framework.myApp.common.entity.authority.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vicykie on 2016/6/15.
 */
@Component
public class UserTokenUtil {
    private final String SECURITY_KEY = "vicykie";
    private final String AUDIENCE_UNKNOWN = "unknown";
    private final String AUDIENCE_WEB = "web";
    private final String AUDIENCE_MOBILE = "mobile";
    private final String AUDIENCE_TABLET = "tablet";
    private long expiration = 604800L;//7days

    //claims key
    private final String CLAIMS_USER_NAME = "name";
    private final String CLAIMS_ADDRESS = "address";
    private final String CLAIMS_CREATED = "created";
    private final String CLAIMS_ROLES = "roles";
    private final String CLAIMS_USER_EXPIRE_DATE = "expire_date";
    private final String CLAIMS_USER_CREATE_DATE = "create_date";

    public String generateToken(User user) {
        String username = user.getUsername();
        String name = user.getName();
        String address = user.getAddress();
        Role role = user.getRole();
        Date createDate = user.getCreateDate();
        Date expireDate = user.getExpireDate();
        /**
         * 放入相关信息
         */
        Map<String, Object> claims = new HashMap<>();
        claims.put(Claims.SUBJECT, username);
        claims.put(Claims.AUDIENCE, AUDIENCE_WEB);
        claims.put(CLAIMS_CREATED, this.generateCurrentDate());
        claims.put(CLAIMS_USER_NAME, name);
        claims.put(CLAIMS_ADDRESS, address);
        claims.put(CLAIMS_ROLES, role);
        claims.put(CLAIMS_USER_CREATE_DATE, createDate);
        claims.put(CLAIMS_USER_EXPIRE_DATE, expireDate);
        claims.put(Claims.EXPIRATION, this.generateExpirationDate());
        return this.generateToken(claims);

    }


    public User parseToken(String token) {
        User user = new User();
        final Claims claims = this.getClaimsFromToken(token);
        user.setUsername(claims.getSubject());
        user.setName((String) claims.get(CLAIMS_USER_NAME));
        Object s = claims.get(CLAIMS_USER_CREATE_DATE);
        user.setCreateDate(new Date((Long) claims.get(CLAIMS_USER_CREATE_DATE)));
        user.setExpireDate(new Date((Long) claims.get(CLAIMS_USER_EXPIRE_DATE)));
        user.setRole((Role) claims.get(CLAIMS_ROLES));
        return user;
    }

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIMS_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    public String getAudienceFromToken(String token) {
        String audience;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            audience = claims.getAudience();
        } catch (Exception e) {
            audience = null;
        }
        return audience;
    }


    /**
     * 是否可以更新token
     *
     * @param token
     * @param lastPasswordReset
     * @return
     */
    public Boolean canTokenBeRefresh(String token, Date lastPasswordReset) {
        final Date created = this.getCreatedDateFromToken(token);
        return (!(this.isCreatedBeforeLastPasswordReset(created, lastPasswordReset))
                && (!(this.isTokenExpired(token)) || this.ignoreTokenExpiration(token)));
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            claims.put("created", this.generateCurrentDate());//更新时间
            refreshedToken = this.generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean validateToken(String token, User user) {

        final String username = this.getUsernameFromToken(token);
        final Date created = this.getCreatedDateFromToken(token);
//        final Date expiration = this.getExpirationDateFromToken(token);
        return (username.equals(user.getUsername())
                && !(this.isTokenExpired(token))
                && !(this.isCreatedBeforeLastPasswordReset(created, user.getLastPasswordReset())));
    }


    private Boolean ignoreTokenExpiration(String token) {
        String audience = this.getAudienceFromToken(token);
        return (this.AUDIENCE_TABLET.equals(audience) || this.AUDIENCE_MOBILE.equals(audience));
    }


    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(this.generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, SECURITY_KEY)
                .compact();
    }


    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECURITY_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + this.expiration * 1000);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(this.generateCurrentDate());
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    public static void main(String[] args) {
        UserTokenUtil t = new UserTokenUtil();
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aWN5a2" +
                "llIiwiYXVkIjoid2ViIiwiYWRkcmVzcyI6bnVsbCwiZXhwaXJlX2RhdGUiOjE1Mj" +
                "g4ODQzMjE0NDYsImNyZWF0ZWQiOjE0NjYwNjczMzkzOTIsInJvbGVzIjpudWxsLC" +
                "JuYW1lIjoidmljeWtpZSIsImNyZWF0ZV9kYXRlIjoxNDY1ODEyMzIxNDQ2LCJleHAi" +
                "OjE0NjY2NzIxMzl9.pzJw5F2nVHWKDEVBX1ZaC1-cAy0o9jw47wo32HRZd3SbSfply" +
                "q_piDRsUB5qPiAwkbDoPlXarSLA5-O7VUCJsg";
        User user = t.parseToken(token);
        System.out.println(user);
    }
}
