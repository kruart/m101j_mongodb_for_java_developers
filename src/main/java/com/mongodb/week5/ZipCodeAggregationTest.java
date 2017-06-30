package com.mongodb.week5;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kruart on 30.06.2017.
 */
public class ZipCodeAggregationTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> zipcode = db.getCollection("zipcodes");

        List<Document> pipeline = Arrays.asList(
                new Document("$group", new Document("_id", "$state")
                        .append("totalPop", new Document("$sum", "$pop"))),
                new Document("$match", new Document("totalPop", new Document("$gte", 10000000))));

        ArrayList<Document> results = zipcode.aggregate(pipeline).into(new ArrayList<>());

        for (Document cur : results) {
            System.out.println(cur.toJson());
        }
    }
}
