package com.org.cart.dao;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedList;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.org.cart.exception.DAOException;
import com.org.cart.model.cart.CartDAO;
import com.org.cart.model.cart.CartDAOImpl;

public class DynamoDBManager {

	// credentials for the client come from the environment variables pre-configured by Lambda. These are tied to the
    // Lambda function execution role.new BasicAWSCredentials(amazonAWSAccessKey,amazonAWSSecretKey)
    private static AmazonDynamoDBClient dynamoDB;

    public static void init() throws Exception,DAOException {
    	dynamoDB = new AmazonDynamoDBClient();
    }

    public static void save(Object entity) {
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
        mapper.save(entity);
    }

    public static <T> PaginatedList<T> query(Class<T> klass, DynamoDBQueryExpression query) {
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
        PaginatedList<T> list = mapper.query(klass, query);
        return list;
    }

    public static <T> T get(Class<T> klass, DynamoDBQueryExpression query) {
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
        PaginatedList<T> list = mapper.query(klass, query);
        if (list.isEmpty()) {
        	return null;
        }
        return list.get(0);
    }

    public static List<String> tableNames() {
    	ListTablesResult tables = dynamoDB.listTables();
    	List<String> result = new ArrayList<String>();
    	for (String tableName : tables.getTableNames()) {
    		result.add(tableName);
    	}
    	return result;
    }

    /**
     * Contains the implementations of the DAO objects. By default we only have a DynamoDB implementation
     */
    public enum DAOType {
        DynamoDB
    }

    public static CartDAO getCartDAO(DAOType daoType) {
        CartDAO dao = null;
        switch (daoType) {
            case DynamoDB:
                dao = CartDAOImpl.getInstance();
                break;
        }

        return dao;
    }

    public static CartDAO getCartDAO() {
        return getCartDAO(DAOType.DynamoDB);
    }

}
