package com.restaurant.menu;

import com.mongodb.client.MongoDatabase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;


import java.util.List;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
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

    // rest-assured integration test
    @Test
    void whenRequestGet_thenOK(){
        when().request("GET", "/api/menus").then().statusCode(200);
    }

    @Test
    void whenRequestGet_thenContentTypeJSON(){
        when().get("/api/menus").then().contentType(ContentType.JSON);
    }
}
