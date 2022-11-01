package com.example.handlingformsubmission;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class StudentItem {
    private String studentName;
    private String studentSurname;
    private String studentID;
    private String studentYear;
    private boolean isPresent;

    public StudentItem() {

    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurname() {
        return this.studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public String getStudentID() {
        return this.studentID;
    }

    @DynamoDbPartitionKey
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentYear() {
        return this.studentYear;
    }

    public void setStudentYear(String studentYear) {
        this.studentYear = studentYear;
    }

    public boolean isPresent() {
        return this.isPresent;
    }

    public void setPresent(boolean present) {
        this.isPresent = present;
    }
}
