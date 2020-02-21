# Lights out

A simple solution for "Light-out" like a problem with special rules.

---

## Problem state

There is a rectangular board with integers and a special parameter called **depth**. 
There are also several predefined rectangular pieces which are filled by **.** or **X**.  
Every time we put one piece on the board value on the board increments if corresponding piece cell has **X**. If the value in a board cell is equal to **depth** then it reset to 0.
The problem is to place all pieces on the board so that all board cells become equal to 0. 

### Requirements

- Java 8
- Maven > 3.0

### Tech

Lights out use a number of open-source projects to work properly:

* [Maven](https://maven.apache.org/download.cgi) - a software project management and comprehension tool
* [Lombok](https://projectlombok.org) - a java library that automatically plugs into your editor and build tools
* [JUnit4](https://junit.org/junit4/) - a simple framework to write repeatable tests

## Building
Run the following command to build the project:
```sh
$ mvn clean install
```

### Running
Run the following command to start the program:
```sh
$ java -jar target/light-out-1.0.jar "path_to_file"
```