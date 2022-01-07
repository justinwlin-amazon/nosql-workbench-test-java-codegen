## Tips on connecting
Install a plugin - AWS Toolkit.
This plugin will help you connect to your AWS account.
Choose your connection on the bottom-right.

## Steps to run the generated code
- Install aws sdk via maven
```
mvn clean install
```
- Add generated code under `src/java/javacodeegen`

- Change following code if needed

```java
// Change to right code package
package javacodeegen;

// Optional - Enable import 
import com.amazonaws.client.builder.AwsClientBuilder;

// Change region
AmazonDynamoDB dynamoDB = createDynamoDbClient("eu-west-1");

// Optional - print out result - if needed
System.out.println(batchExecuteStatementResult);

```
- Then compile your changes and run it
```bash
# Compile
mvn compile

# Run your main class
mvn exec:java -Dexec.mainClass=<package-name>.<main-class>
```

## Other thing you might need to configure
In project settings > modules, choose the source as ..../src/main/java