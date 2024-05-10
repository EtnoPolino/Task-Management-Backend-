package com.etnopolino.Task_springboot.dto;

import lombok.Data;

@Data
public class SignRequest {

    private String name;
    private String email;
    private String password;

}
