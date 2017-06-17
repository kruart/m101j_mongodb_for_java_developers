package com.mongodb.week3.hw;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;

/**
 * Download the students.json file from the Download Handout link and import it into your local Mongo instance with this command:
 * mongoimport --drop -d school -c students students.json
 *
 * This dataset holds the same type of data as last week's grade collection, but it's modeled differently.
 * You might want to start by inspecting it in the Mongo shell.
 * Write a program in the language of your choice that will remove the lowest homework score for each student.
 * Since there is a single document for each student containing an array of scores,
 * you will need to update the scores array and remove the homework.
 *
 * Remember, just remove a homework score. Don't remove a quiz or an exam!
 *
 * Hint/spoiler: With the new schema, this problem is a lot harder and that is sort of the point.
 * One way is to find the lowest homework in code and then update the scores array with the low homework pruned.
 *
 * Created by kruart on 17.06.2017.
 */
public class HW3_1 {

    public static void main(String[] args) {
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
        MongoDatabase db = client.getDatabase("school");
        MongoCollection<Document> students = db.getCollection("students");

        Document getAllArrayItems = new Document("$unwind", "$scores"); //return output document for each element of array
        Document matchType = new Document("$match", new Document("scores.type", "homework"));   //leave only with the type='homework'
        Document groupBy = new Document("$group", new Document("_id", "$_id").append("min", new Document("$min", "$scores.score")));
        AggregateIterable<Document> aggregate = students.aggregate(Arrays.asList(getAllArrayItems, matchType, groupBy));

        for (Document doc : aggregate) {
            students.updateOne(
                    new Document("_id", doc.get("_id")),    //find by id
                    new Document("$pull", new Document("scores", new Document("score", doc.get("min"))))); //find and remove item from array
        }
    }
}
