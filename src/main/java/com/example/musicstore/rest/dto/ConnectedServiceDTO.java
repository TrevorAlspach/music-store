package com.example.musicstore.rest.dto;

import com.example.musicstore.config.ExternalService;
import lombok.Data;

@Data
public class ConnectedServiceDTO {
    ExternalService externalService;
    //Boolean expired;
    String displayName;

    public ConnectedServiceDTO(ExternalService externalService){
        this.externalService = externalService;
       // this.expired = expired;
        this.displayName = externalService.getDisplayName();
    }

}
