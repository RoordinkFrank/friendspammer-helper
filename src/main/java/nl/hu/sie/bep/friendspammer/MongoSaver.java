package nl.hu.sie.bep.friendspammer;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoSaver {
	
	private static Logger logger = LoggerFactory.getLogger(MongoSaver.class);
	
	private MongoSaver(){}
	
	public static boolean saveEmail(String to, String from, String subject, String text, Boolean html) {
		
		String database = "friendspammer";
		
		boolean success = true;
		
		MongoClientURI uri = new MongoClientURI("mongodb+srv://opdracht2admin:opdracht2frank@cluster0-725c7.mongodb.net/test?retryWrites=true");

	    
	    //new ServerAddress("ds227939.mlab.com", 27939), credential, MongoClientOptions.builder().build()
		try (MongoClient mongoClient = new MongoClient(uri) ) {
			
			MongoDatabase db = mongoClient.getDatabase( database );
			
			MongoCollection<Document> c = db.getCollection("email");
			
			Document  doc = new Document ("to", to)
			        .append("from", from)
			        .append("subject", subject)
			        .append("text", text)
			        .append("asHtml", html);
			c.insertOne(doc);
		} catch (MongoException mongoException) {
			logger.info("XXXXXXXXXXXXXXXXXX ERROR WHILE SAVING TO MONGO XXXXXXXXXXXXXXXXXXXXXXXXXX");
			mongoException.printStackTrace();
			success = false;
		}
		return success;
 		
	}

}
