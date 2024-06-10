package com.etnopolino.Task_springboot.services.admin;

import com.etnopolino.Task_springboot.dto.TaskDto;
import com.etnopolino.Task_springboot.dto.UserDto;

import java.util.List;

public interface AdminService {

    List<UserDto> getUsers();

    TaskDto createTask(TaskDto taskDto);

    List<TaskDto> getAllTask();

    void deleteTask(Long id);

    TaskDto getTaskById(Long id);

    TaskDto updateTask(Long id, TaskDto taskDto);
}
