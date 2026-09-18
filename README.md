# Rest Assured API Automation Framework

A Java-based API automation framework built using Rest Assured and TestNG for testing the Restful Booker API.

## Tech Stack

- Java 17
- Rest Assured 6.0.0
- TestNG 7.11.0
- Maven
- Jackson
- Hamcrest
- JSON Schema Validation
- Allure Report
- Jenkins
- Git & GitHub

## Project Structure

```text
rest-assured-api-automation-framework
├── pom.xml
├── testng.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.api
│   │   │       ├── base
│   │   │       ├── endpoints
│   │   │       ├── pojo
│   │   │       ├── requests
│   │   │       └── utils
│   │   └── resources
│   │       └── config.properties
│   │
│   └── test
│       ├── java
│       │   └── com.api.tests
│       └── resources
│           └── schemas
│
└── README.md
