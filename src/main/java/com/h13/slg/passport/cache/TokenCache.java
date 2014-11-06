package com.h13.slg.passport.cache;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-17
 * Time: 上午12:54
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TokenCache {

    private static final String KEY = "slg:passport:token:";

    @Resource(name = "tokenTemplate")
    private RedisTemplate<String, TokenCO> tokenTemplate;


    public TokenCO get(String token) {
        return tokenTemplate.opsForValue().get(KEY + token);
    }

    public void set(TokenCO co, int timeoutS) {
        tokenTemplate.opsForValue().set(KEY + co.getToken(), co, timeoutS, TimeUnit.SECONDS);
    }

}
