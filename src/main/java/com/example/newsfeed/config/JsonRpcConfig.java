package com.example.newsfeed.config;

import com.example.newsfeed.service.NewsJsonRpcServiceImpl;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImplExporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonRpcConfig {

    @Bean
    public static AutoJsonRpcServiceImplExporter autoJsonRpcServiceImplExporter() {
        return new AutoJsonRpcServiceImplExporter();
    }

    @Bean(name = "newsJsonRpcServiceImpl")
    public NewsJsonRpcServiceImpl newsJsonRpcServiceImpl() {
        return new NewsJsonRpcServiceImpl();
    }
}