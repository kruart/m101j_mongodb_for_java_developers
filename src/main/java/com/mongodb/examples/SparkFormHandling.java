package com.mongodb.examples;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import spark.Spark;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.halt;

/**
 * Created by kruart on 02.06.2017.
 */
public class SparkFormHandling {

    public static void main(String[] args) {
        //Configure Freemarker
        Configuration configuration = new Configuration(new Version("2.3.23")); //freemarker version
        configuration.setClassForTemplateLoading(SparkFormHandling.class, "/");

        //Configure routes
        Spark.get("/", (request, response) -> {
            try {
                Map<String, Object> fruitsMap = new HashMap<>();
                fruitsMap.put("fruits", Arrays.asList("apple", "orange", "banana", "peach"));

                Template fruitsPickerTemplate = configuration.getTemplate("fruitPicker.ftl");
                StringWriter writer = new StringWriter();
                fruitsPickerTemplate.process(fruitsMap, writer);
                return writer;
            } catch (Exception e) {
                halt(500);
                return null;
            }
        });

        Spark.post("/favorite_fruit", (request, response) -> {
            String fruit = request.queryParams("fruit");
            if (fruit == null) {
                return "Why don't you pick one?";
            } else {
                return "Your favorite fruit is " + fruit;
            }

        });
    }
}
