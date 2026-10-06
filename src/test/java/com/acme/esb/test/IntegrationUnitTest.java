package com.acme.esb.test;

import jakarta.inject.Inject;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import java.nio.file.Files;
import java.io.File;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.acme.esb.properties.RestConsumer;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@QuarkusTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class IntegrationUnitTest {

    @Inject
    CamelContext context;

    @Inject
    ProducerTemplate producerTemplate;

    @Inject
    ConsumerTemplate consumerTemplate;

    @Inject
    RestConsumer restConsumerConfig;

    @Test
    @Order(1)
    void successTest() throws Exception {
        String body = Files.readString(new File("src/test/resources/data/successRequest.json").toPath());
        RestAssured.given()
            .contentType(ContentType.JSON)
            .body(body)
            .when()
            .post(restConsumerConfig.getServiceName())
            .then().statusCode(200)
        .log().all();
        
    }

    @Test
    @Order(2)
    void failedTest() throws Exception {
        String body = Files.readString(new File("src/test/resources/data/failedRequest.json").toPath());
        AdviceWith.adviceWith(context, "transformationRoute", a -> {
            a.weaveAddFirst().throwException(IllegalArgumentException.class, "invalid input data - test");
        });
        RestAssured.given()
            .contentType(ContentType.JSON)
            .body(body)
            .when()
            .post(restConsumerConfig.getServiceName())
            .then().statusCode(200)
        .log().all();
    }


}