package com.mongodb.examples;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import spark.Spark;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.halt;

/**
 * Created by kruart on 02.06.2017.
 */
public class HelloWorldSparkFreemarkerStyle {

    public static void main(String[] args) {
        Configuration configuration = new Configuration(new Version("2.3.23")); //freemarker version
        configuration.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class, "/");

        //default port: 4567 => localhost:4567/hello
        Spark.get("/hello", (request, response) -> {
            StringWriter writer = new StringWriter();

            try {
                Template helloTemplate = configuration.getTemplate("hello.ftl");

                Map<String, Object> helloMap = new HashMap<>();
                helloMap.put("name", "Arthur");

                helloTemplate.process(helloMap, writer);

            } catch (Exception e) {
                halt(500); //return status error
                e.printStackTrace();
            }
            return  writer;
        });


    }
}
