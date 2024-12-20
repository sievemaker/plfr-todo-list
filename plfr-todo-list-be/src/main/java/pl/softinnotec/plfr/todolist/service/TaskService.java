package pl.softinnotec.plfr.todolist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.softinnotec.plfr.todolist.entity.Task;
import pl.softinnotec.plfr.todolist.repository.TaskRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task addTask(Task task){
        return taskRepository.save(task);
    }

    public void deleteTaskById(UUID id){
        taskRepository.deleteById(id);
    }
}
