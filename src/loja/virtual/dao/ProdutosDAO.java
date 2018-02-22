package loja.virtual.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import loja.virtual.modelo.Categoria;
import loja.virtual.modelo.Produto;

public class ProdutosDAO {

    private Connection connection;

    public ProdutosDAO(Connection pool) {

        this.connection = pool;
    }

    public List<Produto> lista() throws SQLException {

        List<Produto> lista = new ArrayList<>();

        String sql = "select * from Produto";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.execute();
            transformaResultadosEmProdutos(preparedStatement, lista);
            return lista;
        }
    }

    public void transformaResultadosEmProdutos(PreparedStatement preparedStatement, List<Produto> lista) throws SQLException {

        try (ResultSet rs = preparedStatement.getResultSet()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");

                Produto produto = new Produto(nome, descricao);
                produto.setId(id);

                lista.add(produto);
            }
        }
    }

    public void salva(Produto produto) throws SQLException {

        String sql = "insert into Produto (nome, descricao) values ( ? , ? )";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setString(2, produto.getDescricao());

            preparedStatement.execute();

            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    produto.setId(id);
                    System.out.println("produto inserido com sucesso ...");
                }
            }
        }
    }

    public List<Produto> busca(Categoria categoria) throws SQLException {

        List<Produto> lista = new ArrayList<>();

        String sql = "select * from Produto where categoria_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, categoria.getId());
            preparedStatement.execute();

            transformaResultadosEmProdutos(preparedStatement, lista);
            
        }
        return lista;
    }
}
