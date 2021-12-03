package com.sycoldstorage.apigateway.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Slf4j
@Configuration
public class CorsGlobalConfiguration {

    @Value("${cors.allowedOrigin}")
    public String allowedOrigin;

    @Value("${cors.allowedMethods}")
    public String allowedMethods;

    @Value("${cors.exposedHeader}")
    public String exposedHeader;


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedOrigin(allowedOrigin);
        corsConfiguration.addAllowedMethod(allowedMethods);

        for (String header : StringUtils.split(exposedHeader, ",")) {
            corsConfiguration.addExposedHeader(header);
        }
//        corsConfiguration.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public CorsWebFilter corsWebFilter() {
        return new CorsWebFilter(corsConfigurationSource());
    }
}