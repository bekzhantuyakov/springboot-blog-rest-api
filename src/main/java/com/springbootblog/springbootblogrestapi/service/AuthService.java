package com.springbootblog.springbootblogrestapi.service;

import com.springbootblog.springbootblogrestapi.dto.LoginDto;
import com.springbootblog.springbootblogrestapi.dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
