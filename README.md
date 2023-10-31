# Description
This is an AWS Lambda function made using Java if correctly configured allows you to call an AWS API endpoint and obtain all the products stored in the DynamoDB

# Usage and Modify

**0. Prerequisites:**

- Install [SAM  1.97.0+](https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/install-sam-cli.html) or check if already installed with `sam --version`.
- Install [AWS CLI 2.13.20+](https://docs.aws.amazon.com/cli/latest/userguide/cli-chap-getting-started.html)  or check if already installed with `aws --version`.
- Install [Java SE JDK-17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or check if already installed with `java -version`.
- Install [Maven 3.8.4+](https://maven.apache.org/install.html) or check if already installed with `mvn -v`.

**1. Configure AWS User**

- Use the AWS IAM console to create a user with the AdministatorAccess policy and configure it into aws cli with `aws configure` and insert as parameters *access key*, *secret* (user > security credentials > access key), your [region](https://docs.aws.amazon.com/AmazonRDS/latest/UserGuide/Concepts.RegionsAndAvailabilityZones.html) and *json*. (You can test if all data are correctly configured with `aws iam get-user`)
- Use the AWS IAM console to create another user with the AmazonDynamoDBFullAccess policy and obtain the *access key* and *secret*  (user > security credentials > access key) with this user. After downloading the code, configure the *src/main/resurces/application.yml* with the new parameters.
> [!NOTE]
> Is it safer to use two different users.

**2. Build**

- Install all project dependencies specified in the pom.xml file using `mvn clean install`, a target folder will be created.
- In the *template.yml* edit the CodeUri with the *.jar* file path.

**3. Deploy**

- Create an AWS S3 BUCKET using the user interface or with `aws s3 mb s3://NAME_OF_THE_BUCKET`
- In the same folder of the template.yml run `sam deploy --s3-bucket NAME_OF_THE_BUCKET_CREATED --stack-name CHOOSE_STACK_NAME --capabilities CAPABILITY_IAM`.

 **4. Configure the endpoint**
- In the AWS Gateway find the new endpoint
- Navigate to: integration request > settings > deactivate the Proxy option.
- Navigate to: method responses > configure the default 200 response.
- In models create a new one with these values:
 
   | Property      | Value                                                                                                           | 
   | ------------- |-----------------------------------------------------------------------------------------------------------------|
   | Model Name    | Empty                                                                                                           | 
   | Content Type  | application/Json                                                                                                |   
   | Schema        |  ` { "$schema": "http://json-schema.org/draft-04/schema#", "title" : "Empty Schema",  "type" : "object" } `     |
  
- In the retrieveAll resources enable and configure the CORS.
- Deploy the API in a stage with a name of your choice.

**5. Testing**
- Use the deployed link + /api/products/retrieveAll endpoint and test it with [Postman](https://www.postman.com/), you should get a JSON response with a list of all the products inside the Dynamo DB:
![](https://github.com/Puntiss/java-retrieveAllProduct-AWSLambdaFunction/blob/master/postmanTest.JPG)

Happy coding!


