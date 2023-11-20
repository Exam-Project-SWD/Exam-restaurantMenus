package com.restaurant.menu;


import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;


public class DatabaseConnection {
    Dotenv dotenv = Dotenv.load();
    ConnectionString connectionString = new ConnectionString(dotenv.get("mongoConnectionString"));
    MongoClient mongoClient = MongoClients.create(connectionString);
    MongoDatabase db = null;
    public MongoDatabase getConnection(){
        try {
            db = mongoClient.getDatabase("mtogo");
        } catch(MongoException me) {
            System.err.println(me);
        }
        return db;
    }

    public void closeConnection(){
        mongoClient.close();
    }
}
