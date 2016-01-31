# cart-lambda
AWS Lambda Cart Micro Service - Serverless REST API's

## TODO LIST

`adding aws cognito security aspect`

`add more unit testing mocking dynamoDB locally`

##Architecture

![Architecture](https://github.com/dhanugupta/cart-aws-lambda/blob/master/aws-lambda-cart-microservice.png?raw=true)

## Presentation

http://www.slideshare.net/dhanugupta/aws-lambda-cart-microservice-server-less

## Various components used in this example:

AWS API Gateway
> API Gateway helps developers deliver robust, secure, and scalable mobile and web application back ends. 

AWS Lambda Functions
> Run code without thinking about servers. Pay for only the compute time you consume.

AWS DynamoDB
> Amazon DynamoDB is a fast and flexible NoSQL database service for all applications that need consistent, single-digit millisecond latency at any scale. It is a fully managed cloud database and supports both document and key-value store models.

Deployment
> JAVA, Gradle, Jenkins Deployment

### AWS DynamoDB

1. Create Table on DynamoDB

   	Name :  ‘cart’
  
    Partition Key : ‘loginId’ (String)
    
    Sort key : ‘sku’ (String)
    
    Read Capacity Units : 100
    
    Write Capacity Units : 50
	
 Note: If you want to read more on provisioned read/write capacities

 http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ProvisionedThroughputIntro.html

### AWS Lambda
1. Create a new Lambda function. As 

    Name : `CartRequestRouter`
    
    Handler : `com.org.cart.router.RequestRouter::lambdaHandler`
    
    Runtime Env : `Java`
    
    Memory : `1024`
    
    Timeout : `30secs` to start with
    
    Note : 
    1. Ensure your Lambda function is using the correct IAM role. The role must have the ability to write/read to DynamoDB. 
    2. All Lambda interactions are logged in Cloudwatch logs. View the logs for debugging. 

2. The Lambda function handler tells Lambda what java method under `com.org.cart.router.RequestRouter` - 
  ` lambdaHandler(InputStream request, OutputStream response, Context context) `
3. Review & create function. 

###API Gateway
1. Create a new API. Give it a name and description. This will be our RESTful endpoint. 
2. Create a resource. The path should be `/cart` , for example.
3. We need to add one more resource as `/read` under cart as `/cart/read`. Create a GET method with Lambda integration.
4. Now let's setup the Integration Request. Cart Micro Service GET request will be of type `application/json`. This Integration step will map this type to a JSON object, which Lambda requires. In the Integration Requests page create a mapping template. Content-type is `application/json` and template:
```
{
  "body" : $input.json('$'),
  "headers": {
    #foreach($header in $input.params().header.keySet())
    "$header": "$util.escapeJavaScript($input.params().header.get($header))" #if($foreach.hasNext),#end
    
    #end
  },
  "params": {
    #foreach($param in $input.params().path.keySet())
    "$param": "$util.escapeJavaScript($util.urlDecode($input.params().path.get($param)))" #if($foreach.hasNext),#end
    
    #endhjkyt
  },
  "query": {
    #foreach($queryParam in $input.params().querystring.keySet())
    "$queryParam": "$util.escapeJavaScript($util.urlDecode($input.params().querystring.get($queryParam)))" #if($foreach.hasNext),#end
    #end
  },
  "stage" : "$context.stage",
"requestId" : "$context.requestId",
"apiId" : "$context.apiId",
"resource-path" : "$context.resourcePath",
"resourceId" : "$context.resourceId",
"httpMethod" : "$context.httpMethod",
"sourceIp" : "$context.identity.sourceIp",
"userAgent" : "$context.identity.userAgent",
"accountId" : "$context.identity.accountId",
"apiKey" : "$context.identity.apiKey",
"caller" : "$context.identity.caller",
"user" : "$context.identity.user",
"userArn" : "$context.identity.userArn"
}

```

 More on [Intergration Requests](http://docs.aws.amazon.com/apigateway/latest/developerguide/api-gateway-mapping-template-reference.html).
 `$input.params()` parse the request object for the corresponding variable and allows the mapping template to build a JSON object. 
 
    ![Screencast](https://github.com/dhanugupta/cart-aws-lambda/blob/master/aws-api-gateway-cart-REST.png?raw=true)
   
5.  Let's ensure the response is correct. Cart Micro Service will respond as  valid JSON.
6. Lambda cannot return valid JSON Response.

7. Now let's deploy this API, so we can test it! Click the Deploy API button.

###Connecting the dots & Testing

1. We should now have a publically accessible GET endpoint. Ex: `https://xxxx.execute-api.us-west-2.amazonaws.com/prod/cart`
2. Make sure you add API Key to API Gateway for security purpose. Each client has to provie `x-api-key:xxxxxxxx` to access each REST API's.
3. You can access read API as `https://xxxx.execute-api.us-west-2.amazonaws.com/prod/cart/read?loginId=xxxx` and response will be JSON Object like
```
{ cart:[],
  code:200,
  loginId:xxx}
```

##Troubleshooting

1. Cloudwatch logs are your friends.



