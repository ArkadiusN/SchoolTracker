package com.example.handlingformsubmission;

import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.sns.SnsClient;

public class ClientBuilder {
    final static protected Region region = Region.EU_WEST_2;
    final protected EnvironmentVariableCredentialsProvider evcp = EnvironmentVariableCredentialsProvider.create();

    protected SnsClient buildClientSNS (){
        return SnsClient.builder()
                .region(region)
                .credentialsProvider(evcp)
                .build();
    }

    protected  DynamoDbClient buildClientDynDB() {
        return DynamoDbClient.builder()
                .region(region)
                .credentialsProvider(evcp)
                .build();
    }

    protected  DynamoDbEnhancedClient buildEnhancedClientDynDB(DynamoDbClient dbClient){
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dbClient)
                .build();
    }
}
