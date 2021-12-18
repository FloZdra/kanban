package org.flozdra.kanban;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.flozdra.kanban.models.Task;
import org.flozdra.kanban.services.DeveloperService;
import org.flozdra.kanban.services.TaskService;
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

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class Direction {
    public String direction;

    Direction(String d) {
        this.direction = d;
    }
}

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
class TaskRestTests {
    @Autowired
    private MockMvc mockMvc;

    /**
     * Test GET /tasks endpoint
     * They are created in {@link KanbanApplication#populateTasks(TaskService)}
     */
    @Test
    public void testFindAllTasksRest() throws Exception {
        this.mockMvc.perform(get("/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title", is("Create Task table in database")))
                .andExpect(jsonPath("$[0].nbHoursForecast", is(1)))
                .andExpect(jsonPath("$[0].nbHoursReal", is(0)))
        ;
    }

    /**
     * Test GET /tasks/{id} endpoint with id=1 (Task: "Create Task table in database")
     * They are created in {@link KanbanApplication#populateTasks(TaskService)}
     */
    @Test
    public void testFindTaskRest() throws Exception {
        this.mockMvc.perform(get("/tasks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is("Create Task table in database")))
                .andExpect(jsonPath("$.nbHoursForecast", is(1)))
                .andExpect(jsonPath("$.nbHoursReal", is(0)))
        ;
    }

    /**
     * Test POST /tasks endpoint
     */
    @Test
    public void testCreateTaskRest() throws Exception {
        Task task = new Task("Test POST /tasks", 2, 1, new Date());

        this.mockMvc.perform(post("/tasks")
                .content(asJsonString(task))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is(task.getTitle())))
                .andExpect(jsonPath("$.nbHoursForecast", is(task.getNbHoursForecast())))
                .andExpect(jsonPath("$.nbHoursReal", is(task.getNbHoursReal())))
        ;
    }

    /**
     * Test PATCH /tasks/1 endpoint
     * Move task from Backlog to To Do
     */
    @Test
    public void testPatchTaskRest() throws Exception {
        Direction dir = new Direction("RIGHT");

        this.mockMvc.perform(patch("/tasks/1")
                .content(asJsonString(dir))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.status.label", is("To Do")))
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
