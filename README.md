Angular-Boot-Rest
=================

Rest service using AngularJS, BootStrap, Spring Boot and Spring REST

Build and dependency management tools used are these.

1. http://bower.io/
  This is indispendable for managing JavaScript frameworks and saves time.
2. Gradle
  Automated Build and dependency management for the entire project. A clone of the project which already contains
  the gradle script should be enough to pull all dependencies and start the application.
  
Application development tools.

1. Spring boot reduced the time needed to bootstrap a Spring application and enabled easy deployment and execution. No
   separate container is needed. 
    The Gradle build script created a JAR file which was executed like this.
    
    java -jar build/libs/Angular-Boot-Rest-0.1.0.jar
   
2. BootStrap enabled to create the UI with many features that were easy to introduce.

Logging tools.

1. slf4j for the Java code
2. 'console' log API for JavaScript.
