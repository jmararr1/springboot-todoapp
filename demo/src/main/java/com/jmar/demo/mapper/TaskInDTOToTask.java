package com.jmar.demo.mapper;

import com.jmar.demo.persistence.entity.Task;
import com.jmar.demo.persistence.entity.TaskStatus;
import com.jmar.demo.service.dto.TaskInDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

// con @component se define como un componente de spring para poder inyectarlo posteriormente
@Component
public class TaskInDTOToTask implements IMapper<TaskInDTO, Task>{
    @Override
    public Task map(TaskInDTO in) {
        Task task = new Task();
        task.setTitle(in.getTitle());
        task.setDescription(in.getDescription());
        task.setEta(in.getEta());
        task.setCreatedDate(LocalDateTime.now());
        task.setFinished(false);
        task.setTaskStatus(TaskStatus.ON_TIME);

        return task;
    }
}
