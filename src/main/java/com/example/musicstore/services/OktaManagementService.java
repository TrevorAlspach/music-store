package com.example.musicstore.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
public class OktaManagementService {
    private final RestClient apiClient;
    private final OktaTokenService tokenService;
    @Autowired
    public OktaManagementService(@Qualifier("apiClient") RestClient apiClient, OktaTokenService tokenService) {
        this.apiClient = apiClient;
        this.tokenService = tokenService;
    }

    public void deleteUser(String id){
        String token = tokenService.getAccessToken();

        String response = apiClient
                .delete()
                .uri("users/" + id)
                .header("authorization", "Bearer " + token)
                .retrieve().body(String.class);
        log.debug(response);

    }
}
