package io.iot.platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import io.iot.platform.service.impl.AlertsServiceImpl;

@Configuration
public class RuleConfig {

    @Bean
    @Primary
    public AlertsServiceImpl getAlertService() {
        return new AlertsServiceImpl();
    }
}
