package pl.softinnotec.plfr.todolist.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.softinnotec.plfr.todolist.dto.TaskCreateDTO;
import pl.softinnotec.plfr.todolist.dto.TaskDTO;
import pl.softinnotec.plfr.todolist.entity.Task;
import pl.softinnotec.plfr.todolist.mapper.TaskMapper;
import pl.softinnotec.plfr.todolist.service.TaskService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    private final TaskMapper taskMapper;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks(){
        List<TaskDTO> allTasks = taskService.getAllTasks().stream().map(taskMapper::toTaskDTO).collect(Collectors.toUnmodifiableList());
        return ResponseEntity.ok(allTasks);
    }

    @PostMapping
    public ResponseEntity<TaskDTO> addTask(@Valid @RequestBody TaskCreateDTO task){
        Task addedTask = taskService.addTask(taskMapper.toTask(task));
        return ResponseEntity.status(HttpStatus.CREATED).body(taskMapper.toTaskDTO(addedTask));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id){
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }
}
