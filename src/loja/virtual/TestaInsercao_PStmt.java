package loja.virtual;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import loja.virtual.database.ConnectionPool;

public class TestaInsercao_PStmt {

    public static void main(String[] args) {

        try (Connection conexao = new ConnectionPool().getConnection()) {

            conexao.setAutoCommit(false);
            
            try (PreparedStatement prepared_statement = conexao.prepareStatement("insert into Produto (nome, descricao) values ( ? , ? )",
                    PreparedStatement.RETURN_GENERATED_KEYS)) {

                
                adiciona(prepared_statement, "TV LCD 32", "32 polegadas");
                adiciona(prepared_statement, "Blueray", "Full HDMI");
                conexao.commit();
                
            }
            catch(Exception ex){
                ex.printStackTrace();
                conexao.rollback();           
            }
        } catch (SQLException e) {
            System.out.println("erro na sintaxe SQL ...");
        }
    }

    private static void adiciona(PreparedStatement prepared_statement, String nome, String descricao) throws SQLException {
        prepared_statement.setString(1, nome);
        prepared_statement.setString(2, descricao);

        if (nome.equals("Blueray")) {

            throw new IllegalArgumentException("Ocorreu um problema !!!");
        }
        prepared_statement.execute();

        ResultSet result = prepared_statement.getGeneratedKeys();

        while (result.next()) {
            int id = result.getInt("id");

            System.out.println(id + " gerado");
        }
        result.close();
    }
}
