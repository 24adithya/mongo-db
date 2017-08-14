package org.pack.mongo.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	
	private void readData() {
		try(BufferedReader br = new BufferedReader(new FileReader("Z:\\")); ) {
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getSuppressed()); 
		}
		MongoClient mongoClient = new MongoClient(Arrays.asList(new ServerAddress("localhost", 27017)));
		MongoDatabase db = mongoClient.getDatabase("local");
		readTableData(db);
	}

	public void readTableData(MongoDatabase mongoDB) {

		Scanner sc = new Scanner(System.in);
		String tableToRead = sc.nextLine();

		MongoCollection<Document> coll = mongoDB.getCollection(tableToRead);
		FindIterable<Document> documents = coll.find();
		for (Document tempDocument : documents) {
			iterateDocument(tempDocument);
		}
	}

	private void iterateDocument(Document document) {
		Set<String> rowKeys = document.keySet();
		Iterator<String> rowKeyIterator = rowKeys.iterator();
		int count = 1;
		while (rowKeyIterator.hasNext()) {
			String key = rowKeyIterator.next();
			System.out.println("Record " + count++ + " : " + key + ", " + document.get(key));
		}
	}
}
