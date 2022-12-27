package com.example.handlingformsubmission;

/**
 * Student class is the representation model
 * <br>
 * for the Spring Boot application.
 */
public class Student {
    //Private instance variables.
    private String studentName;
    private String studentSurname;
    private String studentID;
    private String studentYear;
    private String moduleID = "";
    private String lectureDate = "RECORD";
    private boolean attended = false;

    ///Getters.
    public String getStudentName() {return this.studentName;}
    public String getStudentSurname() {return this.studentSurname;}
    public String getStudentID() {return studentID;}
    public String getStudentYear() {return this.studentYear;}
    public String getModuleID() {return moduleID;}
    public String getLectureDate() {return lectureDate;}
    public boolean getAttended() {return attended;}

    //Setters.
    public void setStudentName(String studentName) {this.studentName = studentName;}
    public void setStudentSurname(String studentSurname) {this.studentSurname = studentSurname;}
    public void setStudentID(String studentID) {this.studentID = studentID;}
    public void setStudentYear(String studentYear) {this.studentYear = studentYear;}
    public void setModuleID(String moduleID) {this.moduleID = moduleID;}
    public void setLectureDate(String lectureDate) {this.lectureDate = lectureDate;}
    public void setAttended(boolean attended) {this.attended = attended;}
}
