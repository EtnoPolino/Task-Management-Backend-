package com.etnopolino.Task_springboot.services.employee;

import com.etnopolino.Task_springboot.dto.TaskDto;

import java.util.List;

public interface EmployeeService {
    List<TaskDto> getTaskByUserId();

    TaskDto updateTask(Long id, String status);
}
