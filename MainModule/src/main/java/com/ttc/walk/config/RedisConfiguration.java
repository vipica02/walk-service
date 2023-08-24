package com.ttc.walk.config;


import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.net.http.HttpClient;


@Configuration
public class RedisConfiguration {

    @Value("${app.redis.host}")
    private String host;
    @Value("${app.redis.port}")
    private int port;
    @Value("${app.redis.password}")
    private String password;
    @Value("${app.redis.db-default}")
    private int db;
    @Value("${app.redis.timeout}")
    private int timeout;

    @Bean
    public JedisPool create() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(30);
        poolConfig.setMaxIdle(30);
        if (StringUtils.isEmpty(password))
            password = null;

        return new JedisPool(poolConfig, host, port, timeout, password, db);
    }

    @Bean
    public HttpClient httpClient() {
        return HttpClient.newHttpClient();
    }

    @Bean
    public CommonsRequestLoggingFilter commonsRequestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludeHeaders(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setHeaderPredicate(s -> true);
        loggingFilter.setMaxPayloadLength(64000);
        return loggingFilter;
    }



}
