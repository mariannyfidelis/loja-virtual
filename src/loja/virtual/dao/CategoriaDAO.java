package loja.virtual.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import loja.virtual.modelo.Categoria;
import loja.virtual.modelo.Produto;

public class CategoriaDAO {

    private final Connection connection;

    public CategoriaDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Categoria> listaComProdutos() throws SQLException {

        Categoria ultima = null;
        List<Categoria> lista = new ArrayList<>();

        String sql = "SELECT c.id as c_id, c.nome as c_nome, p.id as p_id, p.nome as p_nome, p.descricao as p_descricao "
                + "FROM Categoria as c join Produto as p on p.categoria_id = c.id";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.execute();

            try (ResultSet rs = preparedStatement.getResultSet()) {

                while (rs.next()) {
                    int id = rs.getInt("c_id");
                    String nome = rs.getString("c_nome");

                    if (ultima == null || !ultima.getNome().equals(nome)) {

                        Categoria categoria = new Categoria(id, nome);
                        lista.add(categoria);
                        ultima = categoria;
                    }

                    int id_p = rs.getInt("p_id");
                    String nome_p = rs.getString("p_nome");
                    String descricao_p = rs.getString("p_descricao");

                    Produto produto = new Produto(nome_p, descricao_p);
                    produto.setId(id_p);

                    ultima.adiciona(produto);

                }
            }
        }

        return lista;
    }

    public List<Categoria> lista() throws SQLException {

        List<Categoria> categorias = new ArrayList<>();

        String sql = "select * from categoria";
        Categoria ultima = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.execute();

            try (ResultSet rs = preparedStatement.getResultSet()) {

                while (rs.next()) {
                    Integer id = rs.getInt("id");
                    String nome = rs.getString("nome");

                    Categoria categoria = new Categoria(id, nome);

                    if (categoria == null || !categoria.equals(ultima)) {
                        categorias.add(categoria);
                    }
                }
            }
        }
        return categorias;
    }
}
