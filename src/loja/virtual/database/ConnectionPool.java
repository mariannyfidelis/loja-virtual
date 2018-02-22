package loja.virtual.database;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.hsqldb.jdbc.JDBCPool;

public class ConnectionPool {

    private final DataSource dataSource;
    
    public ConnectionPool() throws SQLException {
        
        JDBCPool pool = new JDBCPool();
        pool.setURL("jdbc:hsqldb:hsql://localhost/loja.virtual");
        pool.setUser("SA");
        pool.setPassword("");
        
        dataSource = pool;   
    }  
    
    public Connection getConnection() throws SQLException{
    
        Connection conexao = dataSource.getConnection();
        
        return conexao;
    }
}
