package com.todoapp.backend.controller;

import com.todoapp.backend.dto.TaskDto;
import com.todoapp.backend.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private  final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDto> saveTask(@RequestBody  TaskDto taskDto) {
    TaskDto savedTask= taskService.saveTask(taskDto);
    return ResponseEntity.ok().body(savedTask);
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTask(){
    List<TaskDto> tasks= taskService.getAllTasks();
    return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getAllTaskById( @PathVariable Long id){
        Optional<TaskDto> task = taskService.getTaskById(id);
        return task.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable long id, @RequestBody   TaskDto taskDto) {
        taskService.updateTask(id,taskDto);
        return ResponseEntity.ok().body(taskDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }
}