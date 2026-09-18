package com.api.utils;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class ApiAllureFilter implements Filter {

    @Override
    public Response filter(
            FilterableRequestSpecification requestSpec,
            FilterableResponseSpecification responseSpec,
            FilterContext ctx) {

        String request =
                "Method: " + requestSpec.getMethod() + "\n"
                + "URI: " + requestSpec.getURI() + "\n"
                + "Headers:\n" + requestSpec.getHeaders() + "\n"
                + "Body:\n" + requestSpec.getBody();

        ApiTestContext.setRequestDetails(request);

        Response response = ctx.next(requestSpec, responseSpec);

        String responseDetails =
                "Status: " + response.getStatusLine() + "\n"
                + "Headers:\n" + response.getHeaders() + "\n"
                + "Body:\n" + response.asPrettyString();

        ApiTestContext.setResponseDetails(responseDetails);

        return response;
    }
}