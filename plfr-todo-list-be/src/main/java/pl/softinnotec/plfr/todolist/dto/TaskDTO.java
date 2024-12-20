package pl.softinnotec.plfr.todolist.dto;

import java.util.UUID;

public record TaskDTO(UUID id, String title, String description) {
}
