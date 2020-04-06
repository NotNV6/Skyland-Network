package rip.skyland.commons.mongo;


import com.google.gson.JsonParser;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.Document;
import rip.skyland.commons.module.Module;

import java.util.Collections;

/**
 * The mongo handler is used to handle all mongo connections
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MongoAPI extends Module {

    private static MongoAPI instance;

    private final String hostname;
    private final int port;

    // authentication fields
    private String username;
    private String password;
    private String authenticateDatabase;
    private boolean authenticate;

    // mongo fields
    private MongoClient mongoClient;

    // json parser
    private final JsonParser parser = new JsonParser();

    /**
     * Constructor for creating the module API without any authentication methods (PASSWORD)
     *
     * @param hostname the ip address of the mongo server
     * @param port     the port of the mongo server
     */
    public MongoAPI(String hostname, int port) {
        instance = this;

        this.hostname = hostname;
        this.port = port;

        authenticate = false;
    }

    /**
     * Constructor for creating the module API with authentication (PASSWORD)
     *
     * @param hostname               the ip address of the mongo server
     * @param password               the password of the mongo database
     * @param authenticationDatabase the database to authenticate with
     * @param port                   the port of the mongo server
     */
    public MongoAPI(String hostname, String username, String password, String authenticationDatabase, int port) {
        this(hostname, port);

        this.username = username;
        this.password = password;
        this.authenticateDatabase = authenticationDatabase;

        authenticate = true;
    }

    /**
     * Connect to the mongo database and setup fields
     */
    @Override
    public void enable() {
        super.enable();

        System.out.println(authenticate);

        this.mongoClient = !authenticate ?
                new MongoClient(hostname, port) :
                new MongoClient(new ServerAddress(hostname, port), Collections.singletonList(MongoCredential.createCredential(username, authenticateDatabase, password.toCharArray())));
    }

    /**
     * Get a collection by a name.
     * If the collection doesn't exist already it creates the collection.
     *
     * @param collectionName the name of the collection
     * @return the collection
     */
    public MongoCollection<Document> getCollection(String database, String collectionName) {
        MongoDatabase mongoDatabase = mongoClient.getDatabase(database);

        if (mongoDatabase.getCollection(collectionName) == null) {
            mongoDatabase.createCollection(collectionName);
        }

        return mongoDatabase.getCollection(collectionName);
    }

    /**
     * Get the current instance of the API.
     *
     * @return the instance
     */
    public static MongoAPI get() {
        return instance;
    }
}