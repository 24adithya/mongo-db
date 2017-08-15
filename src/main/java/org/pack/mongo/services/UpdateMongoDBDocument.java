package org.pack.mongo.services;

import java.util.Arrays;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;

public class UpdateMongoDBDocument {

	public static void main(String[] args) {
		UpdateMongoDBDocument updateMongoDBDocument = new UpdateMongoDBDocument();
		updateMongoDBDocument.updateExistingDBDocument();
	}

	private void updateExistingDBDocument() {
		System.out.println("Enter table/collection to update data");
		try (MongoClient mongoClient = new MongoClient(Arrays.asList(new ServerAddress("localhost", 27017)));
				Scanner sc = new Scanner(System.in);) {
			MongoDatabase mongoDB = mongoClient.getDatabase("local");
			String tableToUpdate = sc.nextLine();
			
			MongoCollection<Document> coll = mongoDB.getCollection(tableToUpdate);
			
			System.out.println("Enter document id to update data");
			String documentToUpdate = sc.nextLine();
			
			System.out.println("Enter columns/keys to update data");
			String columnsToUpdate = sc.nextLine();
			String[] columnsToUpdateArray = columnsToUpdate.split(" ");
			
			System.out.println("Enter new columns values/keys to update data");
			String columnValues = sc.nextLine();
			String[] columnValuesArray = columnValues.split(" ");
			
			Document newDocument = new Document();
			int count = 0;
			for(String tempColumn : columnsToUpdateArray) {
				newDocument.append(documentToUpdate + "." + tempColumn, columnValuesArray[count++]);
			}
			
			coll.updateOne(new Document("_id", documentToUpdate),new Document("$set", newDocument));
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getSuppressed());
		}
	}

	private void updateTableData(MongoDatabase mongoDB) {
		
		
	}
}
