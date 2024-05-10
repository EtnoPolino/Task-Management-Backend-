package com.etnopolino.Task_springboot.controller.auth;

import com.etnopolino.Task_springboot.dto.SignRequest;
import com.etnopolino.Task_springboot.dto.UserDto;
import com.etnopolino.Task_springboot.services.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignRequest signRequest){
        if(authService.hasEmailWithEmail(signRequest.getEmail())){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User already exist with this email");
        }

        UserDto createdUserDto = authService.signupUser(signRequest);

        if(createdUserDto == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not created");
        }

        return ResponseEntity.status(HttpStatus.OK).body(createdUserDto);

    }
}
