package com.piffer.first_spring_app.controllers;

import com.piffer.first_spring_app.entities.ToDoEntity;
import com.piffer.first_spring_app.services.ToDoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/todo")
public class ToDoController {

    @Autowired
    private ToDoService toDoServices;

    @GetMapping
    public List<ToDoEntity> getAllToDos() {
        return this.toDoServices.getAllToDo();
    }

    @PostMapping
    public ToDoEntity createToDo(
            @Valid @RequestBody ToDoEntity toDo
    ) {
        return this.toDoServices.createToDo(toDo);
    }

    @PutMapping("/{id}")
    public ToDoEntity updateToDo(
            @PathVariable UUID id,
            @RequestBody ToDoEntity toDo
    ) {
        return this.toDoServices.updateToDo(id, toDo);
    }

    @DeleteMapping("/{id}")
    public void deleteToDo(
            @PathVariable UUID id
    ) {
        this.toDoServices.deleteToDo(id);
    }

}
