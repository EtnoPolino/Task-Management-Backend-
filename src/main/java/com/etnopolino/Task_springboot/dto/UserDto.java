package com.etnopolino.Task_springboot.dto;

import com.etnopolino.Task_springboot.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private UserRole userRole;
}
