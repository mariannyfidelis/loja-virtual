package loja.virtual;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import loja.virtual.modelo.Categoria;
import loja.virtual.dao.CategoriaDAO;
import loja.virtual.dao.ProdutosDAO;
import loja.virtual.database.ConnectionPool;
import loja.virtual.modelo.Produto;

public class TestaCategorias {

    public static void main(String[] args) throws SQLException {

        try (Connection connection = new ConnectionPool().getConnection()) {

            List<Categoria> categorias = new CategoriaDAO(connection).listaComProdutos();

            for (Categoria categoria : categorias) {
                for (Produto produto : categoria.getProdutos()) {
                    System.out.println(categoria.getNome() + " - " + produto.getNome());
                }
            }

            //categorias.forEach(c -> System.out.println(c.getNome()));
        }
    }

}
