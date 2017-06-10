package com.mongodb.week2.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by kruart on 10.06.2017.
 */
public class InsertTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> coll = db.getCollection("insertTest");
        coll.drop();

        Document smith = new Document("name", "Smith")
                .append("age", 30)
                .append("profession", "programmer");

//        Document jones = new Document("name", "Jones")
//                .append("age", 26)
//                .append("profession", "hacker");

//        printJson(smith); //not id field
        coll.insertOne(smith);
        smith.remove("_id");
        coll.insertOne(smith);
//        coll.insertMany(Arrays.asList(smith, jones));
//        printJson(smith); //but when we insert field to db, mongoDB insert _id for us in our document
    }
}
