package com.example.handlingformsubmission;

import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// @ModelAttribute is an annotation that binds a method parameter
// or method return value to a named model attribute, and then
// exposes it to a web view.
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.exceptions.TemplateProcessingException;

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

    @GetMapping("/attend")
    public String getAttendance(Model model) {
        model.addAttribute("student", new Student());
        return "attend";
    }

    @GetMapping("/query")
    public String getQuery(Model model) {
        model.addAttribute("query", new Query());
        return "query";
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
        try {
            msg.sendMessage(student.getStudentName(),
                    student.getStudentSurname(),
                    student.getStudentID(),
                    student.getStudentYear());
        }catch (NotReadablePropertyException err1){
            System.err.println("One or more properties is not readable or has an invalid getter method.");
            System.out.println("The error is: " + err1);
            System.exit(500);

        }catch (TemplateProcessingException err2){
            System.err.println("The org.thymeleaf engine has found error in the template's syntax.");
            System.out.println("The error is: " + err2);
            System.exit(500);
        }
        return "result";
    }

    @PostMapping("/attend")
    public String attendSubmit(@ModelAttribute Student student) {

        //Stores data in an Amazon DynamoDB table.
        dde.insertDynamoItem((student));

        return "result";
    }
}
