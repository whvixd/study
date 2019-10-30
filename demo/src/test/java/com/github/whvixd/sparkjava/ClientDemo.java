package com.github.whvixd.sparkjava;

import org.junit.BeforeClass;

import static spark.Spark.*;
import static spark.Spark.internalServerError;

/**
 * Created by wangzhx on 2019/10/30.
 */
public class ClientDemo {
    @BeforeClass
    public static void startClient() {
        port(8080);
        path("/API/v1/inner/rest/internal", () -> {
            get("/object_SQ2Ny__c/5d9c3b217cfed927cf173ce3", (request, response) -> "{Hello}");
        });
        //404
        notFound("<html><body><h1>Custom 404 handling</h1></body></html>");
        //500
        internalServerError("<html><body><h1>Custom 500 handling</h1></body></html>");
    }
}
