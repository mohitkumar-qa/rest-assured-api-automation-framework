package com.api.base;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import com.api.utils.ApiTestContext;
import com.api.utils.ConfigReader;

import io.qameta.allure.Allure;
import io.restassured.RestAssured;

public class BaseTest {

    @BeforeClass
    public void setup() {

        ConfigReader.loadProperties();
        RestAssured.baseURI = ConfigReader.getBaseUrl();
    }

    public static String getBaseUrl() {
        return ConfigReader.getBaseUrl();
    }

    @AfterMethod
    public void attachApiDetailsOnFailure(ITestResult result) {

        if (!result.isSuccess()) {

            String request = ApiTestContext.getRequestDetails();
            String response = ApiTestContext.getResponseDetails();

            if (request != null) {
                Allure.addAttachment(
                        "API Request",
                        "text/plain",
                        request);
            }

            if (response != null) {
                Allure.addAttachment(
                        "API Response",
                        "text/plain",
                        response);
            }
        }

        ApiTestContext.clear();
    }
}