package com.example.handlingformsubmission;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
  Many Spring Boot developers like their apps to use autoconfiguration,
  component scan and be able to define extra configuration on their "application class".
  A single @SpringBootApplication annotation can be used to enable those three features,
  that is:
   - @EnableAutoConfiguration: enable Spring Boot’s
      autoconfiguration mechanism
   - @ComponentScan: enable @Component scans on the package
      where the application is located (see the best practices)
   - @Configuration: allow registering extra beans in the context
      or import additional configuration classes
 */
@SpringBootApplication
public class SchoolTrackerApp {
    public static void main(String[] args) {
        SpringApplication.run(SchoolTrackerApp.class, args);
    }
}
