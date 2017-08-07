package org.pack.mongo.services;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class CreateMongoDBDocumentModel {

	public static void main(String[] args) {
		
		MongoClient mongoClient = null;
		try {
			// Create a mongo client
			mongoClient = new MongoClient(Arrays.asList(new ServerAddress("localhost", 27017)));

			// Get defauly DB by the name 'local'
			MongoDatabase mongoDB = mongoClient.getDatabase("local");
			
			CreateMongoDBDocumentModel createMongoDBDocumentModel = new CreateMongoDBDocumentModel();
			
			//Insert multiple records
			createMongoDBDocumentModel.insertMultipleRecords(mongoDB);
		}
		finally {
			mongoClient.close();
		}
	}

	private void insertMultipleRecords(MongoDatabase mongoDB) {
		
		mongoDB.getCollection("catalog");
		
		Catalog adamsCatalog = new Catalog("Adams", "Journal1", "Adams publisher", "2017", "King Adams", "Adams");
		Catalog catalog24 = new Catalog("24", "Journal2", "24 publisher", "2017", "King 24", "24");
		
		Document documents = new Document();
		documents.append("adams", adamsCatalog);
		documents.append("24", catalog24);
		
		List<Document> documentList = new LinkedList<>();
		documentList.add(documents);
		
		
	}
}
