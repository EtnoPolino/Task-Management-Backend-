package com.etnopolino.Task_springboot.services.employee;

import com.etnopolino.Task_springboot.dto.TaskDto;
import com.etnopolino.Task_springboot.entities.Task;
import com.etnopolino.Task_springboot.entities.User;
import com.etnopolino.Task_springboot.enums.TaskStatus;
import com.etnopolino.Task_springboot.repository.TaskRepository;
import com.etnopolino.Task_springboot.utils.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    private final TaskRepository taskRepository;
    private final JwtUtil jwtUtil;

    @Override
    public List<TaskDto> getTaskByUserId() {
        User user = jwtUtil.getLoggedInUser();

        if(user != null){
            return taskRepository.findAllByUserId(user.getId()).stream()
                                                        .sorted(Comparator.comparing(Task::getDueDate).reversed())
                                                        .map(Task::getTaskDto)
                                                        .collect(Collectors.toList());
        }

        throw new EntityNotFoundException("User not found");
    }

    @Override
    public TaskDto updateTask(Long id, String status) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if(optionalTask.isPresent()){
            Task existingTask = optionalTask.get();
            existingTask.setTaskStatus(mapStringToTaskStatus(status));
            return taskRepository.save(existingTask).getTaskDto();

        }
        throw new EntityNotFoundException("Task not found");
    }

    private TaskStatus mapStringToTaskStatus(String status){
        return switch(status){
            case "PENDING" -> TaskStatus.PENDING;
            case "INPROGRESS" -> TaskStatus.INPROGRESS;
            case "COMPLETED" -> TaskStatus.COMPLETED;
            case "DEFERRED" -> TaskStatus.DEFERRED;
            default -> TaskStatus.CANCELED;
        };
    }
}
