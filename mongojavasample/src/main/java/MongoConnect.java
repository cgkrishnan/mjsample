import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import org.json.JSONObject;

import java.net.UnknownHostException;
import java.util.List;

public class MongoConnect {

    public MongoClient mongo;

    public MongoConnect() {
        try {
            mongo = new MongoClient( "localhost" , 27017 );
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    //Method to test Connection - Read the list of Databases.
    public List<String> readData() {

        List<String> dbs = mongo.getDatabaseNames();
        return  dbs;

    }

    public void writeData(JSONObject json) {

        DB db = mongo.getDB("cgk");
        DBCollection table = db.getCollection("mjtest");
        BasicDBObject document = new BasicDBObject();
        document.put("auctionID",json.getString("auctionId"));
        table.insert(document);
    }
}
