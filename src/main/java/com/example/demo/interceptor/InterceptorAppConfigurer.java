package com.example.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Component
@CrossOrigin
public class InterceptorAppConfigurer extends WebMvcConfigurationSupport {
    @Autowired
    private TokenInterceptor tokenInterceptor;
    @Autowired
    private FileTypeInterceptor fileTypeInterceptor;
    @Autowired
    private AdminInterceptor adminInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");
        registry.addInterceptor(fileTypeInterceptor).addPathPatterns("/**");
        registry.addInterceptor(adminInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
