package org.flozdra.kanban.services;

import org.flozdra.kanban.models.Developer;

import java.util.List;
import java.util.Optional;

public interface DeveloperService {

    Developer createDeveloper(Developer developer);

    List<Developer> getDevelopers();

    Optional<Developer> getDeveloperById(Long id);

//    List<Developer> findDeveloper(String search);
}
