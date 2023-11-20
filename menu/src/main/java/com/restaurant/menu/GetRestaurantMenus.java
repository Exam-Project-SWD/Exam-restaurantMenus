package com.restaurant.menu;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api")
public class GetRestaurantMenus {

    @GetMapping("/menus")
    public List getMenus(){
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
            cursor.close();
            conn.closeConnection();
        }

        return menus;
    }
}
