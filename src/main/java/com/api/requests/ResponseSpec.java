package com.api.requests;

import java.util.concurrent.TimeUnit;

import com.api.utils.ConfigReader;
import static org.hamcrest.Matchers.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.ResponseSpecification;


public class ResponseSpec {

    public static ResponseSpecification getResponseSpec() {

        return new ResponseSpecBuilder()
        		.expectResponseTime(
        				lessThan(ConfigReader.getResponseTimeout()),
        				TimeUnit.MILLISECONDS)
        		.log(LogDetail.ALL)
                .build();
    }
}