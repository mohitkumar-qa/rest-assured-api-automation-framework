package com.api.utils;

import static io.restassured.RestAssured.given;

import com.api.endpoints.AuthEndpoints;
import com.api.requests.RequestSpec;

import io.restassured.response.Response;

public class AuthManager {

    public static String getToken() {

        String requestBody =
                "{"
                + "\"username\":\"admin\","
                + "\"password\":\"password123\""
                + "}";

        Response response =
                given()
                    .spec(RequestSpec.getRequestSpec())
                    .body(requestBody)
                .when()
                    .post(AuthEndpoints.CREATE_TOKEN);

        response.then()
                .statusCode(200);

        return response.jsonPath().getString("token");
    }
}