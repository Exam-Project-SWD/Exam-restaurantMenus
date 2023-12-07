package com.restaurant.menu;

import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RestaurantMenuIT {
    DatabaseConnection dbconn = new DatabaseConnection();

    @AfterEach
    void tearDown(){
        dbconn.closeConnection();
    }

    // this test checks if the database connection is successful
    @Test
    void getConnection_isNotNullTest(){

        MongoDatabase db = dbconn.getConnection();
        assertNotNull(db);
    }

    // this test checks if the database returns some (not empty) menus
    @Test
    void getMenus_isNotEmptyIntegrationTest(){
        GetRestaurantMenus grm = new GetRestaurantMenus();
        List<String> menus = grm.getMenus();
        assertFalse(menus.isEmpty());
    }
}
