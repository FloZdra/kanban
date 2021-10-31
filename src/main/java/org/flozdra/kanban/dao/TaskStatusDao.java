package org.flozdra.kanban.dao;

import org.flozdra.kanban.models.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

// public interface ModelDao extends JpaRepository<Model, PrimaryKeyType> {
public interface TaskStatusDao extends JpaRepository<TaskStatus, Long> {
}
