package org.flozdra.kanban.dao;

import org.flozdra.kanban.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

// public interface ModelDao extends JpaRepository<Model, PrimaryKeyType> {
public interface TaskDao extends JpaRepository<Task, Long> {
}
