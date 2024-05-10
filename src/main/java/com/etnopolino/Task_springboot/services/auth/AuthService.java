package com.etnopolino.Task_springboot.services.auth;

import com.etnopolino.Task_springboot.dto.SignRequest;
import com.etnopolino.Task_springboot.dto.UserDto;

public interface AuthService {
    UserDto signupUser(SignRequest signRequest);

    boolean hasEmailWithEmail(String email);
}
