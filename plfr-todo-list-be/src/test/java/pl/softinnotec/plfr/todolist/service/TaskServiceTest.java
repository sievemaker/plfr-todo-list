package pl.softinnotec.plfr.todolist.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.softinnotec.plfr.todolist.entity.Task;
import pl.softinnotec.plfr.todolist.repository.TaskRepository;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService testedInstance;

    @Test
    void getAllTasks(){
        // given
        UUID _1stId = UUID.fromString("f6c37b3f-3804-45b4-b9b5-937eccbd13ef");
        UUID _2ndId = UUID.fromString("7f57e2cb-0fb1-4680-8c23-73a313055913");
        UUID _3rdId = UUID.fromString("ca55149e-d03e-442b-9cab-37c4f0fadbe9");

        // and
        Task _1stTask = new Task(_1stId, "Task 1", "Task 1 description");
        Task _2ndTask = new Task(_2ndId, "Task 2", "Task 1 description");
        Task _3rdTask = new Task(_3rdId, "Task 3", "Task 1 description");

        // and
        when(taskRepository.findAll()).thenReturn(List.of(_1stTask, _2ndTask, _3rdTask));

        // when
        List<Task> allTasks = testedInstance.getAllTasks();

        // then
        assertEquals(3, allTasks.size());
        assertEquals(_1stTask, allTasks.get(0));
        assertEquals(_2ndTask, allTasks.get(1));
        assertEquals(_3rdTask, allTasks.get(2));
    }

    @Test
    void addTask(){
        // given
        UUID _1stId = UUID.fromString("f6c37b3f-3804-45b4-b9b5-937eccbd13ef");
        Task _1stTask = new Task(null, "Task 1", "Task 1 description");
        Task _1stTaskSaved = new Task(_1stId, "Task 1", "Task 1 description");
        when(taskRepository.save(_1stTask)).thenReturn(_1stTaskSaved);

        // when
        Task addedTask = testedInstance.addTask(_1stTask);

        // then
        verify(taskRepository).save(_1stTask);
        assertEquals(_1stTaskSaved, addedTask);
    }

    @Test
    void deleteTaskById(){
        // given
        UUID _1stId = UUID.fromString("f6c37b3f-3804-45b4-b9b5-937eccbd13ef");

        // when
        testedInstance.deleteTaskById(_1stId);

        // then
        verify(taskRepository).deleteById(_1stId);
    }
}