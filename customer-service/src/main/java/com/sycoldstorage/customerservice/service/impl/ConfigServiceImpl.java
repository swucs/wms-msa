package com.sycoldstorage.customerservice.service.impl;

import com.sycoldstorage.customerservice.service.ConfigService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Service
@RefreshScope
public class ConfigServiceImpl implements ConfigService {
    @Value("${application.name}")
    private String applicationName;

    @Override
    public String getApplicationName() {
        return applicationName;
    }
}
