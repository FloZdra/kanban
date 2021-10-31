package org.flozdra.kanban;

import org.flozdra.kanban.models.Developer;
import org.flozdra.kanban.models.Task;
import org.flozdra.kanban.models.TaskStatus;
import org.flozdra.kanban.models.TaskType;
import org.flozdra.kanban.services.DeveloperService;
import org.flozdra.kanban.services.TaskService;
import org.flozdra.kanban.services.TaskStatusService;
import org.flozdra.kanban.services.TaskTypeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SpringBootApplication
public class KanbanApplication {

    public static void main(String[] args) {
        SpringApplication.run(KanbanApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext applicationContext, DeveloperService developerService, TaskService taskService, TaskStatusService taskStatusService, TaskTypeService taskTypeService) {
        return args -> {
            populateTaskTypeStatus(taskStatusService, taskTypeService);
            populateDevelopers(developerService);
            populateTasks(taskService);

            String[] noms = applicationContext.getBeanDefinitionNames();

            for (String nom : noms) {
                System.out.println(nom + " : " + applicationContext.getBean(nom).getClass().toString());
            }
        };
    }


    public static void populateTaskTypeStatus(TaskStatusService taskStatusService, TaskTypeService taskTypeService) {
        List<TaskStatus> taskStatuses = new ArrayList<>();
        taskStatuses.add(taskStatusService.createTaskStatus(new TaskStatus("Backlog")));
        taskStatuses.add(taskStatusService.createTaskStatus(new TaskStatus("To Do")));
        taskStatuses.add(taskStatusService.createTaskStatus(new TaskStatus("In progress")));
        taskStatuses.add(taskStatusService.createTaskStatus(new TaskStatus("Testing")));
        taskStatuses.add(taskStatusService.createTaskStatus(new TaskStatus("Done")));

        List<TaskType> taskTypes = new ArrayList<>();
        taskTypes.add(taskTypeService.createTaskType(new TaskType("Database")));
        taskTypes.add(taskTypeService.createTaskType(new TaskType("Backend")));
        taskTypes.add(taskTypeService.createTaskType(new TaskType("Frontend")));

        for (TaskStatus taskStatus : taskStatuses) {
            System.out.println("New task status created : " + taskStatus.getLabel());
        }
        for (TaskType taskType : taskTypes) {
            System.out.println("New task type created : " + taskType.getLabel());
        }
    }

    public static void populateDevelopers(DeveloperService developerService) {
        List<Developer> developers = new ArrayList<>();

        developers.add(new Developer("Florian", "Zdrada", "p4sSw0rD", "florian.zdrada@kanban.fr", new Date()));
        developers.add(new Developer("Kamil", "Caglar", "p4sSw0rD", "kamil.caglar@kanban.fr", new Date()));
        developers.add(new Developer("Alexandre", "Anastassiades", "p4sSw0rD", "alexandre.anas@kanban.fr", new Date()));

        for (Developer developer : developers) {
            developerService.createDeveloper(developer);
            System.out.println("New developer created : " + developer.getFullName());
        }

    }

    public static void populateTasks(TaskService taskService) {
        List<Task> tasks = new ArrayList<>();

        tasks.add(new Task("Create Task table in database", 1, 0, new Date()));
        tasks.add(new Task("Add /developer/search endpoint", 2, 0, new Date()));
        tasks.add(new Task("Sort task by date option in kanban", 2, 0, new Date()));

        for (Task task : tasks) {
            taskService.createTask(task);
            System.out.println("New task created : " + task.getTitle());
        }
    }
}
