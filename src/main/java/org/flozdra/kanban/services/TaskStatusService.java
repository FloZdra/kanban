package org.flozdra.kanban.services;

import org.flozdra.kanban.dao.TaskStatusDao;
import org.flozdra.kanban.models.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TaskStatusService {
    @Autowired
    private TaskStatusDao taskStatusDao;

    public TaskStatus createTaskStatus(TaskStatus taskStatus) {
        return taskStatusDao.save(taskStatus);
    }

    public Collection<TaskStatus> findTaskStatusByLabel(String label) {
        return taskStatusDao.findByLabel(label);
    }
}
