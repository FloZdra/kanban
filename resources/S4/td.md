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

## Step 8

Read following docs to understand the basics of VueJs framework :

- [VueJs Guide](https://vuejs.org/v2/guide/)
- [Vue Router Guide](https://router.vuejs.org/)
- [Vuex Guide](https://vuex.vuejs.org/)
- [Vue CLI Guide](https://cli.vuejs.org/guide/)

Set up a new Vue Cli project, with store and router.

Install bootstrap and bootsrap-vue for basic UI components.

Set up the Home Vue component associated to route /.

## Step 9

Set up the router with :

- route to /developers leads to a Developers component, that in next steps will display the list of developers extracted from api
- route to /tasks leads to a Tasks component, that in next steps will display the list of tasks extracted from api
- route to /add_task leads to a AddTask component, that in next steps will display a form to enable task creation

## Step 10

Set up the store with the possiblity to have in the state :
- the tasks list
- the developers list
- the task status list
- the task types list

Initialize in the store the status and the types with dedicated actions and mutations

## Step 11

Develop the Developers component.
It displays the list of developers, in a table.
It loads the developers from the API, by triggering a VueX action (that triggers a mutation, and the developers are stored in the VueX store) 