package pl.softinnotec.plfr.todolist.mapper;

import org.mapstruct.Mapper;
import pl.softinnotec.plfr.todolist.dto.TaskCreateDTO;
import pl.softinnotec.plfr.todolist.dto.TaskDTO;
import pl.softinnotec.plfr.todolist.entity.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDTO toTaskDTO(Task task);
    Task toTask(TaskCreateDTO taskDTO);
}
