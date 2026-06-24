package com.todoapp.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="user")
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    @JsonProperty("user_id")
    private long userId;

//    @NotBlank(message = "username is mandatory")
    @Size(max = 20, message = "Username cannot exceed 20 characters")
    @Column(name="user_name", length=42, unique=true, nullable=false)
    @JsonProperty("user_name")
    private String username;

    @JsonIgnore
    @Column(name="password", nullable=false)
    private String password;

    @Size(max=30,message = "email cannot exceed 30 characters")
    @Column(name = "email",length=30, nullable=false)
    @JsonProperty("email")
    private String email;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    @JsonProperty("created_date")
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "modified_date")
    @JsonProperty("modified_date")
    private Date modifiedDate;
}
