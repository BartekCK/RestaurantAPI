package com.restaurant.commands.response;

import lombok.Data;

@Data
public class JwtTokenDTO {
    private String token;
    private String type = "Bearer";

    public JwtTokenDTO(String accessToken) {
        this.token = accessToken;
    }

}
