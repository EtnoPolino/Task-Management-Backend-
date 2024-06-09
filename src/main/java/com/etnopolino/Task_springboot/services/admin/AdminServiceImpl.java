package com.etnopolino.Task_springboot.services.admin;

import com.etnopolino.Task_springboot.dto.TaskDto;
import com.etnopolino.Task_springboot.dto.UserDto;
import com.etnopolino.Task_springboot.entities.Task;
import com.etnopolino.Task_springboot.entities.User;
import com.etnopolino.Task_springboot.enums.TaskStatus;
import com.etnopolino.Task_springboot.enums.UserRole;
import com.etnopolino.Task_springboot.repository.TaskRepository;
import com.etnopolino.Task_springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream()
                                       .filter(user -> user.getUserRole() == UserRole.EMPLOYEE)
                                       .map(User::getUserDto)
                                       .collect(Collectors.toList());
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        Optional<User> optionalUser = userRepository.findById(taskDto.getEmployeeId());

        if(optionalUser.isPresent()){
            Task task = new Task();
            task.setTitle(taskDto.getTitle());
            task.setDescription(taskDto.getDescription());
            task.setPriority(taskDto.getPriority());
            task.setDueDate(taskDto.getDueDate());
            task.setTaskStatus(TaskStatus.INPROGRESS);
            task.setUser(optionalUser.get());
            return taskRepository.save(task).getTaskDto();
        }
        return null;
    }

    @Override
    public List<TaskDto> getAllTask() {
        return taskRepository.findAll().stream()
                             .sorted(Comparator.comparing(Task::getDueDate).reversed())
                             .map(Task::getTaskDto)
                             .collect(Collectors.toList());
    }
}
