package com.example.musicstore.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class OktaRestClientConfig {
    @Value("${okta.managementApi.tokenUrl}")
    private String tokenUrl;

    @Value("${okta.managementApi.audience}")
    private String managementApiUrl;

    // RestClient for obtaining access token
    @Bean
    @Qualifier("tokenClient")
    public RestClient tokenClient() {
        return RestClient.builder()
                .baseUrl(tokenUrl)
                .defaultHeader("content-type", "application/x-www-form-urlencoded")
                .build();
    }

    // RestClient for making Okta API calls with an access token
    @Bean
    @Qualifier("apiClient")
    public RestClient apiClient() {
        return RestClient.builder()
                .defaultHeader("content-type", "application/json")
                .baseUrl(managementApiUrl) // Okta Management API base URL
                .build();
    }
}
