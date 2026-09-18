package com.api.utils;

public class ApiTestContext {

    private static final ThreadLocal<String> requestDetails =
            new ThreadLocal<>();

    private static final ThreadLocal<String> responseDetails =
            new ThreadLocal<>();

    public static void setRequestDetails(String details) {
        requestDetails.set(details);
    }

    public static void setResponseDetails(String details) {
        responseDetails.set(details);
    }

    public static String getRequestDetails() {
        return requestDetails.get();
    }

    public static String getResponseDetails() {
        return responseDetails.get();
    }

    public static void clear() {
        requestDetails.remove();
        responseDetails.remove();
    }
}