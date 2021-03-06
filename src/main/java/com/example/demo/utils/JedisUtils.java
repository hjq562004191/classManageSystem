package com.example.demo.utils;

import redis.clients.jedis.Jedis;


public class JedisUtils {

    private static Jedis jedis;
    private static void init() {
        jedis = new Jedis("localhost");
    }
    public static void setToken(String id, String token, int day) {
        int second = day * 60 * 60 * 24;
        JedisUtils.init();
        jedis.set(String.valueOf(id), token);
        jedis.expire(String.valueOf(id), second);
    }

    public static String getToken(String id) {
        JedisUtils.init();
        String token = jedis.get(String.valueOf(id));
        return token;
    }
}
