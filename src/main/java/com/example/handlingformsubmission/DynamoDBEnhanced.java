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

    // Queries the dynamoDB table to get student attendance records
    public void queryDynamoTable(Query query) {
        DynamoDbClient ddb = buildClient();

        try {
            // Builds the scan request
            ScanRequest scanRequest = ScanRequest
                    .builder()
                    .tableName("Student")
                    .projectionExpression("moduleID, lectureDate, studentID, attended")
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

            Object[][] format = new Object[length + 1][4];

            format[0][0] = columnNames[0];
            format[0][1] = columnNames[1];
            format[0][2] = columnNames[2];
            format[0][3] = columnNames[3];

            // Formats the table to be usable by the html page
            for (int i = 0; i < length; i++) {
                format[i + 1][0] = data[0][i];
                format[i + 1][1] = data[1][i];
                format[i + 1][2] = data[2][i];
                format[i + 1][3] = data[3][i];
            }

            // Removes record rows from the table (they are used to hold additional student data
            int rows_removed = 0;
            for (int i = 0; i < length + 1; i++) {
                if (format[i][1] == "RECORD") {
                    rows_removed++;
                }
            }

            String[][] final_table = new String[length + 1 - rows_removed][4];

            rows_removed = 0;
            for (int i = 0; i < length + 1; i++) {
                if (format[i][2] == "RECORD") {
                    rows_removed += 1;
                } else {
                    final_table[i][0] = format[i + rows_removed][0].toString();
                    final_table[i][1] = format[i + rows_removed][1].toString();
                    final_table[i][2] = format[i + rows_removed][2].toString();
                    final_table[i][3] = format[i + rows_removed][3].toString();
                }
            }

            query.setData(final_table);
            query.setColumnNames(columnNames);

        } catch (DynamoDbException err) {
            System.out.println(err.getMessage());
            System.exit(1);
        }
    }
}
