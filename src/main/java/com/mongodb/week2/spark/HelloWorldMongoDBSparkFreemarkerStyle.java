package com.mongodb.week2.spark;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import org.bson.Document;
import spark.Spark;

import java.io.StringWriter;

import static spark.Spark.halt;

public class HelloWorldMongoDBSparkFreemarkerStyle {
    public static void main(String[] args)  {
        final Configuration configuration = new Configuration(new Version("2.3.23"));
        configuration.setClassForTemplateLoading(HelloWorldMongoDBSparkFreemarkerStyle.class, "/freemarker");

        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

        MongoDatabase database = client.getDatabase("course");
        MongoCollection<Document> collection = database.getCollection("hello");
        collection.drop();

        collection.insertOne(new Document("name", "MongoDB"));

        Spark.get("/", (request, response) -> {
            StringWriter writer = new StringWriter();
            try {
                Template helloTemplate = configuration.getTemplate("hello.ftl");
                Document document = collection.find().first();
                helloTemplate.process(document, writer);
            } catch (Exception e) {
                halt(500);
                e.printStackTrace();
            }
            return writer;
        });
    }
}
