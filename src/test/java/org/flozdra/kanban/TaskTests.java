package org.flozdra.kanban;

import org.flozdra.kanban.dao.TaskDao;
import org.flozdra.kanban.models.Developer;
import org.flozdra.kanban.models.Task;
import org.flozdra.kanban.models.TaskStatus;
import org.flozdra.kanban.services.DeveloperService;
import org.flozdra.kanban.services.TaskService;
import org.flozdra.kanban.services.TaskStatusService;
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
    private TaskStatusService taskStatusService;

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private DeveloperService developerService;

    /**
     * Test addDeveloper of Task model
     */
    @Test
    public void testAddDeveloperToTask() {
        Task task = new Task("Test task", 1, 0, new Date());
        Developer developer = new Developer("Developer", "Test", "p4sSw0rD&", "developer@test.com", new Date());

        task.addDeveloper(developer);

        assertTrue(task.getDevelopers().contains(developer));
    }

    /**
     * Test findAll() method of task DAO, should return 3
     * They are created in {@link KanbanApplication#populateTasks(TaskService)}
     */
    @Test
    public void testFindAllTasksDao() {
        Collection<Task> tasks = taskDao.findAll();
        assertEquals(3, tasks.size());
    }

    /**
     * Test save() method of task DAO
     */
    @Test
    public void testSaveTaskDao() {
        Task task = taskDao.save(new Task("Test task", 1, 0, new Date()));

        assertNotNull(task);
        assertNotNull(task.getId());

        // Delete it to clear DB
        taskDao.delete(task);
    }

    /**
     * Test findAll() method of task service, should return 3
     * They are created in {@link KanbanApplication#populateTasks(TaskService)}
     */
    @Test
    public void testFindAllTasksService() {
        Collection<Task> tasks = taskService.findAllTasks();
        assertEquals(3, tasks.size());
    }

    /**
     * Test findTask() method of task service
     */
    @Test
    public void testFindTask() {
        Task task = taskService.createTask(new Task("Test task", 1, 0, new Date()));

        Optional<Task> taskFound = taskService.findTask(task.getId());

        assertTrue(taskFound.isPresent());
        assertEquals(task.getId(), taskFound.get().getId());

        taskService.deleteTask(task.getId());
    }

    /**
     * Test moveRightTask() method of task service
     */
    @Test
    public void testMoveRightTask() {
        // Initialize task
        Task task = taskService.createTask(new Task("Test task", 1, 0, new Date()));
        TaskStatus taskStatus = taskStatusService.findTaskStatusByLabel("Backlog").iterator().next();
        task.setStatus(taskStatus);
        taskDao.save(task);

        taskService.moveRightTask(task);

        assertEquals("To Do", task.getStatus().getLabel());

        taskService.deleteTask(task.getId());
    }

    /**
     * Test moveLeftTask() method of task service
     */
    @Test
    public void testMoveLeftTask() {
        // Initialize task
        Task task = taskService.createTask(new Task("Test task", 1, 0, new Date()));
        TaskStatus taskStatus = taskStatusService.findTaskStatusByLabel("Done").iterator().next();
        task.setStatus(taskStatus);
        taskDao.save(task);

        taskService.moveLeftTask(task);

        assertEquals("Testing", task.getStatus().getLabel());

        taskService.deleteTask(task.getId());
    }

    /**
     * Create a task, then delete it
     *
     * @see TaskService#createTask(Task)
     * @see TaskService#deleteTask(Long)
     */
    @Test
    public void testAddDeleteTask() {
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
        Task task = taskService.findAllTasks().iterator().next();
        Developer developer = developerService.findAllDevelopers().iterator().next();

        // Assign developer to task
        taskService.assignDeveloperToTask(task, developer);
        assertTrue(task.getDevelopers().contains(developer));

        // Check if tasks have been successfully updated
        Optional<Task> taskSaved = taskService.findTask(task.getId());
        // Assert taskSaved exists before continue
        assertTrue(taskSaved.isPresent());
        // Get first developer of taskSaved
        assertEquals(developer.getId(), taskSaved.get().getDevelopers().iterator().next().getId());

        // Check if developer have been successfully updated
        Optional<Developer> developerSaved = developerService.findDeveloperById(developer.getId());
        // Assert developerSaved exists before continue
        assertTrue(developerSaved.isPresent());
        // Get first task of developerSaved
        assertEquals(task.getId(), developerSaved.get().getTasks().iterator().next().getId());
    }

}
