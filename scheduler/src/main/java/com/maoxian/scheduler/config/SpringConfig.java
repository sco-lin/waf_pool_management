package com.maoxian.scheduler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

/**
 * @author Lin
 * @date 2023/12/17 23:03
 */
@Configuration
@EnableScheduling //开启定时任务的执行
public class SpringConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
