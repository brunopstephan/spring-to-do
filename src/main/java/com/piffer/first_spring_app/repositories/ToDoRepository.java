package com.piffer.first_spring_app.repositories;

import com.piffer.first_spring_app.entities.ToDoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ToDoRepository extends JpaRepository<ToDoEntity, UUID> {
}
