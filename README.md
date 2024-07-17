**User's guide**

how to launch the application:
1) clone the repository, create a database and run the application
2) launch docker (docker-composer up), having previously launched the rental (mvn package)

In this application, there is a Spring Security with JWT tool and a division into roles. 
There are 2 roles in this project (user, administrator).
The user has access to registration and login and to all requests that are related to the project. 
The administrator has access to all requests related to the user. 
When creating a user, the user role is automatically assigned. 
To change the user role, you need to change the data in the table to admin using Intellij Idea or adminer

**requests to the model Project**
  - POST - save project: http://localhost:8082/api/v1/projects;
  - GET - get project by id: http://localhost:8082/api/v1/projects/id;
  - GET - get all projects: http://localhost:8082/api/v1/projects;
  - PUT - update project by id: http://localhost:8082/api/v1/projects/id;
  - DELETE - delete project by id: http://localhost:8082/api/v1/projects/id;

**requests to the model User**
  - POST - user registration:  http://localhost:8082/api/v1/users/registration
  - POST - user logn: http://localhost:8082/api/v1/users/login
  - GET - get user by id: http://localhost:8082/api/v1/users/id
  - GET - get all users: http://localhost:8082/api/v1/users
  - PUT - update user by id: http://localhost:8082/api/v1/users/id
  - DELETE - delete user by id: http://localhost:8082/api/v1/users/id

**example of the request body for request 'save project'**
          _{
              "title": "New Project",
              "description": "Description of the new project",
              "user": {
          			"id": 3,
          			"username": "Guest2",
          			"password": "$2a$10$czgz947XV3aMwV6U0vojPeh2y3wVPxEV5qwMEknQsDEYKtcMbc/IG",
          			"email": "guest2@gmail.com",
          			"roles": [
          					"USER"
          			]
              }
          }_
          
**example of the request body for request 'update project'**
_          _{
               "title": "update Project",
              "description": "Description of the update project"
          }_

**example of the request body for request 'user registration'**
          _{
              "username": "Guest2",
              "email": "guest2@gmail.com",
              "password": "2222"
          }_

**example of the request body for request 'user logn'**
_          {
              "username": "Guest2",
              "password": "2222"
          }_

**example of the request body for request 'update user by id'**
_          {
              "name": "test",
              "email": "test@gmail.com"
          }_
