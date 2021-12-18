package org.flozdra.kanban.controllers;

import org.flozdra.kanban.models.Task;
import org.flozdra.kanban.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

class Direction {
    public String direction;
}

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        super();
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public Collection<Task> findAllTasks() {
        return taskService.findAllTasks();
    }

    @GetMapping("/tasks/{id}")
    public Task findTask(@PathVariable Long id) {
        Optional<Task> task = taskService.findTask(id);

        if (task.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Resource not found"
            );
        }
        return task.get();
    }

    @PostMapping("/tasks")
    public Task createTask(@Valid @RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PatchMapping("/tasks/{id}")
    public Task moveTask(@Valid @RequestBody Direction d, @PathVariable Long id) {
        Optional<Task> task = taskService.findTask(id);

        if (task.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Resource not found"
            );
        }
        if (d.direction == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Property direction is needed"
            );
        }

        if (d.direction.equals("LEFT")) {
            return taskService.moveLeftTask(task.get());
        } else if (d.direction.equals("RIGHT")) {
            return taskService.moveRightTask(task.get());
        }

        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Bad direction: should be LEFT or RIGHT"
        );
    }
}
