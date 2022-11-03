package com.example.handlingformsubmission;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

/**
 * Purpose of the class is to map
 * <br>
 * the data participants into the
 * <br>
 * columns of the table from the DynamoDb.
 */
@DynamoDbBean
public class StudentItems {
    //Private instance variables.
    private String studentName;
    private String studentSurname;
    private String studentID;
    private String studentYear;
    private boolean isPresent;

    //Constructor.
    public StudentItems() {}

    ///Getters.
    public String getStudentName() {return this.studentName;}
    public String getStudentSurname() {return this.studentSurname;}
    public String getStudentID() {return this.studentID;}
    public String getStudentYear() {return this.studentYear;}
    public boolean isPresent() {return this.isPresent;}

    //Setters.
    public void setStudentName(String studentName) {this.studentName = studentName;}
    public void setStudentSurname(String studentSurname) {this.studentSurname = studentSurname;}
    @DynamoDbPartitionKey
    public void setStudentID(String studentID) {this.studentID = studentID;}
    public void setStudentYear(String studentYear) {this.studentYear = studentYear;}
    public void setPresent(boolean present) {this.isPresent = present;}
}
