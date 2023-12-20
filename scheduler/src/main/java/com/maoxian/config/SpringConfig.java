package com.maoxian.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Lin
 * @date 2023/12/17 23:03
 */
@Configuration
public class SpringConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
