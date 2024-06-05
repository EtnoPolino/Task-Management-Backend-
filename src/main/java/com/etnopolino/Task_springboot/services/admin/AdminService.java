package com.etnopolino.Task_springboot.services.admin;

import com.etnopolino.Task_springboot.dto.UserDto;

import java.util.List;

public interface AdminService {

    List<UserDto> getUsers();
}
