package javacodeegen;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.InternalServerErrorException;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughputExceededException;
import com.amazonaws.services.dynamodbv2.model.RequestLimitExceededException;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
// include the following class when using DynamoDB Local
// import com.amazonaws.client.builder.AwsClientBuilder;
// import com.amazonaws.auth.AWSStaticCredentialsProvider;
// import com.amazonaws.auth.BasicAWSCredentials;

import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ItemCollectionSizeLimitExceededException;
import com.amazonaws.services.dynamodbv2.model.TransactionConflictException;
import java.util.HashMap;
import java.util.Map;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.client.builder.AwsClientBuilder;

/**
 *  Following generated code sample describes use of AWS SDK for Java v1 for calling Amazon DynamoDB APIs
 *
 *  This generated code sample requires Java version 1.6 or higher
 *  NOTE: The decodeBinaryString method below uses java.util.Base64 which is only available since Java 1.8
 *
 *  Before running the code below, please follow these steps to setup your workspace if you have not
 *  set it up already:
 *  1) Setup the AWS SDK for Java v1. Instructions for setup using Maven (one of the supported build systems):
 *    To select individual SDK modules, use the AWS SDK for Java v1 bill of materials (BOM) for Maven, which will ensure
 *      that the modules you specify use the same version of the SDK and that they're compatible with each other.
 *    To use the BOM, add a <dependencyManagement> section to your application's pom.xml file,
 *     adding aws-java-sdk-bom as a dependency and specifying the version of the SDK you want to use:
 *
 *     <dependencyManagement>
 *       <dependencies>
 *         <dependency>
 *           <groupId>com.amazonaws</groupId>
 *           <artifactId>aws-java-sdk-bom</artifactId>
 *           <version>1.11.327</version>
 *           <type>pom</type>
 *           <scope>import</scope>
 *         </dependency>
 *       </dependencies>
 *     </dependencyManagement>
 *
 *    You can now select individual modules from the SDK that you use in your application. Because you already declared
 *    the SDK version in the BOM, you don't need to specify the version number for each component.
 *      <dependencies>
 *       <dependency>
 *         <groupId>com.amazonaws</groupId>
 *         <artifactId>aws-java-sdk-dynamodb</artifactId>
 *       </dependency>
 *     </dependencies>
 *
 *   Please refer the following guide for other supported build systems:
 *     https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/setup-install.html
 *
 *  2) Setup credentials for DynamoDB access. One of the ways to setup credentials is to add them to
 *    ~/.aws/credentials file (C:\Users\USER_NAME\.aws\credentials file for Windows users) in
 *    following format:
 *
 *    [<profile_name>]
 *    aws_access_key_id = YOUR_ACCESS_KEY_ID
 *    aws_secret_access_key = YOUR_SECRET_ACCESS_KEY
 *
 *    If <profile_name> is specified as "default" then AWS SDKs and CLI will be able to read the credentials
 *    without any additional configuration. But if a different profile name is used then it needs to be
 *    specified while initializing DynamoDB client via AWS SDKs or while configuring AWS CLI.
 *    Please refer following guide for more details on credential configuration:
 *    https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/credentials.html
 */
public class PutItem {

    public static void main(String[] args) {
        // Create the DynamoDB Client with the region you want
        AmazonDynamoDB dynamoDB = createDynamoDbClient("us-east-1");

        try {
            // Create PutItemRequest
            PutItemRequest putItemRequest = createPutItemRequest();
            PutItemResult putItemResult = dynamoDB.putItem(putItemRequest);
            System.out.println("Successfully put item.");
            // Handle putItemResult

        } catch (Exception e) {
            handlePutItemErrors(e);
        }
    }

    private static AmazonDynamoDB createDynamoDbClient(String region) {
        // Use the following builder instead when using DynamoDB Local
        // return AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
        // new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "localhost")).withCredentials(
        // new AWSStaticCredentialsProvider(new BasicAWSCredentials("access_key_id", "secret_access_key"))
        // )
        // .build();
        return AmazonDynamoDBClientBuilder.standard().withRegion(region).build();
    }

    private static PutItemRequest createPutItemRequest() {
        PutItemRequest putItemRequest = new PutItemRequest();
        putItemRequest.setTableName("Forum");
        putItemRequest.setItem(getItem());
        return putItemRequest;
    }

    private static Map<String, AttributeValue> getItem() {
        Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
        item.put("ForumName", new AttributeValue("testhaha"));
        return item;
    }

    // Handles errors during PutItem execution. Use recommendations in error messages below to add error handling specific to
    // your application use-case.
    private static void handlePutItemErrors(Exception exception) {
        try {
            throw exception;
        } catch (ConditionalCheckFailedException ccfe) {
            System.out.println("Condition check specified in the operation failed, review and update the condition " +
                                       "check before retrying. Error: " + ccfe.getErrorMessage());
        } catch (TransactionConflictException tce) {
            System.out.println("Operation was rejected because there is an ongoing transaction for the item, generally " +
                                       "safe to retry with exponential back-off. Error: " + tce.getErrorMessage());
        } catch (ItemCollectionSizeLimitExceededException icslee) {
            System.out.println("An item collection is too large, you\'re using Local Secondary Index and exceeded " +
                                       "size limit of items per partition key. Consider using Global Secondary Index instead. Error: " + icslee.getErrorMessage());
        } catch (Exception e) {
            handleCommonErrors(e);
        }
    }

    private static void handleCommonErrors(Exception exception) {
        try {
            throw exception;
        } catch (InternalServerErrorException isee) {
            System.out.println("Internal Server Error, generally safe to retry with exponential back-off. Error: " + isee.getErrorMessage());
        } catch (RequestLimitExceededException rlee) {
            System.out.println("Throughput exceeds the current throughput limit for your account, increase account level throughput before " +
                                       "retrying. Error: " + rlee.getErrorMessage());
        } catch (ProvisionedThroughputExceededException ptee) {
            System.out.println("Request rate is too high. If you're using a custom retry strategy make sure to retry with exponential back-off. " +
                                       "Otherwise consider reducing frequency of requests or increasing provisioned capacity for your table or secondary index. Error: " +
                                       ptee.getErrorMessage());
        } catch (ResourceNotFoundException rnfe) {
            System.out.println("One of the tables was not found, verify table exists before retrying. Error: " + rnfe.getErrorMessage());
        } catch (AmazonServiceException ase) {
            System.out.println("An AmazonServiceException occurred, indicates that the request was correctly transmitted to the DynamoDB " +
                                       "service, but for some reason, the service was not able to process it, and returned an error response instead. Investigate and " +
                                       "configure retry strategy. Error type: " + ase.getErrorType() + ". Error message: " + ase.getErrorMessage());
        } catch (AmazonClientException ace) {
            System.out.println("An AmazonClientException occurred, indicates that the client was unable to get a response from DynamoDB " +
                                       "service, or the client was unable to parse the response from the service. Investigate and configure retry strategy. "+
                                       "Error: " + ace.getMessage());
        } catch (Exception e) {
            System.out.println("An exception occurred, investigate and configure retry strategy. Error: " + e.getMessage());
        }
    }

}