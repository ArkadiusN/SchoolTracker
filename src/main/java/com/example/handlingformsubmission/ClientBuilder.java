package com.example.handlingformsubmission;

import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public abstract class ClientBuilder<T> {
    //Constants used for multiple client builders.
    protected final Region region = Region.EU_WEST_2;
    protected final EnvironmentVariableCredentialsProvider evcp = EnvironmentVariableCredentialsProvider.create();

    /**Abstract Generic method which
     * adapts based on the return-type
     * of the client builder.
     * @return the client of the chosen type.
     */
    protected abstract T buildClient();

    /**
     * Enhanced client builder requesting
     * the basic client builder as a parameter.
     * @param ddbClient the basic dynamodb client used to create the enhanced version.
     */
    protected DynamoDbEnhancedClient buildEnhancedClientDynDB(DynamoDbClient ddbClient){
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(ddbClient)
                .build();
    }
}
