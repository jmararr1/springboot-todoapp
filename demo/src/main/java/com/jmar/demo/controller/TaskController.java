package com.jmar.demo.controller;

import com.jmar.demo.persistence.entity.Task;
import com.jmar.demo.persistence.entity.TaskStatus;
import com.jmar.demo.service.TaskService;
import com.jmar.demo.service.dto.TaskInDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// con esta anotación, spring identifica que esto es un controlador
// la capa de controlador solo deberia comunicarse con la capa de servicio
// Es buena practica poner el mapping en plurar con respecto a la entidad de gestion task->tasks
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    // constructor para inicializar

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task createTask(@RequestBody TaskInDTO taskInDTO){
        return this.taskService.createTask(taskInDTO);
    }

    @GetMapping
    public List<Task> findAll(){
        return this.taskService.findAll();
    }

    @GetMapping("/status/{status}")
    public List<Task> findAllByStatus (@PathVariable("status")TaskStatus status) {
        return this.taskService.findAllByTaskStatus(status);
    }

    @PatchMapping("/mark_as_finished/{id}")
    public ResponseEntity<Void> markAsFinished(@PathVariable("id") Long id) {
        this.taskService.updateTaskAsFinished(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(@PathVariable("id") Long id) {
        this.taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
