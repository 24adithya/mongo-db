package org.pack.mongo.services;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ReadMongoDBDocument {

	public void readTableData(MongoDatabase mongoDB) {
		
		Scanner sc = new Scanner(System.in);
	    String tableToRead = sc.nextLine();
		
		MongoCollection<Document> coll = mongoDB.getCollection(tableToRead);
		FindIterable<Document> documents = coll.find();
		for(Document tempDocument : documents) {
			iterateDocument(tempDocument);
		}
	}
	
	private void iterateDocument(Document document) {
		Set<String> rowKeys = document.keySet();
		Iterator<String> rowKeyIterator = rowKeys.iterator();
		int count = 1;
		while(rowKeyIterator.hasNext()) {
			String key = rowKeyIterator.next();
			System.out.println("Record " + count++ + " : " + key + ", " + document.get(key));
		}
	}
}
