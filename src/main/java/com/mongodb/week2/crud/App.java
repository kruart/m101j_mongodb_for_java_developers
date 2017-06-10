package com.mongodb.week2.crud;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by kruart on 09.06.2017.
 */
public class App {

    public static void main(String[] args) {
        MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(100).build();
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017), options);

        MongoDatabase db = client.getDatabase("video");
        MongoCollection<Document> reviews = db.getCollection("reviews");
        System.out.println(reviews.count());
    }
}
