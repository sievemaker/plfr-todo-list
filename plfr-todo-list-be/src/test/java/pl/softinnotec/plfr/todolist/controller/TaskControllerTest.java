package pl.softinnotec.plfr.todolist.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.softinnotec.plfr.todolist.dto.TaskCreateDTO;
import pl.softinnotec.plfr.todolist.dto.TaskDTO;
import pl.softinnotec.plfr.todolist.entity.Task;
import pl.softinnotec.plfr.todolist.mapper.TaskMapper;
import pl.softinnotec.plfr.todolist.service.TaskService;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskController testedInstance;

    @Test
    void getAllTasks(){
        // given
        UUID _1stId = UUID.fromString("f6c37b3f-3804-45b4-b9b5-937eccbd13ef");
        UUID _2ndId = UUID.fromString("7f57e2cb-0fb1-4680-8c23-73a313055913");
        UUID _3rdId = UUID.fromString("ca55149e-d03e-442b-9cab-37c4f0fadbe9");

        // and
        TaskDTO _1stTaskDTO = new TaskDTO(_1stId, "Task 1", "Task 1 description");
        TaskDTO _2ndTaskDTO = new TaskDTO(_2ndId, "Task 2", "Task 1 description");
        TaskDTO _3rdTaskDTO = new TaskDTO(_3rdId, "Task 3", "Task 1 description");

        // and
        Task _1stTask = new Task(_1stId, "Task 1", "Task 1 description");
        Task _2ndTask = new Task(_2ndId, "Task 2", "Task 1 description");
        Task _3rdTask = new Task(_3rdId, "Task 3", "Task 1 description");

        // and
        when(taskService.getAllTasks()).thenReturn(List.of(_1stTask, _2ndTask, _3rdTask));
        when(taskMapper.toTaskDTO(_1stTask)).thenReturn(_1stTaskDTO);
        when(taskMapper.toTaskDTO(_2ndTask)).thenReturn(_2ndTaskDTO);
        when(taskMapper.toTaskDTO(_3rdTask)).thenReturn(_3rdTaskDTO);

        // when
        List<TaskDTO> allTasks = testedInstance.getAllTasks().getBody();

        // then
        assertEquals(3, allTasks.size());
        assertEquals(_1stTaskDTO, allTasks.get(0));
        assertEquals(_2ndTaskDTO, allTasks.get(1));
        assertEquals(_3rdTaskDTO, allTasks.get(2));
    }

    @Test
    void addTask(){
        // given
        TaskCreateDTO _1stTaskDTO = new TaskCreateDTO("Task 1", "Task 1 description");
        Task _1stTask = new Task(null, "Task 1", "Task 1 description");

        // and
        UUID _1stId = UUID.fromString("f6c37b3f-3804-45b4-b9b5-937eccbd13ef");
        Task createdTask = new Task(_1stId, "Task 1", "Task 1 description");
        TaskDTO createdTaskDTO = new TaskDTO(_1stId, "Task 1", "Task 1 description");


        // and
        when(taskMapper.toTask(_1stTaskDTO)).thenReturn(_1stTask);
        when(taskService.addTask(_1stTask)).thenReturn(createdTask);
        when(taskMapper.toTaskDTO(createdTask)).thenReturn(createdTaskDTO);

        // when
        TaskDTO addedTask = testedInstance.addTask(_1stTaskDTO).getBody();

        // then
        verify(taskService).addTask(_1stTask);
        verify(taskMapper).toTask(_1stTaskDTO);
        assertEquals(createdTaskDTO, addedTask);
    }

    @Test
    void deleteTask(){
        // given
        UUID _1stId = UUID.fromString("f6c37b3f-3804-45b4-b9b5-937eccbd13ef");

        // when
        testedInstance.deleteTask(_1stId);

        // then
        verify(taskService).deleteTaskById(_1stId);
    }
}