package com.maoxian.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Configuration
@PropertySource("classpath:scheduler.properties")
public class SchedulerConfig {
    @Value("${scheduler.mode}")
    private Integer schedulerMode;

    @Value("${waf.threshold}")
    private Integer wafThreshold;

    @Value("${waf.mode}")
    private Integer wafMode;

    @Value("${scheduler.server}")
    private String serverAddress;
}
