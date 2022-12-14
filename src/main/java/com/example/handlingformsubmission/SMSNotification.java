package com.example.handlingformsubmission;

import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.SnsException;
import org.springframework.stereotype.Component;

@Component("SMSNotification")
public class SMSNotification extends ClientBuilder{

    public void sendMessage(String studentName, String studentSurname, String studentID, String studentYear, boolean isPresent){
        //Message to be sent to the person taking care of the
        //students register.
        String message = String
                .format("Student: %s %s with the ID: %s \nfrom Year: %s \nwas added to register \nand the Status is: %s",
                        studentName,
                        studentSurname,
                        studentID,
                        studentYear,
                        isPresent);

        //Based on the documentation the number to be used needs to
        //adhere to standard E.164 to work.
        String phoneNumber = "+44 771-513-1488";

        try {
            PublishRequest request = PublishRequest.builder()
                    .message(message)
                    .phoneNumber(phoneNumber)
                    .build();

            //Send the message via Amazon SNS (Simple Notification Service).
            SnsClient snsClient = super.buildClientSNS();
            snsClient.publish(request);

        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
}
