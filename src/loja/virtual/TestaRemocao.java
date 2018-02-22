package loja.virtual;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import loja.virtual.database.ConnectionPool;

public class TestaRemocao {

    public static void main(String[] args) throws SQLException {
        
        Connection conexao = new ConnectionPool().getConnection();
        
        Statement statement = conexao.createStatement();
        
        statement.execute("delete from produto where id > 3");
        
        int linhas = statement.getUpdateCount();
           
        System.out.println(linhas + " linhas deletadas");

        statement.close();
        conexao.close();     
        
    }   
}
