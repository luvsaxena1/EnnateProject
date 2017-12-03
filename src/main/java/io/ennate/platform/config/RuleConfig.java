package io.ennate.platform.config;

import io.ennate.platform.service.impl.AlertsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RuleConfig {

    @Bean
    @Primary
    public AlertsServiceImpl getAlertService() {
        return new AlertsServiceImpl();
    }
}
