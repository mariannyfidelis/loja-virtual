package loja.virtual;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import loja.virtual.database.ConnectionPool;

public class TestaListagem {

    public static void main(String args[]) throws SQLException {

        Connection conexao = new ConnectionPool().getConnection();

        Statement statement = conexao.createStatement();

        statement.execute("select * from produto");

        ResultSet lista = statement.getResultSet();

        while (lista.next()) {

            String id = lista.getString("id");
            String nome = lista.getString("nome");
            String descricao = lista.getString("descricao");

            System.out.println(id + " " + nome + " " + descricao + " ");
        }

        lista.close();
        statement.close();

        conexao.close();

    }
}
