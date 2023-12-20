package com.maoxian.scheduler.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Lin
 * @date 2023/12/17 23:03
 */
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
