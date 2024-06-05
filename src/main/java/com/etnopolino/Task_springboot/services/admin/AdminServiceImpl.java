package com.etnopolino.Task_springboot.services.admin;

import com.etnopolino.Task_springboot.dto.UserDto;
import com.etnopolino.Task_springboot.entities.User;
import com.etnopolino.Task_springboot.enums.UserRole;
import com.etnopolino.Task_springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final UserRepository userRepository;

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream()
                                       .filter(user -> user.getUserRole() == UserRole.EMPLOYEE)
                                       .map(User::getUserDto)
                                       .collect(Collectors.toList());
    }
}
