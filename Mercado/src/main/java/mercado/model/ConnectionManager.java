package mercado.model;

import java.sql.Connection;
import java.sql.SQLException;
import org.postgresql.ds.PGPoolingDataSource;

public class ConnectionManager {

    private PGPoolingDataSource dataSource;

    public Connection getConnection() throws SQLException {
        Connection conn = dataSource.getConnection();
        conn.setAutoCommit(false);
        return conn;
    }

    private static ConnectionManager instance;

    //Singleton
    private ConnectionManager() {
        dataSource = new PGPoolingDataSource();
        dataSource.setDataSourceName("mercado");
        dataSource.setServerName("ec2-107-21-109-15.compute-1.amazonaws.com");
        dataSource.setDatabaseName("d8iile3kkvk06o");
        dataSource.setUser("eddkwtmmuavhoo");
        dataSource.setPassword("8386e6d8653e4a1d390fe80b9ba9b70ec80fb149408d5588e6f3a123802f0b15");
        dataSource.setSsl(true);
        dataSource.setSslfactory("org.postgresql.ssl.NonValidatingFactory");

        dataSource.setMaxConnections(20);
        dataSource.setInitialConnections(5);
    }

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

}
