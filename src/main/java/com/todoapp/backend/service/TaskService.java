package com.todoapp.backend.service;

import com.todoapp.backend.dto.TaskDto;
import com.todoapp.backend.exception.TaskNotFoundException;
import com.todoapp.backend.mapper.TaskMapper;
import com.todoapp.backend.model.Task;
import com.todoapp.backend.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


//Pure Constructor Injection (Best Practice)
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskDto saveTask(TaskDto taskDto) {
        Task entity = taskMapper.toEntity(taskDto);
        Task saved = taskRepository.save(entity);
        return taskMapper.toDto(saved);
    }
    public List<TaskDto> getAllTasks() {
        return taskMapper.toDtoList(taskRepository.findAll());
    }
    public Optional<TaskDto> getTaskById(long id) {
        return taskRepository.findById(id).map(taskMapper::toDto);
    }
    public void deleteTaskById(long id) { taskRepository.deleteById(id); }

    public TaskDto updateTask(long id, TaskDto taskDto) {
        Task entity = taskRepository.findById(id)
//                .orElseThrow(()->new RuntimeException("Task not found"));
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found"));
        taskMapper.updateEntityFromDto(taskDto, entity);
        taskRepository.save(entity);
        return  taskMapper.toDto(entity);
    }


}

//Pure Field Injection
//@Service
//public class TaskService {
//    @Autowired
//    private TaskRepository taskRepository;
//
//    public Task saveTask(Task task) { return taskRepository.save(task); }
//    public List<Task> getAllTasks() { return taskRepository.findAll(); }
//    public Optional<Task> getTaskById(long id) { return taskRepository.findById(id); }
//    public void deleteTaskById(long id) { taskRepository.deleteById(id); }
//}
