package com.jmar.demo.service;

import com.jmar.demo.exceptions.ToDoException;
import com.jmar.demo.mapper.TaskInDTOToTask;
import com.jmar.demo.persistence.entity.Task;
import com.jmar.demo.persistence.entity.TaskStatus;
import com.jmar.demo.persistence.repository.TaskRepository;
import com.jmar.demo.service.dto.TaskInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository repository;
    private final TaskInDTOToTask mapper;

    public TaskService(TaskRepository repository, TaskInDTOToTask mapper) {
        this.repository = repository;
        this.mapper = mapper;
    } // tambien se podría usar con @Autowired, pero es buena practica hacerlo por constructor

    public Task createTask(TaskInDTO taskInDTO){
        // usamos dto, data transfer object
        Task task = mapper.map(taskInDTO);
        return this.repository.save(task);
    }

    public List<Task> findAll(){
        return this.repository.findAll();
    }

    public List<Task> findAllByTaskStatus(TaskStatus status){
        return this.repository.findAllByTaskStatus(status);
    }

    @Transactional
    public void updateTaskAsFinished(Long id) {
        Optional<Task> optionalTask = this.repository.findById(id);
        if (optionalTask.isEmpty()){
            throw new ToDoException(HttpStatus.NOT_FOUND, "Task not found");
        }

        this.repository.markTaskAsFinished(id);
    }

    @Transactional
    public void deleteById(Long id) {
        Optional<Task> optionalTask = this.repository.findById(id);
        if (optionalTask.isEmpty()){
            throw new ToDoException(HttpStatus.NOT_FOUND, "Task not found");
        }

        this.repository.deleteById(id);
    }

    
}
