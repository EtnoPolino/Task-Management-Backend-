package com.etnopolino.Task_springboot.repository;

import com.etnopolino.Task_springboot.dto.TaskDto;
import com.etnopolino.Task_springboot.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByTitleContaining(String title);
}
