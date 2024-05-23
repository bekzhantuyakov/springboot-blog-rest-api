package com.springbootblog.springbootblogrestapi.controller;

import com.springbootblog.springbootblogrestapi.dto.JwtAuthResponse;
import com.springbootblog.springbootblogrestapi.dto.LoginDto;
import com.springbootblog.springbootblogrestapi.dto.RegisterDto;
import com.springbootblog.springbootblogrestapi.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "CRUD REST APIs for Authentication Controller")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //build Login REST API(sign in REST API)
    @Operation(summary = "Login/Sign-In REST API",
            description = "Create Login/Sign-In REST API")
    @ApiResponse(responseCode = "201",
            description = "Http Status 201 CREATED")
    @SecurityRequirement(name = "Bear Authentication")
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    //Build register REST API
    @Operation(summary = "Register/Sign-Up REST API",
            description = "Create Register/Sign-Up REST API")
    @ApiResponse(responseCode = "201",
            description = "Http Status 201 CREATED")
    @SecurityRequirement(name = "Bear Authentication")
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
       String response = authService.register(registerDto);
       return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
