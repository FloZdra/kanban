package org.flozdra.kanban.dao;

import org.flozdra.kanban.models.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;

// public interface ModelDao extends JpaRepository<Model, PrimaryKeyType> {
public interface TaskTypeDao extends JpaRepository<TaskType, Long> {
}
