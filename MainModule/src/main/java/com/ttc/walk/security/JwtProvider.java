package com.ttc.walk.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ttc.walk.model.AccountAuth;
import com.ttc.walk.model.AccountClaim;
import com.ttc.walk.util.Constant;
import lombok.extern.log4j.Log4j2;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;

@Component
@Log4j2
public class JwtProvider {

    private final Logger logger = LogManager.getLogger(getClass());
    private final Algorithm algorithm;
    private final JWTVerifier jwtVerifier;
    private final JedisPool jedisPool;
    private final LoginSessionManager loginSessionManager;

    @Autowired
    public JwtProvider(@Value("${app.jwt.secret.account}") String secretJwt,
                       JedisPool jedisPool,
                       LoginSessionManager loginSessionManager) {
        this.algorithm = Algorithm.HMAC256(secretJwt);
        this.jwtVerifier = JWT.require(this.algorithm).build();
        this.jedisPool = jedisPool;
        this.loginSessionManager = loginSessionManager;
    }

    public DecodedJWT validateJWT(HttpServletRequest request) {
        String token = getJWTToken(request);
        return StringUtils.isEmpty(token) ? null : validateJWT(token);
    }

    /**
     * Validate JWT
     *
     * @param token
     * @return
     */
    public DecodedJWT validateJWT(String token) {
        try (Jedis jedis = jedisPool.getResource()) {
            DecodedJWT decodedJWT = this.jwtVerifier.verify(token);
            String tokenSession = jedis.get(loginSessionManager.toKey(decodedJWT.getClaim(Constant.USER_ID).asString()));
            return token.equals(tokenSession) ? decodedJWT : null;
        }
    }



    /**
     * @param request
     * @return
     */
    public AccountClaim getAccountClaim(HttpServletRequest request) {
        DecodedJWT decodedJWT;
        try {
            decodedJWT = (DecodedJWT) request.getAttribute(Constant.USER_ATTR);
            if (decodedJWT == null) return null;
        } catch (Exception e) {
            logger.error(e);
            return null;
        }

        AccountClaim accountClaim = new AccountClaim();
        accountClaim.setUserId(decodedJWT.getClaim(Constant.USER_ID).asString());
        accountClaim.setPhone(decodedJWT.getClaim(Constant.PHONE).asString());
        accountClaim.setPhone(decodedJWT.getClaim(Constant.USERNAME).asString());

        return accountClaim;
    }

    /**
     * Generate JWT
     * if timeExpire is null, default expireTime will be used
     *
     * @param timeExpire
     * @param accountAuth
     * @return
     */
    public String generateJWT(AccountAuth accountAuth, Long timeExpire) {
        String token = JWT.create()
                .withIssuedAt(Date.from(Instant.now()))
                .withClaim(Constant.USER_ID, accountAuth.getUserId())
                .withClaim(Constant.PHONE, accountAuth.getPhone())
                .sign(this.algorithm);

        // save token to buffer
        loginSessionManager.saveLoginSession(accountAuth.getUserId(), token, timeExpire);
        return token;
    }

    /**
     * Find Token in buffer (Redis, Aerospike, ...), if don't have then generate a new token
     * if timeExpire is null, default expireTime will be used
     *
     * @param accountAuth
     * @param timeExpire
     * @return
     */
    public String findOrGenerateJwt(AccountAuth accountAuth, Long timeExpire) {
        String token = loginSessionManager.findToken(accountAuth.getUserId());
        return StringUtils.isEmpty(token) ? generateJWT(accountAuth, timeExpire) : token;
    }

    /**
     * Get JWT in request
     * @param request
     * @return
     */
    public String getJWTToken(HttpServletRequest request) {
        String authorizationVal = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationVal == null
                || authorizationVal.length() <= Constant.BEARER.length() + 1
                || !authorizationVal.startsWith(Constant.BEARER))
            return "";

        return authorizationVal.substring(Constant.BEARER.length() + 1);
    }

}
