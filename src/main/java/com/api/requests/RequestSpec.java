package com.api.requests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

import com.api.base.BaseTest;
import com.api.utils.ApiAllureFilter;

public class RequestSpec {

    public static RequestSpecification getRequestSpec() {

        return new RequestSpecBuilder()
                .setBaseUri(BaseTest.getBaseUrl())
                .setContentType("application/json")
                .addFilter(new ApiAllureFilter())
                .log(LogDetail.ALL)
                .build();
    }
}