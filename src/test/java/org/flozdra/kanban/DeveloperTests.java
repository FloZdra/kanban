package org.flozdra.kanban;

import org.flozdra.kanban.dao.DeveloperDao;
import org.flozdra.kanban.models.Developer;
import org.flozdra.kanban.services.DeveloperService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeveloperTests {
    @Autowired
    private DeveloperService developerService;

    @Autowired
    private DeveloperDao developerDao;

    /**
     * Create a developer, then delete it
     *
     * @see DeveloperService#createDeveloper(Developer)
     * @see DeveloperService#deleteDeveloper(Long)
     */
    @Test
    public void testAddDeleteDeveloper() {
        Developer developer = developerService.createDeveloper(
                new Developer("Developer", "Test", "p4sSw0rD&", "developer@test.com", new Date()));

        assertNotNull(developer);
        assertNotNull(developer.getId());
        assertEquals("Developer", developer.getFirstname());

        // Delete
        assertTrue(developerService.deleteDeveloper(developer.getId()));
    }

    /**
     * Find a created developer
     */
    @Test
    public void testFindDeveloper() {
        // Create developer
        Developer developer = developerService.createDeveloper(
                new Developer("Developer", "Test", "p4sSw0rD&", "developer@test.com", new Date()));


        // Test find developer
        Optional<Developer> developerFound = developerService.getDeveloperById(developer.getId());

        assertTrue(developerFound.isPresent());
        assertEquals(developer.getFirstname(), developerFound.get().getFirstname());

        // Delete it
        developerService.deleteDeveloper(developer.getId());
    }

    /**
     * Find all developers, should return 3
     * They are created in {@link KanbanApplication#populateDevelopers(DeveloperService)}
     */
    @Test
    public void testFindAllDevelopers() {
        Collection<Developer> developersFound = developerService.getDevelopers();
        assertEquals(3, developersFound.size());
    }

}
