package com.restaurant.menu;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@RestController
@RequestMapping(path = "api", produces = MediaType.APPLICATION_JSON_VALUE)
public class GetRestaurantMenus {
    private static Logger logger =  LogManager.getLogger(GetRestaurantMenus.class);
    @GetMapping("/menus")
    public List<String> getMenus(){
        DatabaseConnection conn = new DatabaseConnection();
        MongoDatabase db = conn.getConnection();
        List<String> menus = new ArrayList<>();
        MongoCollection<Document> collection = db.getCollection("restaurantMenus");
        MongoCursor<Document> cursor = collection.find().iterator();

        try {
            while(cursor.hasNext()) {
                menus.add(cursor.next().toJson());
            }
        } finally {
            logger.info("Database has been used and connection is closed");
            cursor.close();
            conn.closeConnection();
        }

        return menus;
    }
}
