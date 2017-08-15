package org.pack.mongo.services;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
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
//			createMongoDBDocumentModel.insertMultipleRecords(mongoDB);
			createMongoDBDocumentModel.insertMultipleRecordsVariant(mongoDB);
			//Iterate over collection
			ReadMongoDBDocument readMongoDBDocument = new ReadMongoDBDocument();
			readMongoDBDocument.readTableData(mongoDB);
		}
		finally {
			mongoClient.close();
		}
	}

	private void insertMultipleRecords(MongoDatabase mongoDB) {
		
		MongoCollection<Document> coll = mongoDB.getCollection("catalog");
		
		Catalog adamsCatalog = new Catalog("Adams", "Journal1", "Adams publisher", "2017", "King Adams", "Adams");
		Catalog catalog24 = new Catalog("24", "Journal2", "24 publisher", "2017", "King 24", "24");
		
		Document documents = new Document();
		documents.append("adams", adamsCatalog);
		documents.append("24", catalog24);
		
		List<Document> documentList = new LinkedList<>();
		documentList.add(documents);
		
		coll.insertMany(documentList);
	}
	
	private void insertMultipleRecordsVariant(MongoDatabase mongoDB) {
		
		MongoCollection<Document> coll = mongoDB.getCollection("catalog");
		
		Catalog adamsCatalog = new Catalog();
		adamsCatalog.put("catalogId", "Adams");
		adamsCatalog.put("journal", "Journal1");
		adamsCatalog.put("publisher", "Adams publisher");
		adamsCatalog.put("edition", "2017");
		adamsCatalog.put("title", "King Adams");
		adamsCatalog.put("author", "Adams");
		
//		putInCollection(adamsCatalog, new String[] {"Adams", "Journal1", "Adams publisher", "2017", "King Adams", "Adams"});
		
		Catalog catalog24 = new Catalog();
		catalog24.put("catalogId", "24");
		catalog24.put("journal", "Journal2");
		catalog24.put("publisher", "24 publisher");
		catalog24.put("edition", "2017");
		catalog24.put("title", "King 24");
		catalog24.put("author", "24");
		
		List<Document> documentList = new LinkedList<>();
		documentList.add(new Document("_id", "Adams").append("adams", adamsCatalog));
		documentList.add(new Document("_id", "24").append("24", catalog24));
		
		coll.insertMany(documentList);
	}
	
	private void putInCollection(Catalog catalog, String[] values) {
		for(String tempString : values) {
			catalog.put(tempString, tempString);
		}
	}
}
