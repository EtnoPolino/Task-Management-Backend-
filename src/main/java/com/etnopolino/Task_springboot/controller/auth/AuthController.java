package com.etnopolino.Task_springboot.controller.auth;

import com.etnopolino.Task_springboot.dto.AuthenticationRequest;
import com.etnopolino.Task_springboot.dto.AuthenticationResponse;
import com.etnopolino.Task_springboot.dto.SignRequest;
import com.etnopolino.Task_springboot.dto.UserDto;
import com.etnopolino.Task_springboot.entities.User;
import com.etnopolino.Task_springboot.repository.UserRepository;
import com.etnopolino.Task_springboot.services.auth.AuthService;
import com.etnopolino.Task_springboot.services.jwt.UserService;
import com.etnopolino.Task_springboot.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*") //on autorise toutes les requete qui vont venir du front end
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

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

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));

        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect user name or password");
        }

        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser = userRepository.findFirstByEmail(authenticationRequest.getEmail());
        final String jwtToken = jwtUtil.generateToken(userDetails);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        if(optionalUser.isPresent()){
            authenticationResponse.setJwt(jwtToken);
            authenticationResponse.setUserId(optionalUser.get().getId());
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
        }

        return authenticationResponse;

    }

}
