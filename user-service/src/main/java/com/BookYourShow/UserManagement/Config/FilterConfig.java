package com.BookYourShow.UserManagement.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.BookYourShow.UserManagement.Filter.JwtRequestFilter;
import com.BookYourShow.UserManagement.Utils.JwtUtil;

public class FilterConfig {

    @Autowired
    private JwtUtil jwtUtil;

    @Bean
    public FilterRegistrationBean<JwtRequestFilter> jwtFilter() {
        FilterRegistrationBean<JwtRequestFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new JwtRequestFilter(jwtUtil));
        registrationBean.addUrlPatterns("/api/users/verify");
        registrationBean.setOrder(1);

        return registrationBean;
    }

}
