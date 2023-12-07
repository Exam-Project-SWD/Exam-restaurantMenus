package com.restaurant.menu;

import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RestaurantMenuIT {
    DatabaseConnection dbconn = new DatabaseConnection();

    @AfterEach
    void tearDown(){
        dbconn.closeConnection();
    }

    @Test
    void getConnectionTest(){

        MongoDatabase db = dbconn.getConnection();
        assertNotNull(db);
    }

    @Test
    void getMenusIntegrationTest(){
        GetRestaurantMenus grm = new GetRestaurantMenus();
        List<String> menus = grm.getMenus();
        assertTrue(!menus.isEmpty());
    }
}
