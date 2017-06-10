package com.mongodb.week1.examples;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kruart on 02.06.2017.
 */
public class HelloWorldFreeMarkerStyle {

    public static void main(String[] args) {
        Configuration configuration = new Configuration(new Version("2.3.23"));
        configuration.setClassForTemplateLoading(HelloWorldFreeMarkerStyle.class, "/");

        try {
            Template helloTemplate = configuration.getTemplate("freemarker/hello.ftl");
            StringWriter writer = new StringWriter();
            Map<String, Object> helloMap = new HashMap<>();
            helloMap.put("name", "Arthur");

            helloTemplate.process(helloMap, writer);
            System.out.println(writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
