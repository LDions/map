package com.ruowei.security.jwt;

import com.ruowei.security.UserModel;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import tech.jhipster.config.JHipsterProperties;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider {

    private static final String AUTHORITIES_KEY = "auth";
    private static final String USERID_KEY = "uid";
    private static final String NICKNAME_KEY = "unm";
    private static final String ENTERPRISEID_KEY = "eid";
    private static final String GROUPID_KEY = "gid";
    private static final String ENTERPRISENAME_KEY = "enm";
    private static final String GROUPENAME_KEY = "gnm";
    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);
    private final Key key;

    private final JwtParser jwtParser;

    private final long tokenValidityInMilliseconds;

    private final long tokenValidityInMillisecondsForRememberMe;

    public TokenProvider(JHipsterProperties jHipsterProperties) {
        byte[] keyBytes;
        String secret = jHipsterProperties.getSecurity().getAuthentication().getJwt().getBase64Secret();
        if (!ObjectUtils.isEmpty(secret)) {
            log.debug("Using a Base64-encoded JWT secret key");
            keyBytes = Decoders.BASE64.decode(secret);
        } else {
            log.warn(
                "Warning: the JWT key used is not Base64-encoded. " +
                    "We recommend using the `jhipster.security.authentication.jwt.base64-secret` key for optimum security."
            );
            secret = jHipsterProperties.getSecurity().getAuthentication().getJwt().getSecret();
            keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        }
        key = Keys.hmacShaKeyFor(keyBytes);
        jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
        this.tokenValidityInMilliseconds = 1000 * jHipsterProperties.getSecurity().getAuthentication().getJwt().getTokenValidityInSeconds();
        this.tokenValidityInMillisecondsForRememberMe =
            1000 * jHipsterProperties.getSecurity().getAuthentication().getJwt().getTokenValidityInSecondsForRememberMe();
    }

    public String createToken(Authentication authentication, boolean rememberMe) {
        UserModel userModel = (UserModel) authentication.getPrincipal();
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity;
        if (rememberMe) {
            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        } else {
            validity = new Date(now + this.tokenValidityInMilliseconds);
        }

        return Jwts
            .builder()
            .setSubject(authentication.getName())
            .claim(AUTHORITIES_KEY, authorities)
            .claim(USERID_KEY, userModel.getUserId())
            .claim(NICKNAME_KEY, userModel.getNickName())
            .claim(ENTERPRISEID_KEY, userModel.getcode())
            .claim(GROUPID_KEY, userModel.getgroupCode())
            .claim(ENTERPRISENAME_KEY, userModel.getEnterpriseName())
            .claim(GROUPENAME_KEY, userModel.getGroupName())
            .signWith(key, SignatureAlgorithm.HS512)
            .setExpiration(validity)
            .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();

        Collection<? extends GrantedAuthority> authorities = Arrays
            .stream(claims.get(AUTHORITIES_KEY).toString().split(","))
            .filter(auth -> !auth.trim().isEmpty())
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
        Long userId = ((Number) claims.get(USERID_KEY)).longValue();
        String nickName = (String) claims.get(NICKNAME_KEY);
        String code = claims.get(ENTERPRISEID_KEY) != null ? claims.get(ENTERPRISEID_KEY).toString() : null;
        String groupCode = claims.get(GROUPID_KEY) != null ? claims.get(GROUPID_KEY).toString() : null;
        String enterpriseName = claims.get(ENTERPRISENAME_KEY) != null ? (String) claims.get(ENTERPRISENAME_KEY) : null;
        String groupName = claims.get(GROUPENAME_KEY) != null ? (String) claims.get(GROUPENAME_KEY) : null;

        User principal = new UserModel(claims.getSubject(), "", authorities, userId, nickName, code, groupCode, enterpriseName, groupName);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String authToken) {
        try {
            jwtParser.parseClaimsJws(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("Invalid JWT token.");
            log.trace("Invalid JWT token trace.", e);
        }
        return false;
    }
}
