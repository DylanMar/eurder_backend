package com.switchfully.eurder_backend.controller;

import com.switchfully.eurder_backend.dto.itemdto.ItemResupplyUrgencyDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ItemControllerIntegrationTest {

    @LocalServerPort
    private int port = 8080;

    @Test
    void whenAdminLogin_whenGetAllMembers_thenGetAllMembers() {
        // GIVEN
        String email = "1";
        String password = "admin";

        //WHEN
        List<ItemResupplyUrgencyDto> actual;
        actual = RestAssured
                .given()
                .contentType(JSON)
                .header("email", email)
                .header("password", password)
                .when()
                .port(port)
                .get("/item/filter=")
                .then()
                .assertThat()
                .extract()
                .body()
                .jsonPath()
                .getList(".", ItemResupplyUrgencyDto.class);

        //THEN
        assertThat(actual).isNotEmpty();
    }

}