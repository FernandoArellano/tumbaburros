package com.eazybytes.cards.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class HeadersInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String acceptHeader = String.valueOf(request.getHeaders("Accept"));
        System.out.println("Interceptor prehandle: " + acceptHeader);
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        String contentHeader = String.valueOf(response.getHeaders("Content-Type"));
        System.out.println("Interceptor postHandle: " + contentHeader);
    }
}
