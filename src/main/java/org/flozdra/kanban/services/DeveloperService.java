package org.flozdra.kanban.services;

import org.flozdra.kanban.dao.DeveloperDao;
import org.flozdra.kanban.models.Developer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class DeveloperService {
    @Autowired
    private DeveloperDao developerDao;

    public Developer createDeveloper(Developer developer) {
        return developerDao.save(developer);
    }

    public Collection<Developer> getDevelopers() {
        return developerDao.findAll();
    }

    public Optional<Developer> getDeveloperById(Long id) {
        return developerDao.findById(id);
    }

    public boolean deleteDeveloper(Long id) {
        Optional<Developer> developer = developerDao.findById(id);

        if (developer.isEmpty()) {
            return false;
        }
        developerDao.delete(developer.get());
        return true;
    }
}
