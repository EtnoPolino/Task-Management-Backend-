package com.etnopolino.Task_springboot.controller.employee;

import com.etnopolino.Task_springboot.dto.TaskDto;
import com.etnopolino.Task_springboot.services.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/task/{id}/{status}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @PathVariable String status){
        TaskDto updatedTaskDTO = employeeService.updateTask(id, status);
        if(updatedTaskDTO == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build() ;
        return ResponseEntity.ok(updatedTaskDTO);
    }


}
