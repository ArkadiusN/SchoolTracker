package com.example.handlingformsubmission;

import org.springframework.stereotype.Component;
import org.w3c.dom.Attr;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/*
  @Component is an annotation that allows Spring to automatically detect our custom beans.
  In other words, without having to write any explicit code, Spring will:
    - Scan our application.yml for classes annotated with @Component.
    - Instantiate them and inject any specified dependencies into them.
    - Inject them wherever needed.
 */
@Component("DynamoDBEnhanced")
public class DynamoDBEnhanced extends ClientBuilder{

    /**
     * @param student a record with the details to be saved into DynamoDB.
     */
    public void insertDynamoItem(Student student){
        DynamoDbClient ddb = super.buildClientDynDB();

        try {
            //Suggested way to forward the execution
            //of database operations on DynamoDB using
            //applications classes (from AWS SDK 2.0).
            DynamoDbEnhancedClient enhancedClient = super.buildEnhancedClientDynDB(ddb);
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

    // Queries the dynamoDB table to get student attendance records
    public void queryDynamoTable(Query query) {
        DynamoDbClient ddb = super.buildClientDynDB();

        try {
            // Builds the scan request, currently filtering out record values doesn't work
            Map<String, AttributeValue> expressionAttributeValues =
                    new HashMap<>();
            AttributeValue attr = AttributeValue.builder().n("RECORD").build();
            expressionAttributeValues.put(":val", attr);
            ScanRequest scanRequest = ScanRequest
                    .builder()
                    .tableName("Student")
                    // .filterExpression("lectureDate <> :val")
                    .projectionExpression("moduleID, lectureDate, studentID, attended")
                    // .expressionAttributeValues(expressionAttributeValues)
                    .build();

            // Receives the response from dynamoDB
            ScanResponse response = ddb.scan(scanRequest);

            int length = response.count();
            // Stores the response in a usable array
            Object[][] data = new Object[4][length];

            int index = 0;

            // Iterates over all the item responses from the dynamoDB table and sorts them into an array
            for (Map<String, AttributeValue> item : response.items()) {
                data[0][index] = item.get("moduleID");
                data[1][index] = item.get("lectureDate");
                data[2][index] = item.get("studentID");
                data[3][index] = item.get("attended");
                index++;
            }

            String[] columnNames = {"Module ID", "Lecture Date", "Student ID", "Present"};

            String[][] format = new String[length + 1][4];

            format[0][0] = columnNames[0];
            format[0][1] = columnNames[1];
            format[0][2] = columnNames[2];
            format[0][3] = columnNames[3];

            // Formats the table to be usable by the html page
            for (int i = 0; i < length; i++) {
                format[i + 1][0] = data[0][i].toString();
                format[i + 1][1] = data[1][i].toString();
                format[i + 1][2] = data[2][i].toString();
                format[i + 1][3] = data[3][i].toString();
            }

            for (int i=0; i < format.length; i++) {
                for (int j=0; j < format[0].length; j++) {
                    if (!Arrays.asList(columnNames).contains(format[i][j]) && !Objects.equals(format[i][j], "RECORD")) {
                        String[] s = format[i][j].split("=", 2);
                        String s1 = s[1].substring(0, s[1].length() - 1);
                        format[i][j] = s1;
                    }
                }
            }

            query.setData(format);
            query.setColumnNames(columnNames);

        } catch (DynamoDbException err) {
            System.out.println(err.getMessage());
            System.exit(1);
        }
    }
}
