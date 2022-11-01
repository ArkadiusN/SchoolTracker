package com.example.handlingformsubmission;

/**
 * Student class represents the model
 * <br>
 * for the Spring Boot application.
 */
public class Student {
    private String studentName;
    private String studentSurname;
    private String studentID;
    private String studentYear;
    private boolean isPresent;

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
        return studentID;
    }

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
