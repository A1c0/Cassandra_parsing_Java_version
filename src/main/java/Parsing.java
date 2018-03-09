// Import the API classes to access to Apache Cassandra

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.BoundStatement;

public class Parsing {
    // Application settings
    private static String server_ip = "127.0.0.1";
    private static String keyspace = "sampledb";

    // Application connection objects
    private static Cluster cluster = null;
    private static Session session = null;


    public static void openConnection() {
        if(cluster!=null) return;

        cluster = Cluster.builder()
                .addContactPoints(server_ip)
                .build();

        final Metadata metadata = cluster.getMetadata();
        String msg = String.format("Connected to cluster: %s", metadata.getClusterName());
        System.out.println(msg);

        System.out.println("List of hosts");
        for (final Host host : metadata.getAllHosts()){
            msg = String.format("Datacenter: %s; Host: %s; Rack: %s",
                    host.getDatacenter(),
                    host.getAddress(),
                    host.getRack());
            System.out.println(msg);
        }
        session = cluster.connect(keyspace);
    }


    public static void closeConnection() {
        if(cluster!=null){
            cluster.close();
            cluster = null;
            session = null;
        }
    }

    // Method definition
    public static void executeStatement(String statement) {
        session.execute(statement);
    }

    // Methods definition
    public static PreparedStatement getPreparedStatement(String statement) {
        return session.prepare(statement);
    }

    // Methods definition
    public static void executeQuery(String query) {
        for (Row row : session.execute(query)) {
            System.out.println( row.toString() );
        }
    }

    public static void main(String[] args) {

    }
}
