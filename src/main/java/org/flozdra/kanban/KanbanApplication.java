package org.flozdra.kanban;

import org.flozdra.kanban.models.Developer;
import org.flozdra.kanban.services.DeveloperService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class KanbanApplication {

    public static void main(String[] args) {
        SpringApplication.run(KanbanApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext applicationContext, DeveloperService developerService) {
        return args -> {
            createDevelopers(developerService);

            String[] noms = applicationContext.getBeanDefinitionNames();

            for (String nom : noms) {
                System.out.println(nom + " : " + applicationContext.getBean(nom).getClass().toString());
            }
        };
    }

    private void createDevelopers(DeveloperService developerService) {
        // Create developer
        Developer developer1 = developerService.createDeveloper(
                new Developer("Florian", "Zdrada", "p4sSw0rD",
                        "florian.zdrada@gmail.com", new Date()));
        System.out.println("New developer created : " + developer1.getFullName());

        Developer developer2 = developerService.createDeveloper(
                new Developer("Domino", "Lechat", "d0mSs42",
                        "domino@catmail.com", new Date()));
        System.out.println("New developer created : " + developer2.getFullName());
    }
}
