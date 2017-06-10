package com.mongodb.week2.crud;

import com.mongodb.week2.util.Helpers;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by kruart on 10.06.2017.
 */
public class DocumentTest {

    public static void main(String[] args) {
        Document document = new Document()
                .append("str", "MongoDB, Hello")
                .append("int", 42)
                .append("l", 1L)
                .append("double", 20.30)
                .append("b", false)
                .append("date", new Date())
                .append("objectID", new ObjectId())
                .append("null", null)
                .append("embeddedDoc", new Document("x", 0))
                .append("list", Arrays.asList(1, 2, 3));

        Helpers.printJson(document);
    }
}
