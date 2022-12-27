package com.example.handlingformsubmission;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

import java.util.Map;

/*
  @Component is an annotation that allows Spring to automatically detect our custom beans.
  In other words, without having to write any explicit code, Spring will:
    - Scan our application for classes annotated with @Component.
    - Instantiate them and inject any specified dependencies into them.
    - Inject them wherever needed.
 */
@Component("DynamoDBEnhanced")
public class DynamoDBEnhanced extends ClientBuilder<DynamoDbClient> {

    @Override
    protected DynamoDbClient buildClient() {
        return DynamoDbClient.builder()
                .region(region)
                .credentialsProvider(evcp)
                .build();
    }

    /**
     * @param student a record with the details to be saved into DynamoDB.
     */
    public void insertDynamoItem(Student student){
        DynamoDbClient ddb = buildClient();

        try {
            //Suggested way to forward the execution
            //of database operations on DynamoDB using
            //applications classes (from AWS SDK 2.0).
            DynamoDBEnhanced ddbObject = new DynamoDBEnhanced();
            //TODO Check whether a lambda expression can be used for buildEnhancedDynDb();
            DynamoDbEnhancedClient enhancedClient = ddbObject.buildEnhancedClientDynDB(ddb);


            DynamoDbTable<StudentItems> table = enhancedClient.table("Student", TableSchema.fromBean(StudentItems.class));
            StudentItems studentItems = new StudentItems();

            //Data values we want to store
            //via submission of the form, therefore
            //we populate the table.
            studentItems.setStudentName(student.getStudentName());
            studentItems.setStudentSurname(student.getStudentSurname());
            studentItems.setStudentYear(student.getStudentYear());
            studentItems.setStudentID(student.getStudentID());
            studentItems.setModuleID(student.getModuleID());
            studentItems.setLectureDate(student.getLectureDate());
            studentItems.setAttended(student.getAttended());

            PutItemEnhancedRequest enhancedRequest = PutItemEnhancedRequest.builder(StudentItems.class)
                    .item(studentItems)
                    .build();

            //Put the Student data into an Amazon DynamoDB table.
            table.putItem(enhancedRequest);

        }catch (DynamoDbException err){
            System.out.println(err.getMessage());
            System.exit(1);
        }
    }

    public void queryDynamoTable(String moduleID) {
        DynamoDbClient ddb = buildClient();

        try {
            ScanRequest scanRequest = ScanRequest
                    .builder()
                    .tableName("lectureDate-studentID-index")
                    .filterExpression(String.format("moduleID = %s", AttributeValue.builder().s(moduleID).build()))
                    .projectionExpression("lectureDate, studentID, attended")
                    .build();

            ScanResponse result = ddb.scan(scanRequest);

        } catch (DynamoDbException err) {
            System.out.println(err.getMessage());
            System.exit(1);
        }
    }
}
