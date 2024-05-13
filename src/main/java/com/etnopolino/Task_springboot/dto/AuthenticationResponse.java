package com.etnopolino.Task_springboot.dto;

import com.etnopolino.Task_springboot.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private String jwt;
    private Long userId;
    private UserRole userRole;
}
