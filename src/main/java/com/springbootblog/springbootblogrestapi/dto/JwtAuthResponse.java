package com.springbootblog.springbootblogrestapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "JwtAuthResponse Model Information")
public class JwtAuthResponse {

    @Schema(description = "Blog JwtAuthResponse Access Token")
    private String accessToken;
    private String tokenType = "Bearer";
}
