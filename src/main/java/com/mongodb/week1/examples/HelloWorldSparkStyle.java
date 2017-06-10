package com.mongodb.week1.examples;

import spark.Spark;

/**
 * Created by kruart on 02.06.2017.
 */
public class HelloWorldSparkStyle {

    public static void main(String[] args) {
        Spark.get("/hello", (request, response) -> "Hello World From Spark"); //default port: 4567 => localhost:4567/hello
    }
}
