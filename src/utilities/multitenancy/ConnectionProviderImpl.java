package utilities.multitenancy;


import com.mysql.cj.jdbc.MysqlDataSource;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionProviderImpl implements ConnectionProvider {

    private static final long serialVersionUID = 1697402608114701057L;

    private final MysqlDataSource basicDataSource = new MysqlDataSource();

    public ConnectionProviderImpl(String database) {

        // these should come from a property file
        this.basicDataSource.setUrl("jdbc:mysql://127.0.0.1/" + database + "?serverTimezone=UTC");
        this.basicDataSource.setUser("sprava_cien_project");
        this.basicDataSource.setPassword("cps2018");
    }

    @Override
    public boolean isUnwrappableAs(Class arg0) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> arg0) {
        return null;
    }

    @Override
    public void closeConnection(Connection arg0) throws SQLException {
        arg0.close();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return this.basicDataSource.getConnection();
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

}
