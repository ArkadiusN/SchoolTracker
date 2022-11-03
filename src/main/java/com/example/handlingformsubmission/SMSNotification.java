package com.example.handlingformsubmission;


import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.SnsException;
import org.springframework.stereotype.Component;

@Component("SMSNotification")
public class SMSNotification {

    public void sendMessage(String studentName, String studentSurname, String studentID, String studentYear){
        Region region = Region.US_EAST_1;
        SnsClient snsClient = SnsClient.builder()
                .region(region)
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .build();

        //Message to be sent to the person taking care of the
        //students register.
        String message = String
                .format("Student %s %s with the ID %s from year %s was added to DynamoDB register.",
                studentName,
                studentSurname,
                studentID,
                studentYear);
        //TODO Figure out the correct format of the phone number as a String
        // for the purpose of testing/creating the example.
        String phoneNumber = " ";

        try {
            PublishRequest request = PublishRequest.builder()
                    .message(message)
                    .phoneNumber(phoneNumber)
                    .build();

            //Send the message via Amazon SNS (Simple Notification Service).
            snsClient.publish(request);
        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
}
