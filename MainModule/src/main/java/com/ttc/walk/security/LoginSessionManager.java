package com.ttc.walk.security;

import com.ttc.walk.util.Constant;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoginSessionManager {

    private final JedisPool jedisPool;
    private final Long timeExpire;

    public LoginSessionManager(JedisPool jedisPool,
                               @Value("${app.jwt.expire.time}") Long timeExpire) {
        this.jedisPool = jedisPool;
        this.timeExpire = timeExpire;
    }

    public boolean saveLoginSession(String id, String token, Long seconds) {
        if (seconds == null) seconds = timeExpire;

        try (Jedis jedis = jedisPool.getResource()) {
            String status = jedis.set(toKey(id), token, SetParams.setParams().ex(seconds));
            return !StringUtils.isEmpty(status);
        }
    }

    public boolean revokeLoginSession(String id) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.del(toKey(id)) > 0;
        }
    }

    public void revokeAllLoginSession(List<String> ids) {
        try (Jedis jedis = jedisPool.getResource()) {
            List<String> keys = ids.stream().map(this::toKey).collect(Collectors.toList());
            keys.forEach(it -> jedis.del(toKey(it)));
        }
    }

    public String findToken(String id) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.get(toKey(id));
        }
    }

    public String toKey(String id) {
        return id + Constant.SEPARATOR + Constant.JWT_OA;
    }

}
