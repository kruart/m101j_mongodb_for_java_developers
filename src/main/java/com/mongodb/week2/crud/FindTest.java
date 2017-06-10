package com.mongodb.week2.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.util.Helpers.printJson;

/**
 * Created by kruart on 10.06.2017.
 */
public class FindTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> coll = db.getCollection("findTest");
        coll.drop();

        for (int i = 0; i < 10; i++) {
            coll.insertOne(new Document("x", i));
        }

        System.out.println("Find One: ");
        Document first = coll.find().first();
        printJson(first);
        System.out.println("Find all with into: ");
        List<Document> findAll = coll.find().into(new ArrayList<>());
        for (Document document : findAll) {
            printJson(document);
        }
        System.out.println("Find all with iteration: ");
        MongoCursor<Document> cursor = coll.find().iterator();
        try {
            while (cursor.hasNext()) {
                printJson(cursor.next());
            }
        } finally {
            cursor.close();
        }

        System.out.println("Count: " + coll.count());
    }
}
