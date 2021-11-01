package org.flozdra.kanban.dao;

import org.flozdra.kanban.models.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

// public interface ModelDao extends JpaRepository<Model, PrimaryKeyType> {
public interface TaskStatusDao extends JpaRepository<TaskStatus, Long> {

    Collection<TaskStatus> findByLabel(String label);
}
