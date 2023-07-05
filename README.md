# ToDo-App <img src="SchoolTracker logo.PNG" height="120" align="left" style="margin-right:15px; margin-bottom:19px; border: 5px solid #e7790d" />

**&raquo; AWS Student Record application** <br/><sub> _AWS_ &middot; _AWS SDK_ &middot; _Java_ &middot; _Spring Boot_</sub>

---

## About Student Record app
The Student Record app is an engaging web-based application designed to store, display, and effortlessly update student records. With a user-friendly web interface, the app harnesses the power of AWS Elastic Beanstalk for seamless deployment. All student records are securely stored in a DynamoDB database within the cloud. Moreover, clients have the added convenience of receiving SMS notifications to confirm new attendance records or modifications. Prior to accessing the main form and storing new information, users undergo authentication via Amazon Cognito, ensuring data integrity. This comprehensive application allows for the storage of vital information such as student names, ID numbers, modules, and attendance records. Users can conveniently monitor attendance across multiple modules, update attendance records for individual students, and even review attendance for all students enrolled in a particular module or across various modules for a single student.

#### Contents
- **[1 About the program](#1-about-the-program)**
  - [1.1 Target group](#11-target-group)
  - [1.2 Implementation](#12-implementation)
  - [1.3 Architecture](#13-architecture)



## 1 About the program

### 1.1 Target group
This application is aimed at lecturers/teachers who are interested in storing their student attendance records online. Storing records online reduces the possibility of them being lost, local system failures will have no effect on stored information. Moreover, the records can be easily accessed from anywhere quickly without the potential for record corruption due to local system failures; This is crucial since the data needs to be viewed/updated regularly.

### 1.2 Implementation
The implementation of our application is built upon the robust foundation of Spring Boot in Java, leveraging the comprehensive software development kit offered by AWS. This positions our application as an exemplary Software as a Service (SaaS) solution. To ensure broad accessibility, our GUI utilizes the dynamic java templating engine, Thymeleaf, effortlessly rendering HTML content from the java backend to any web browser. Prior to accessing the application, users undergo a secure authentication process powered by Amazon Cognito. The Java application efficiently handles request handling and database queries, with records securely stored within an AWS DynamoDB database. Furthermore, AWS SNS integration enables users to receive real-time text message notifications on their mobile devices whenever a record is added or updated. Seamlessly deploying our application to the cloud is made possible through the efficient utilization of AWS Elastic Beanstalk.

### 1.3 Architecture








