package org.flozdra.kanban.services;

import org.flozdra.kanban.dao.TaskTypeDao;
import org.flozdra.kanban.models.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskTypeService {
    @Autowired
    private TaskTypeDao taskTypeDao;

    public TaskType createTaskType(TaskType taskType) {
        return taskTypeDao.save(taskType);
    }
}
