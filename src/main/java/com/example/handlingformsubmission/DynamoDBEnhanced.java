package com.example.handlingformsubmission;

import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

/*
  @Component is an annotation that allows Spring to automatically detect our custom beans.
  In other words, without having to write any explicit code, Spring will:
    - Scan our application for classes annotated with @Component.
    - Instantiate them and inject any specified dependencies into them.
    - Inject them wherever needed.
 */
@Component("DynamoDBEnhanced")
public class DynamoDBEnhanced {

    /**
     * @param student a record with the details to be saved into DynamoDB.
     */
    public void insertDynamoItem(Student student){
        Region currentRegion = Region.US_EAST_1;
        DynamoDbClient ddb = DynamoDbClient
                .builder()
                .region(currentRegion)
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .build();
        try {
            //Suggested way to forward the execution
            //of database operations on DynamoDB using
            //applications classes (from AWS SDK 2.0).
            DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient
                    .builder()
                    .dynamoDbClient(ddb)
                    .build();
            DynamoDbTable<StudentItems> table = enhancedClient
                    .table("Student", TableSchema.fromBean(StudentItems.class));
            StudentItems studentItems = new StudentItems();

            //Data values we want to store
            //via submission of the form, therefore
            //we populate the table.
            studentItems.setStudentName(student.getStudentName());
            studentItems.setStudentSurname(student.getStudentSurname());
            studentItems.setStudentID(student.getStudentID());
            studentItems.setStudentYear(student.getStudentYear());
            studentItems.setPresent(student.isPresent());

            PutItemEnhancedRequest<StudentItems> enhancedRequest = PutItemEnhancedRequest
                    .builder(StudentItems.class)
                    .item(studentItems)
                    .build();

            //Put the Student data into an Amazon DynamoDB table.
            table.putItem(enhancedRequest);
        }catch (DynamoDbException err){
            System.out.println(err.getMessage());
            System.exit(1);
        }
    }
}
