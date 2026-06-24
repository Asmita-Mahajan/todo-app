package com.todoapp.backend.model;

public enum TaskStatus {
    PENDING,      // Task created but not started
    IN_PROGRESS,  // Task is currently being worked on
    COMPLETED,    // Task finished successfully
    CANCELLED     // Task was cancelled or dropped
}
