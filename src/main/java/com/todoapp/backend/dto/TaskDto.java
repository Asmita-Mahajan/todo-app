package com.todoapp.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskDto {

    @JsonProperty("task_id")
    private long taskId;

    @JsonProperty("user_id")
    private long userId; // Instead of full User object, just expose the ID

    @NotBlank(message = "title is mandatory")
    @Size(max = 100, message = "title cannot exceed 100 characters")
    @JsonProperty("title")
    private String title;

    @Size(max = 100, message = "Description cannot exceed 100 characters")
    @JsonProperty("description")
    private String description;

    @NotBlank(message = "Status is mandatory")
    @JsonProperty("status")
    private String status;

    @JsonProperty("priority")
    private String priority;

    @JsonProperty("due_date")
    private String dueDate;






}
