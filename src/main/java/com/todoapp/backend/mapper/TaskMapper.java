package com.todoapp.backend.mapper;


import com.todoapp.backend.dto.TaskDto;
import com.todoapp.backend.model.Task;
import org.mapstruct.*;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {
    // Entity → DTO
    TaskDto toDto(Task task);

    // DTO → Entity
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
}
