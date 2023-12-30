package com.maoxian.scheduler.pojo;

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
public class Scheduler {
    @Value("${mode}")
    private Integer mode;

    @Value("${threshold}")
    private Integer threshold;

    @Value("${docker-url}")
    private String dockerUrl;
}
