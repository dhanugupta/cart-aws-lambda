# cart-lambda
AWS Lambda Cart Micro Service - Serverless REST API's

<img src="https://github.com/dhanugupta/cart-lambda/blob/master/aws-lambda-cart-microservice.png?raw=true"/>

Various components used in this example:

AWS API Gateway
> API Gateway helps developers deliver robust, secure, and scalable mobile and web application back ends. 

AWS Lambda Functions
> Run code without thinking about servers. Pay for only the compute time you consume.

AWS DynamoDB
> Amazon DynamoDB is a fast and flexible NoSQL database service for all applications that need consistent, single-digit millisecond latency at any scale. It is a fully managed cloud database and supports both document and key-value store models.

Deployment
> JAVA, Gradle, Jenkins Deployment

###Lambda
1. Create a new Lambda function. As 

    Name : `CartRequestRouter`
    
    Handler : `com.org.cart.router.RequestRouter::lambdaHandler`
    
    Runtime Env : `Java`
    
    Memory : `1024`
    
    Timeout : `30secs` to start with
    
    Note : 
    1. Ensure your Lambda function is using the correct IAM role. The role must have the ability to write/read to DynamoDB and S3. 
    2. All Lambda interactions are logged in Cloudwatch logs. View the logs for debugging. 

2. The Lambda function handler tells Lambda what java method under `com.org.cart.router.RequestRouter` - 
  ## lambdaHandler(InputStream request, OutputStream response, Context context) 
3. Review & create function. 

###API Gateway
1. Create a new API. Give it a name and description. This will be our RESTful endpoint. 
2. Create a resource. The path should be `/addphoto` , for example.
3. We need to add a method to this resource. Create a GET method with Lambda integration and select the function we created earlier. API Gateway isn't able to process POST requests that are URL encoded, so we are using GET as a workaround.
4. Now let's setup the Integration Request. Twilio's GET request will be of type `application-x-www-form-urlencoded`. This Integration step will map this type to a JSON object, which Lambda requires. In the Integration Requests page create a mapping template. Content-type is `application/json` and template:
```
{
    "body" : "$input.params('Body')",
    "fromNumber" :  "$input.params('From')",
    "image" : "$input.params('MediaUrl0')",
    "numMedia" : "$input.params('NumMedia')"
}
```

 More on [Intergration Requests](http://docs.aws.amazon.com/apigateway/latest/developerguide/api-gateway-mapping-template-reference.html).
 `$input.params()` parse the request object for the corresponding variable and allows the mapping template to build a JSON object. 
    [Screenshot](https://s3-us-west-2.amazonaws.com/mauerbac-hosting/intergration.png)  
5.  Let's ensure the response is correct. Twilio requires valid XML. Change the response model for 200 to Content-type: `application/xml`. Leave models empty. 
    [Screenshot](https://s3-us-west-2.amazonaws.com/mauerbac-hosting/response.png)  
6. Lambda cannot return proper XML, so API Gateway needs to build this. This is done in Integration response as another mapping template. This time we want to create Content-type: application/xml and template: 

```
#set($inputRoot = $input.path('$'))
<?xml version="1.0" encoding="UTF-8"?>
<Response>
    <Message>
        <Body>
            $inputRoot
        </Body>
    </Message>
</Response> 
```
Our Lambda function solely returns a string of the SMS body. Here we build the XML object and use `$inputRoot` as the string.     [Screenshot](https://s3-us-west-2.amazonaws.com/mauerbac-hosting/responseModel.png)  
7. Now let's deploy this API, so we can test it! Click the Deploy API button.

###Connecting the dots & Testing

1. We should now have a publically accessible GET endpoint. Ex: `https://xxxx.execute-api.us-west-2.amazonaws.com/prod/addphoto`
2. Point your Twilio number to this endpoint. [Screenshot](https://s3-us-west-2.amazonaws.com/mauerbac-hosting/twilio.png)
3. Our app should now be connected. Let's review: Twilio sends a GET request with MMS image, fromNumber and body to API Gateway. API Gateway transforms the GET request into a JSON object, which is passed to a Lambda function. Lambda processes the object and writes the user to DynamoDB and writes the image to S3. Lambda returns a string which API Gateway uses to create an XML object for Twilio's response to the user. 
4. First, let's test the Lambda function. Click the Actions dropdown and Configure sample event. We need to simulate the JSON object passed by API Gateway. Example:      
```
{ 
  "body" : "hello",
  "fromNumber" : "+19145554224" ,
  "image" : "https://api.twilio.com/2010-04-01/Accounts/AC361180d5a1fc4530bdeefb7fbba22338/Messages/MM7ab00379ec67dd1391a2b13388dfd2c0/Media/ME7a70cb396964e377bab09ef6c09eda2a",
  "numMedia" : "1"
}
```
Click Test. At the bottom of the page you view Execution result and the log output in Cloudwatch logs. This is very helpful for debugging.  
    5. Testing API Gateway requires a client that sends requests to the endpoint. I personally like the Chrome Extension [Advanced Rest Client](https://chrome.google.com/webstore/detail/advanced-rest-client/hgmloofddffdnphfgcellkdfbfbjeloo?hl=en-US) Send the endpoint a GET request and view its response. Ensure the S3 link works. You can also test by sending an MMS to phone number and checking the Twilio logs.

##Troubleshooting

1. Ensure your Lambda function is using the correct IAM role. The role must have the ability to write/read to DynamoDB and S3. 
2. All Lambda interactions are logged in Cloudwatch logs. View the logs for debugging. 
3. Lambda/API Gateway Forums

##Architecture

![Architecture](https://s3-us-west-2.amazonaws.com/mauerbac-hosting/Screen+Shot+2015-09-30+at+4.00.47+PM.png)
