package org.pack.mongo.services;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ReadMongoDBDocument {

	public static void main(String[] args) {
		ReadMongoDBDocument mongoDBDocument = new ReadMongoDBDocument();
		mongoDBDocument.readData();
	}

	public void readData() {
		try (MongoClient mongoClient = new MongoClient(Arrays.asList(new ServerAddress("localhost", 27017)));) {
			MongoDatabase db = mongoClient.getDatabase("local");
			readTableData(db);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getSuppressed());
		}
	}

	public void readTableData(MongoDatabase mongoDB) {
		System.out.println("Enter table to read data");
		try (Scanner sc = new Scanner(System.in);) {
			String tableToRead = sc.nextLine();
			MongoCollection<Document> coll = mongoDB.getCollection(tableToRead);
			int count = 1;
			for (Document tempDocument : coll.find()) {
				iterateDocument(tempDocument, count++);
			}
		}
	}

	private void iterateDocument(Document document, int count) {
		Iterator<String> rowKeyIterator = document.keySet().iterator();
		System.out.println( "Record " + count );
		while (rowKeyIterator.hasNext()) {
			String key = rowKeyIterator.next();
			System.out.println("Column : " + key + ", Value : " + document.get(key));
		}
	}
}
