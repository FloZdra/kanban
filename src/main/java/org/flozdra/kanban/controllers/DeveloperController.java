package org.flozdra.kanban.controllers;

import org.flozdra.kanban.models.Developer;
import org.flozdra.kanban.models.Task;
import org.flozdra.kanban.services.DeveloperService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DeveloperController {
    private final DeveloperService developerService;

    public DeveloperController(DeveloperService developerService) {
        super();
        this.developerService = developerService;
    }

    @GetMapping("/developers")
    public Collection<Developer> findAllDevelopers() {
        return developerService.findAllDevelopers();
    }

    @GetMapping("/developers/{id}")
    public Optional<Developer> findDeveloper(@PathVariable Long id) {
        return developerService.findDeveloperById(id);
    }

    @PostMapping("/developers")
    public Developer createDeveloper(@Valid @RequestBody Developer developer) {
        return developerService.createDeveloper(developer);
    }
}
