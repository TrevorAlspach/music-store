package com.example.musicstore.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OktaTokenRequestDTO {

    private String grant_type;
    private String client_id;
    private String client_secret;
    private String audience;

}
