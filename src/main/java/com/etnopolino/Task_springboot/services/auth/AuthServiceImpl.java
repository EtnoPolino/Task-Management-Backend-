package com.etnopolino.Task_springboot.services.auth;

import com.etnopolino.Task_springboot.dto.SignRequest;
import com.etnopolino.Task_springboot.dto.UserDto;
import com.etnopolino.Task_springboot.entities.User;
import com.etnopolino.Task_springboot.enums.UserRole;
import com.etnopolino.Task_springboot.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    @PostConstruct
    public void createAdminAccount(){
       Optional<User> optionalUser = userRepository.findByUserRole(UserRole.ADMIN);

       if(optionalUser.isEmpty()){
           User user = new User();
           user.setEmail("admin@test.com");
           user.setName("admin");
           user.setPassword(new BCryptPasswordEncoder().encode("admin"));
           user.setUserRole(UserRole.ADMIN);
           userRepository.save(user);
           System.out.println("Admin account created successfully !");
       }else{
           System.out.println("Admin account already exist!");
       }
    }

    @Override
    public UserDto signupUser(SignRequest signRequest) {
        User user = new User();
        user.setEmail(signRequest.getEmail());
        user.setName(signRequest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signRequest.getPassword()));
        user.setUserRole(UserRole.EMPLOYEE);
        User createdUser = userRepository.save(user);

        return createdUser.getUserDto();
    }

    @Override
    public boolean hasEmailWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
}
