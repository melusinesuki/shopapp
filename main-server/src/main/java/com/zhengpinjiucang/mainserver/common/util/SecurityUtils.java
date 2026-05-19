package com.zhengpinjiucang.mainserver.common.util;

import com.zhengpinjiucang.mainserver.common.exception.NormalException;

public class SecurityUtils {

    public static final ThreadLocal<Long> idCache = new ThreadLocal<>();

    public static Long getId() {
        Long id = idCache.get();
        if (id == null) {
            throw new NormalException("用户不存在");
        }
        return id;
    }

    public static void remove() {
        idCache.remove();
    }
}
