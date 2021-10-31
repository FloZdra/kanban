package org.flozdra.kanban;

import org.flozdra.kanban.models.Developer;
import org.flozdra.kanban.services.DeveloperService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DeveloperTests {
    @Autowired
    private DeveloperService developerService;

    private Developer developerTest;

    @Test
    public void testAddDeveloper() {
        String firstname = "Developer";
        String lastname = "Test";
        String password = "p4sSw0rD&";
        String email = "devleoper@test.fr";
        Date startContract = new Date();
        developerTest = developerService.createDeveloper(
                new Developer(firstname, lastname, password, email, startContract));

        assertNotNull(developerTest);
        assertNotNull(developerTest.getId());
        assertEquals(developerTest.getFirstname(), firstname);
        assertEquals(developerTest.getLastname(), lastname);
        assertEquals(developerTest.getEmail(), email);
        assertEquals(developerTest.getStartContract(), startContract);
    }
}
