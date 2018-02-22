package loja.virtual;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import loja.virtual.database.ConnectionPool;

public class TestaInsercao {

    public static void main(String[] args) throws SQLException {

        try (Connection conexao = new ConnectionPool().getConnection(); Statement statement = conexao.createStatement()) {
            
            statement.execute("insert into Produto (nome, descricao) values ('notebook 1', 'note core i5')", Statement.RETURN_GENERATED_KEYS);
            
            try (ResultSet result = statement.getGeneratedKeys()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    
                    System.out.println(id+" gerado");                 
                }
            }            
        }
    }
}
