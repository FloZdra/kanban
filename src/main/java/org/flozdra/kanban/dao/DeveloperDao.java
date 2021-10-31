package org.flozdra.kanban.dao;

import org.flozdra.kanban.models.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<Model, PrimaryKeyType> {
public interface DeveloperDao extends JpaRepository<Developer, Long> {
}
