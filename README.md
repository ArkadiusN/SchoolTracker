# School Tracker <img src="SchoolTrackerLogo.PNG" height="150" align="left" style="margin-right:15px; margin-bottom:19px; padding-right: 0px; border: 5px solid #e7790d" />

**&raquo; AWS Student Tracker application** <br/><sub> _SaaS_ &middot; _AWS_ &middot; _AWS SDK_ &middot; _Java_ &middot; _Spring Boot_</sub>

---

## About Student Tracker app
The Student Tracker app is an engaging web-based application designed to store, display, and effortlessly update student records. With a user-friendly web interface, the app harnesses the power of AWS Elastic Beanstalk for seamless deployment. All student records are securely stored in a DynamoDB database within the cloud. Moreover, clients have the added convenience of receiving SMS notifications to confirm new attendance records or modifications. Prior to accessing the main form and storing new information, users undergo authentication via Amazon Cognito, ensuring data integrity. This comprehensive application allows for the storage of vital information such as student names, ID numbers, modules, and attendance records. Users can conveniently monitor attendance across multiple modules, update attendance records for individual students, and even review attendance for all students enrolled in a particular module or across various modules for a single student.

#### Contents
- **[1 Getting Student Tracker](#1-getting-student-tracker)**
  - [1.1 Execute code in Elastic Beanstalk](#11-execute-code-in-elastic-beanstalk)
- **[2 About the program](#2-about-the-program)**
  - [2.1 Target group](#21-target-group)
  - [2.2 Implementation](#22-implementation)
  - [2.3 Architecture](#23-architecture)

## 1 Getting Student Tracker

### 1.1 Execute code in Elastic Beanstalk
The reality of Elastic Beanstalk simplifies the overall project significantly, thanks to its automatic configuration of the application environment. The initial step involves providing a phone number for $\color{white}{SMSNotification.java}$ in the `phoneNumber` variable. After completing this task, we proceed to package the project into a JAR file and deploy it to Elastic Beanstalk using the command provided below:
```bash
mvn package
```
The .jar file should appear in the target folder that we can use with the AWS Elastic Beanstalk. [This link](https://medium.com/javarevisited/deploy-spring-boot-app-to-aws-elastic-beanstalk-fa42b8b4bfa0) will help to configure the app wizard accordingly to the project. To make sure that the right configuration also exists for our DynamoDB table please have a look at the constantly changing [documentation](https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/home.html) to make sure it matches the up-to-date criteria. 

On successful completion of going through these steps, you should be able to view the result below from the link provided by the Elastic Beanstalk:

<img src="SchoolTracker deployed.PNG" height="320" style="border: 5px solid #181414"/>

On the other hand, the database should autonomously update itself with minimal latency, which can be observed in the following:

<img src="SchoolTracker database.PNG" height="380" style="border: 5px solid #181414"/>


<sub>[[Top 🢁](#contents)]</sub>

## 2 About the program

### 2.1 Target group
This application is aimed at lecturers/teachers who are interested in storing their student attendance records online. Storing records online reduces the possibility of them being lost, local system failures will have no effect on stored information. Moreover, the records can be easily accessed from anywhere quickly without the potential for Tracker corruption due to local system failures; This is crucial since the data needs to be viewed/updated regularly.

### 2.2 Implementation
The implementation of our application is built upon the robust foundation of Spring Boot in Java, leveraging the comprehensive software development kit offered by AWS. This positions our application as an exemplary Software as a Service (SaaS) solution. To ensure broad accessibility, our GUI utilizes the dynamic java templating engine, Thymeleaf, effortlessly rendering HTML content from the java backend to any web browser. Prior to accessing the application, users undergo a secure authentication process powered by Amazon Cognito. The Java application efficiently handles request handling and database queries, with records securely stored within an AWS DynamoDB database. Furthermore, AWS SNS integration enables users to receive real-time text message notifications on their mobile devices whenever a tracker is added or updated. Seamlessly deploying our application to the cloud is made possible through the efficient utilization of AWS Elastic Beanstalk.

### 2.3 Architecture
<img src="SchoolTracker Architecture.PNG" height="500" style="border: 5px solid #181414"/>

Initially we have explored various technologies, including SQL databases. However, as the project progressed, we made the decision to utilize DynamoDB, a NoSQL database, but also tried to maintain the similarity of what the below architecture conveys. The database schema for the attendance record:

<img src="Database Schema.PNG" height="450" style="border: 5px solid #181414"/>

To obtain more detailed information about the project or its progress, please refer to the files listed below:

[Download PDF 1 (Project Proposal that has changed based on the issues and findings)](https://github.com/ArkadiusN/SchoolTracker/blob/master/Cloud_Computing_Proposal_changed.docx)

[Download PDF 2 (Presentation of the project at the middle stage)](https://github.com/ArkadiusN/SchoolTracker/blob/master/PG12_Presentation_Student_Attendance_Application.pptx)

<sub>[[Top 🢁](#contents)]</sub>






