package com.mongodb.week1.examples;

import spark.Spark;

/**
 * Created by kruart on 02.06.2017.
 */
public class SparkRoutes {

    public static void main(String[] args) {
        Spark.get("/", (request, response) -> "Hello World\n");
        Spark.get("/test", (request, response) -> "This is a test page\n");
        Spark.get("/echo/:thing", (request, response) -> request.params(":thing")); //:thing - placeholder, there can be everything you want
    }
}
