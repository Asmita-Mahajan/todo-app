package com.todoapp.backend.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="task")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="task_id")
    @JsonProperty("task_id")
    private long taskId;

//    @NotBlank(message = "User ID is mandatory")
//    @Column(name="user_id", nullable=false)
//    @JsonProperty("user_id")
//    private String userId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonProperty("user_id")
    private User user;

    @NotBlank(message = "title is mandatory ")
    @Size(max=100, message="Title cannot exceed 100 characters")
    @Column(name = "tittle", length = 100, nullable = false)
    @JsonProperty("title")
    private String title;

    @Size(max = 100,message = "description cannot exceed 100 characters")
    @Column(name="description",length=100,nullable=false)
    @JsonProperty("description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name="status", length=20, nullable=false)
    @JsonProperty("status")
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name="priority", length=20)
    @JsonProperty("priority")
    private TaskPriority priority;

    @Column(name="due_date")
    @JsonProperty("due_date")
    private String dueDate;
}
