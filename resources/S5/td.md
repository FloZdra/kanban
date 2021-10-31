# Subject

This 5-session TD aims at developing a Kanban Board, mainly with the entities of Developers and Tasks.

Students will have to develop all layers of the application :
- domain layer with JPA Entities
- data layer with repositories
- service layer
- controller layer exposing a REST API
- front-end layer, displaying screens and accessing data via the REST API

During sessions 1 and 2, students will work on domain, data, and service layers.

During session 3, students will work on controller layer.

During sessions 4 and 5, students will work on front-end layer.

## Back-end stack

Back-end is structured around SpringBoot, and bootsrapped by Spring Initializr.

Domain layer will be implemented with JPA Entities.

Data layer will be implemented with JPA Repositories.

Service layer will be implemented with Spring Services.

Controller layer will be implemented with Spring Rest Controllers.

Unit tests will be implemented with JUnit and SpringBoot Test infrastructure.

Maven will be used as build tool for the back-end.

## Front-end stack

Front-end will be bult using VueJs and Vue-CLI bootsrapper.

# Input

Project will be launched without any input at session 1.

At session 3, a Maven project with functional service, data and domain layer will be provided, to provide appropriate layers for implementing controllers.

At session 4, a functional back-end will be provided, to provide appropriate layers for implementing front-end. 

# Output

The expected deliverable will be Git repo or a zip of your source code, with all your source files.

Send the URL of the Git repo or the zip by mail to your teacher : remy.girodon@gmail.com

Please send it at the end of session 2, then at the end of session 3, then at the end of session 5.

# Steps 

## Step 12

Develop the Tasks component.
It first displays in the first line the list of task status.
It then displays one task by line, putting it the corresponding status column.

## Step 13

Develop the AddTask component.
It first displays a form allowing to create a new Task.
User can choose :
- task title
- task nb hours forecast
- task nb hours real
- task developers
- task type (bug or feature)
When submitted, the task will be created with Status ToDo by trigerring a post request to back-end, with appropriate post data.
User will then be redirected to Tasks screen (and tasks list will be reloaded in the store by launching appropriate VueX action).

Then you can handle validation error messages, if for example a required field is missing : you then stay on the form and display the error.

## Step 14

Improve the Tasks component : for each Task displayed on the board, add "Move left" (except for Task with To Do status) and "Move right" (except for Task with Done status) buttons.

These buttons will trigger the appropriate API call for moving tasks to left and right respectively.