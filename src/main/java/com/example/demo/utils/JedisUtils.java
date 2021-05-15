package com.example.demo.utils;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


public class JedisUtils {

    static GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
    private static JedisPool jedisPool = null;

    private static Jedis getRedis() {
        if (jedisPool == null){
            poolConfig.setMaxIdle(20);
            poolConfig.setMaxTotal(50);
            poolConfig.setMinIdle(30);
            jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379);
        }
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }
    public static void setToken(String id, String token, int hour) {
        int second = hour * 60 * 60;
        Jedis redis = JedisUtils.getRedis();
        redis.set(id, token);
        redis.expire(id, second);
        redis.close();
    }

    public static String getToken(String id) {
        Jedis redis = JedisUtils.getRedis();
        String token = redis.get(id);
        redis.close();
        return token;
    }

    public static boolean isExists(String id){
        Jedis redis = JedisUtils.getRedis();
        boolean b= redis.exists(id);
        redis.close();
        return b;
    }
}
