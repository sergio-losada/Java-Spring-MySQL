# Java-Spring-MySQL
Implementation of a REST API service using Java Spring Boot and MySQL.
Unit tests have been implemented with Mockito and JUnit5 with WebTestClient, whereas Testcontainers have been used for the integration tests.

[REMINDER]: in order to get the tests running, you may have to change your MySQL user & password in the application.properties file. Otherwise, the MySQL Container will not launch.

## ENDPOINTS
    GET /user
    POST /user
    PUT /user/{userId}
    DELETE /user/{userId}
    GET /user/{userId}

## DTO DETAIL
The DTO modeled is a representation of a basic user profile. 
It can be applied to any social media and has the following attributes available
- userId
- name
- status (inactive, away, active, online)
- description
- initDate (Profile's creation date)
- lastModDate (Profile's last modification date) 
- image (User's profile picture)