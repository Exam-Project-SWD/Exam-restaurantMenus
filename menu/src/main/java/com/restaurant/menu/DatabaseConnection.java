package com.restaurant.menu;


import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// database connection class to connect to mongodb
// it uses a logger to log errors and it uses dotenv to load the connection string from .env file
public class DatabaseConnection {
    private static Logger logger =  LogManager.getLogger(DatabaseConnection.class);
    Dotenv dotenv = Dotenv.load();
    ConnectionString connectionString = new ConnectionString(dotenv.get("mongoConnectionString"));
    MongoClient mongoClient = MongoClients.create(connectionString);
    MongoDatabase db = null;
    public MongoDatabase getConnection(){
        try {
            db = mongoClient.getDatabase("mtogo");
        } catch(MongoException me) {
            System.err.println(me);
            logger.error(me);
        }
        return db;
    }

    public void closeConnection(){
        mongoClient.close();
    }
}
