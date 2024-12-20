package pl.softinnotec.plfr.todolist.dto;

import jakarta.validation.constraints.Size;

public record TaskCreateDTO(@Size(min = 3) String title, String description) {
}
