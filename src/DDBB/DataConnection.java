package DDBB;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

import java.util.Random;

/**
 * Created by 47767573t on 25/02/16.
 */
public class DataConnection {
    Random rnd = new Random();

    private static DataConnection INSTANCE = null;
    private final String PATH = "./soccerData.db"+rnd.nextInt(1000000);

    private static ObjectContainer db;

    private synchronized static void createInstance(){
        if(INSTANCE == null){
            INSTANCE = new DataConnection();
            INSTANCE.performConnection();
        }
    }

    private void performConnection() {
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        db = Db4oEmbedded.openFile(config, PATH);
    }

    public static ObjectContainer getInstance(){
        if(INSTANCE == null)
            createInstance();
        return db;
    }

    public void closeConnection(){
        db.close();
    }
}
