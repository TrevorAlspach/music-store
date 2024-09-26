package com.example.musicstore.services;

import com.example.musicstore.rest.dto.OktaTokenRequestDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
public class OktaTokenService {
    @Value("${okta.managementApi.clientId}")
    private String clientId;
    @Value("${okta.managementApi.clientSecret}")
    private String clientSecret;
    @Value("${okta.managementApi.audience}")
    private String audience;
    @Value("${okta.managementApi.grantType}")
    private String grantType;

    private final RestClient tokenClient;

    private final Gson gson = new Gson();

    @Autowired
    public OktaTokenService(@Qualifier("tokenClient") RestClient tokenClient) {
        this.tokenClient = tokenClient;
    }

    public String getAccessToken(){
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", grantType);
        formData.add("audience", audience);
        formData.add("client_secret", clientSecret);
        formData.add("client_id", clientId);

        JsonObject jsonResponse = gson.fromJson(tokenClient.post().body(formData).retrieve().body(String.class), JsonObject.class);
        return jsonResponse.get("access_token").getAsString();
    }

}
