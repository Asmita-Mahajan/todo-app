package com.todoapp.backend.repository;

import com.todoapp.backend.model.Task;
import com.todoapp.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
