package org.flozdra.kanban.services;

import org.flozdra.kanban.dao.TaskDao;
import org.flozdra.kanban.models.Developer;
import org.flozdra.kanban.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskDao taskDao;

    public Task createTask(Task task) {
        return taskDao.save(task);
    }

    public Collection<Task> getTasks() {
        return taskDao.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskDao.findById(id);
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
