# Portal-for-driving-instructor

 * Author: Karol Jaskot
 * Student PWSZ in Legnica
 * E-mail: karol.jaskot97@gmail.com

Educational project

Design of a simple application for a driving instructor.

Planned items:
* Online driving license tests
* Schedule of practical driving hours available
* List of educational materials
* questionnaire


### Tech

* SpringBoot
* Hibernate
* H2 database
* Vaadin



### Installation

IntelijIdea is required to open this program.


Install project:
```sh
mvn clean
mvn install
```
To start this project:
```sh
mvn spring-boot:run
```

Thanks to that it will be available at =  localhost: 8080

If port 8080 is not available, you can change the address in the file:
```sh
/src/main/resources/application.properties
```