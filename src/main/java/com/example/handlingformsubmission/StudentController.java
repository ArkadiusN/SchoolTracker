package com.example.handlingformsubmission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// @ModelAttribute is an annotation that binds a method parameter
// or method return value to a named model attribute, and then
// exposes it to a web view.
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 Controller
 <br>
 annotation indicates that a particular class
 <br>
  serves the role of a controller.
 <br>
 It’s used to mark a class as a web request handler!!!
 **/
@Controller
public class StudentController {
    /**
     Autowired
     <br>
     Since StudentController needs an instance of
     <br>
     DynamoDBEnhanced, so it will search for a type "DynamoDBEnhanced"
     <br>
     to get this object instance.
    ***/
    @Autowired
    private DynamoDBEnhanced dde;

    @Autowired
    private SMSNotification msg;

    /**
     Annotation for mapping HTTP GET requests onto
     <br>
     specific handler methods.
     **/
    @GetMapping("/")
    public String studentTrackForm(Model model) {
        model.addAttribute("student", new Student());
        return "student";
    }

    /**
     Annotation for mapping HTTP POST requests onto
     <br>
     specific handler methods.
     @see "https://www.baeldung.com/spring-mvc-and-the-modelattribute-annotation"
     **/
    @PostMapping("/student")
    public String studentSubmit(@ModelAttribute Student student) {

        //Stores data in an Amazon DynamoDB table.
        dde.insertDynamoItem(student);

        //Sends a notification to the number specified about
        //newly added student.
        msg.sendMessage(student.getStudentName(),
                    student.getStudentSurname(),
                    student.getStudentID(),
                    student.getStudentYear());
        return "result";
    }
}
