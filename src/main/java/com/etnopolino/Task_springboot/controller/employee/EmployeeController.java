package com.etnopolino.Task_springboot.controller.employee;

import com.etnopolino.Task_springboot.dto.TaskDto;
import com.etnopolino.Task_springboot.services.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
@CrossOrigin(origins = "*") //on autorise toutes les requete qui vont venir du front end
public class EmployeeController {
    public final EmployeeService employeeService;

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getTasksByUserId(){
        return ResponseEntity.ok(employeeService.getTaskByUserId());
    }
}
