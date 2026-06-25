package com.todoapp.backend.mapper;


import com.todoapp.backend.dto.TaskDto;
import com.todoapp.backend.model.Task;
import com.todoapp.backend.model.TaskPriority;
import com.todoapp.backend.model.TaskStatus;
import org.mapstruct.*;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {
    // Entity → DTO
    @Mapping(source = "taskId", target = "taskId")
    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "priority", target = "priority")
    @Mapping(source = "dueDate", target = "dueDate")
    TaskDto toDto(Task task);

    // DTO → Entity
    @Mapping(source = "taskId", target = "taskId")
    @Mapping(source = "userId", target = "user.userId")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "priority", target = "priority")
    @Mapping(source = "dueDate", target = "dueDate")
    Task toEntity(TaskDto dto);


    // List conversions
    List<TaskDto> toDtoList(List<Task> entities);
    List<Task> toEntityList(List<TaskDto> dtos);

    // Update entity from DTO (PATCH style)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(TaskDto dto, @MappingTarget Task entity);

    // Example of a business rule after mapping
    @AfterMapping
    default void applyBusinessRules(@MappingTarget Task entity) {
        if (entity.getTitle() != null) {
            entity.setTitle(entity.getTitle().trim()); // trim whitespace
        }
    }
    // Custom conversions for enums
    default String mapStatus(TaskStatus status) {
        return status != null ? status.name() : null;
    }

    default TaskStatus mapStatus(String status) {
        return status != null ? TaskStatus.valueOf(status) : null;
    }

    default String mapPriority(TaskPriority priority) {
        return priority != null ? priority.name() : null;
    }

//    DTO → Entity
    default TaskPriority mapPriority(String priority) {
        return priority != null ? TaskPriority.valueOf(priority) : null;
//        return priority != null ? TaskPriority.valueOf(priority.toUpperCase()) : null;
    }
}
