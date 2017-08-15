package org.pack.mongo.services;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class CreateMongoDBDocument {

	public static void main(String[] args) {
		CreateMongoDBDocument createMongoDBDocument = new CreateMongoDBDocument();
		try (Scanner sc = new Scanner(System.in);
				// Create a mongo client
			MongoClient mongoClient = new MongoClient(Arrays.asList(new ServerAddress("localhost", 27017)));) {

			// Get defauly DB by the name 'local'
			MongoDatabase mongoDB = mongoClient.getDatabase("local");

			// Get all collections
			MongoIterable<String> colls = mongoDB.listCollectionNames();

			// Iterate over the collection to output the collection names.
			for (String s : colls) {
				System.out.println(s);
			}

			System.out.println("Enter table name to create");
			String tableToCreate = sc.nextLine();
			MongoCollection<Document> coll = createMongoDBDocument.createTableAndInsertData(mongoDB, tableToCreate);

			//Read table data
//			createMongoDBDocument.readDBCollectionInfo(coll);

			//Read table data
//			ReadMongoDBDocument readMongoDBDocument = new ReadMongoDBDocument();
//			readMongoDBDocument.readTableData(mongoDB);

		}
	}

	private void readDBCollectionInfo(MongoCollection<Document> coll) {
		/*
		 * The MongoCollection<TDocument> interface provides overloaded find()
		 * method to find a Document instance. Next, obtain the document added
		 * using the find() method. Furthermore, the find() method returns an
		 * iterable collection from which we obtain the first document using the
		 * first() method.
		 */
		Document dbObj = coll.find().first();

		/*
		 * Output the Document object found as such and also by iterating over
		 * the Set<E> obtained from the Document using the keySet() method. The
		 * keySet() method returns a Set<String>. Create an Iterator from the
		 * Set<String> using the iterator() method. While the Iterator has
		 * elements as determined by the hasNext() method, obtain the elements
		 * using the next() method. Each element is a key in the Document
		 * fetched. Obtain the value for the key using the get(String key)
		 * method in Document.
		 */
		System.out.println("DB Object : " + dbObj);
		Set<String> set = dbObj.keySet();
		Iterator<String> iter = set.iterator();
		while (iter.hasNext()) {
			String obj = iter.next();
			System.out.println(obj);
			System.out.println(dbObj.get(obj));
		}
	}

	private MongoCollection<Document> createTableAndInsertData(MongoDatabase mongoDB, String tableToCreate) {
		/*
		 * create a new MongoCollection<Document>instance using the
		 * getCollection(String collectionName) method in MongoDatabase. Create
		 * a collection of Document instances called catalog. A collection gets
		 * created implicitly when the getCollection(String) method is invoked.
		 */
		MongoCollection<Document> coll = mongoDB.getCollection(tableToCreate);

		/*
		 * Create a Document instance using the Document(String key, Object
		 * value) constructor and use the append(String key, Object value)
		 * method to append key/value pairs. The append() method may be invoked
		 * multiple times in sequence to add multiple key/value pairs. Add
		 * key/value pairs for the journal, publisher, edition, title, and
		 * author fields.
		 */
		Document catalog = new Document("_id","simpleCatalog").append("journal", "Oracle Magazine").append("publisher", "Oracle Publishing")
				.append("edition", "November December 2013").append("title", "Engineering as a Service")
				.append("author", "David A. Kelly");

		/*
		 * The MongoCollection<TDocument> interface provides insertOne(TDocument
		 * document) method to add a document(s) to a collection. Add the
		 * catalog Document to the MongoCollection<TDocument> instance for the
		 * catalog collection.
		 */
		coll.insertOne(catalog);

		return coll;
	}

}
