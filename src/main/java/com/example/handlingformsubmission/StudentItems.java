package com.example.handlingformsubmission;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

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
    private String moduleID;
    private String lectureDate;
    private boolean attended;

    //Constructor.
    public StudentItems()
    {
    }

    ///Getters.
    public String getStudentName() {return this.studentName;}
    public String getStudentSurname() {return this.studentSurname;}
    public String getStudentID() {return this.studentID;}
    public String getStudentYear() {return this.studentYear;}
    public String getModuleID() {return moduleID;}
    public String getLectureDate() {return lectureDate;}
    public boolean getAttended() {return attended;}

    //Setters.
    public void setStudentName(String studentName) {this.studentName = studentName;}
    public void setStudentSurname(String studentSurname) {this.studentSurname = studentSurname;}

    @DynamoDbPartitionKey
    public void setStudentID(String studentID) {this.studentID = studentID;}
    public void setStudentYear(String studentYear) {this.studentYear = studentYear;}
    public void setModuleID(String moduleID) {this.moduleID = moduleID;}
    @DynamoDbSortKey
    public void setLectureDate(String lectureDate) {this.lectureDate = lectureDate;}
    public void setAttended(boolean attended) {this.attended = attended;}
}
