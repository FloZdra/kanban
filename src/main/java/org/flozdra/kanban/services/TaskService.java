package org.flozdra.kanban.services;

import org.flozdra.kanban.dao.TaskDao;
import org.flozdra.kanban.models.Developer;
import org.flozdra.kanban.models.Task;
import org.flozdra.kanban.models.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskDao taskDao;

    @Autowired
    private TaskStatusService taskStatusService;

    public Collection<Task> findAllTasks() {
        return taskDao.findAll();
    }

    public Task createTask(Task task) {
        // Set backlog by default
        if (task.getStatus() == null) {
            task.setStatus(taskStatusService.findTaskStatusByLabel("Backlog").iterator().next());
        }
        return taskDao.save(task);
    }

    public Optional<Task> findTask(Long id) {
        return taskDao.findById(id);
    }

    public Task moveRightTask(Task task) {
        TaskStatus status = task.getStatus();
        TaskStatus nextStatus = switch (status.getLabel()) {
            case "Backlog" -> taskStatusService.findTaskStatusByLabel("To Do").iterator().next();
            case "To Do" -> taskStatusService.findTaskStatusByLabel("In progress").iterator().next();
            case "In progress" -> taskStatusService.findTaskStatusByLabel("Testing").iterator().next();
            case "Testing" -> taskStatusService.findTaskStatusByLabel("Done").iterator().next();
            default -> null;
        };
        if (nextStatus != null) {
            task.setStatus(nextStatus);
            return taskDao.save(task);
        }
        return task;
    }

    public Task moveLeftTask(Task task) {
        TaskStatus status = task.getStatus();
        TaskStatus previousStatus = switch (status.getLabel()) {
            case "Done" -> taskStatusService.findTaskStatusByLabel("Testing").iterator().next();
            case "Testing" -> taskStatusService.findTaskStatusByLabel("In progress").iterator().next();
            case "In progress" -> taskStatusService.findTaskStatusByLabel("To Do").iterator().next();
            case "To Do" -> taskStatusService.findTaskStatusByLabel("Backlog").iterator().next();
            default -> null;
        };
        if (previousStatus != null) {
            task.setStatus(previousStatus);
            return taskDao.save(task);
        }
        return task;
    }

    public Task assignDeveloperToTask(Task task, Developer developer) {
        task.addDeveloper(developer);
        return taskDao.save(task);
    }

    public boolean deleteTask(Long id) {
        Optional<Task> task = taskDao.findById(id);

        if (task.isEmpty()) {
            return false;
        }
        taskDao.delete(task.get());
        return true;
    }
}
