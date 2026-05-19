package com.zhengpinjiucang.mainserver.common.util;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;

import java.util.Map;

public class JWTUtils {

    private static final byte[] KEY = "12345678".getBytes();

    public static String createToken(Map<String, Object> claims) {
        return JWTUtil.createToken(claims, KEY);
    }

    public static boolean verify(String token) {
        try {
            return JWTUtil.verify(token, KEY);
        } catch (Exception e) {
            return false;
        }
    }

    public static Long getUserId(String token) {
        JWT jwt = JWTUtil.parseToken(token);
        Object id = jwt.getPayload("id");
        if (id instanceof Long) {
            return (Long) id;
        }
        if (id instanceof Integer) {
            return ((Integer) id).longValue();
        }
        return Long.valueOf(id.toString());
    }
}
