package org.flozdra.kanban;

import org.flozdra.kanban.models.Developer;
import org.flozdra.kanban.models.Task;
import org.flozdra.kanban.services.DeveloperService;
import org.flozdra.kanban.services.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskTests {
    @Autowired
    private TaskService taskService;

    @Autowired
    private DeveloperService developerService;

    /**
     * Find all tasks, should return 3
     * They are created in {@link KanbanApplication#populateTasks(TaskService)}
     */
    @Test
    public void testFindAllTasks() {
        // Get first task of all tasks
        Collection<Task> tasks = taskService.getTasks();

        assertEquals(3, tasks.size());
    }

    /**
     * Create a task, then delete it
     *
     * @see TaskService#createTask(Task)
     * @see TaskService#deleteTask(Long)
     */
    @Test
    public void testAddDeleteDeveloper() {
        Task task = taskService.createTask(new Task("Test task", 1, 0, new Date()));

        assertNotNull(task);
        assertNotNull(task.getId());
        assertEquals("Test task", task.getTitle());

        // Delete
        assertTrue(taskService.deleteTask(task.getId()));
    }

    /**
     * Get a task and a developer, then assign him ot the task
     *
     * @see TaskService#assignDeveloperToTask(Task, Developer)
     */
    @Test
    public void testAssignDeveloperToTask() {
        // Get task and developer
        Task task = taskService.getTasks().iterator().next();
        Developer developer = developerService.getDevelopers().iterator().next();

        // Assign developer to task
        taskService.assignDeveloperToTask(task, developer);
        assertTrue(task.getDevelopers().contains(developer));

        // Check if tasks have been successfully updated
        Optional<Task> taskSaved = taskService.getTaskById(task.getId());
        // Assert taskSaved exists before continue
        assertTrue(taskSaved.isPresent());
        // Get first developer of taskSaved
        assertEquals(developer.getId(), taskSaved.get().getDevelopers().iterator().next().getId());

        // Check if developer have been successfully updated
        Optional<Developer> developerSaved = developerService.getDeveloperById(developer.getId());
        // Assert developerSaved exists before continue
        assertTrue(developerSaved.isPresent());
        // Get first task of developerSaved
        assertEquals(task.getId(), developerSaved.get().getTasks().iterator().next().getId());
    }

}
