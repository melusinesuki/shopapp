package com.zhengpinjiucang.mainserver.domain.interceptor;

import com.zhengpinjiucang.mainserver.common.exception.NormalException;
import com.zhengpinjiucang.mainserver.common.util.JWTUtils;
import com.zhengpinjiucang.mainserver.common.util.SecurityUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            throw new NormalException("请先登录");
        }
        if (!JWTUtils.verify(token)) {
            throw new NormalException("登录已过期，请重新登录");
        }

        Long userId = JWTUtils.getUserId(token);
        request.setAttribute("userId", userId);
        SecurityUtils.idCache.set(userId);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
        SecurityUtils.remove();
    }
}
