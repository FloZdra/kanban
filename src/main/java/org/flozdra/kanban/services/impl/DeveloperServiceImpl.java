package org.flozdra.kanban.services.impl;

import org.flozdra.kanban.dao.DeveloperDao;
import org.flozdra.kanban.models.Developer;
import org.flozdra.kanban.services.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeveloperServiceImpl implements DeveloperService {
    @Autowired
    private DeveloperDao developerDao;

    @Override
    public Developer createDeveloper(Developer developer) {
        return developerDao.save(developer);
    }

    @Override
    public List<Developer> getDevelopers() {
        return developerDao.findAll();
    }

    @Override
    public Optional<Developer> getDeveloperById(Long id) {
        return developerDao.findById(id);
    }

//    @Override
//    public List<Developer> findDeveloper(String search) {
//
//    }
}
