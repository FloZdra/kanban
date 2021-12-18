package org.flozdra.kanban;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.flozdra.kanban.models.Developer;
import org.flozdra.kanban.models.Task;
import org.flozdra.kanban.services.DeveloperService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
class DeveloperRestTests {
    @Autowired
    private MockMvc mockMvc;

    /**
     * Test GET /developers endpoint
     * They are created in {@link KanbanApplication#populateDevelopers(DeveloperService)}
     */
    @Test
    public void testFindAllDevelopersRest() throws Exception {
        this.mockMvc.perform(get("/developers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].firstname", is("Florian")))
                .andExpect(jsonPath("$[0].lastname", is("Zdrada")))
                .andExpect(jsonPath("$[0].password", is("p4sSw0rD")))
                .andExpect(jsonPath("$[0].email", is("florian.zdrada@kanban.fr")))
        ;
    }

    /**
     * Test GET /developers/{id} endpoint with id=1 (Developer: Florian)
     * They are created in {@link KanbanApplication#populateDevelopers(DeveloperService)}
     */
    @Test
    public void testFindDevelopersByIdRest() throws Exception {
        this.mockMvc.perform(get("/developers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstname", is("Florian")))
                .andExpect(jsonPath("$.lastname", is("Zdrada")))
                .andExpect(jsonPath("$.password", is("p4sSw0rD")))
                .andExpect(jsonPath("$.email", is("florian.zdrada@kanban.fr")))
        ;
    }

    /**
     * Test POST /developers endpoint
     */
    @Test
    public void testCreateDeveloperRest() throws Exception {

        Developer developer = new Developer("Developer", "Test", "p4sSw0rD&", "developer@test.com", new Date());

        this.mockMvc.perform(post("/developers")
                .content(asJsonString(developer))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstname", is(developer.getFirstname())))
                .andExpect(jsonPath("$.lastname", is(developer.getLastname())))
                .andExpect(jsonPath("$.email", is(developer.getEmail())))
        ;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
