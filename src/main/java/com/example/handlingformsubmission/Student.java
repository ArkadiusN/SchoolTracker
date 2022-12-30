package com.example.handlingformsubmission;

/**
 * Student class is the representation model
 * <br>
 * for the Spring Boot application.yml.
 */
public class Student {
    //Private instance variables.
    private String studentName;
    private String studentSurname;
    private String studentID;
    private String studentYear;

    ///Getters.
    public String getStudentName() {return this.studentName;}
    public String getStudentSurname() {return this.studentSurname;}
    public String getStudentID() {return studentID;}
    public String getStudentYear() {return this.studentYear;}

    //Setters.
    public void setStudentName(String studentName) {this.studentName = studentName;}
    public void setStudentSurname(String studentSurname) {this.studentSurname = studentSurname;}
    public void setStudentID(String studentID) {this.studentID = studentID;}
    public void setStudentYear(String studentYear) {this.studentYear = studentYear;}
}
