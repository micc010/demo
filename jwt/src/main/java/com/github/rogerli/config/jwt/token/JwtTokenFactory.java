package com.github.rogerli.config.jwt.token;

import com.github.rogerli.config.jwt.JwtProperties;
import com.github.rogerli.config.jwt.jti.JtiGenerator;
import com.github.rogerli.config.jwt.model.UserContext;
import com.github.rogerli.utils.RestfulUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPool;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Factory class that should be always used to create {@link JwtToken}.
 *
 * @author vladimir.stankovic
 *         <p>
 *         May 31, 2016
 */
@Component
public class JwtTokenFactory {

    private final JwtProperties jwtProperties;

    @Autowired
    @Qualifier("uuidGenerator")
    private JtiGenerator jtiGenerator;

    @Autowired
    public JwtTokenFactory(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    /**
     * Factory method for issuing new JWT Tokens.
     *
     * @param userContext
     * @return
     */
    public AccessJwtToken createAccessJwtToken(UserContext userContext) {
        if (StringUtils.isEmpty(userContext.getUsername()))
            throw new IllegalArgumentException("Cannot create JWT Token without username");

        if (userContext.getAuthorities() == null || userContext.getAuthorities().isEmpty())
            throw new IllegalArgumentException("User doesn't have any privileges");

        Claims claims = Jwts.claims().setSubject(userContext.getUsername());
        claims.put("scopes", userContext.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));

        DateTime currentTime = new DateTime();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(jwtProperties.getTokenIssuer())
                .setIssuedAt(currentTime.toDate())
                .setExpiration(currentTime.plusMinutes(jwtProperties.getTokenExpirationTime()).toDate())
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getTokenSigningKey())
                .compact();

        return new AccessJwtToken(token, claims);
    }

    /**
     * @param userContext
     * @return
     */
    public JwtToken createRefreshToken(UserContext userContext) {
        if (StringUtils.isEmpty(userContext.getUsername())) {
            throw new IllegalArgumentException("Cannot create JWT Token without username");
        }

        DateTime currentTime = new DateTime();

        Claims claims = Jwts.claims().setSubject(userContext.getUsername());
        claims.put("scopes", Arrays.asList(RestfulUtils.ROLE_REFRESH_TOKEN));

        // 生成jti
        String jti = jtiGenerator.generateId(currentTime.toDate(),
                currentTime.plusMinutes(jwtProperties.getRefreshTokenExpTime()).toDate());

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(jwtProperties.getTokenIssuer())
                .setId(jti)
                .setIssuedAt(currentTime.toDate())
                .setExpiration(currentTime.plusMinutes(jwtProperties.getRefreshTokenExpTime()).toDate())
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getTokenSigningKey())
                .compact();

        return new AccessJwtToken(token, claims);
    }
}
