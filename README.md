# API Lead Management System
Solution for the subjected problem

#Assumptiton
```
1. In case of an Update Request, it is assumed that fields that need to be updated will be the part of request object only.
2. In case of an Mark a Lead, it is assumed that if no lead is present then it would respond with 404(Not Found).
```


# Spring Boot Maven

PROJECT START STEPS:

    Pre-requisites:
    1. Java must be installed
    2. Install maven module (https://maven.apache.org/install.html).

    Steps:
    1. To run this application, do the following:
        1.a. Go to the project root directory.
        1.b. Run the following commands in the terminal/command line to build the app:
            - mvn clean install
        1.c. Run the following command(s) in the terminal/command line to run the app:
            - java -jar ./target/spring-boot-in-docker.jar

    2. Go to http://localhost:8080/ in your browser to view it.
