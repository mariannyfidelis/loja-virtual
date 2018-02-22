package loja.virtual.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import loja.virtual.database.ConnectionPool;
import loja.virtual.modelo.Produto;

public class TestaInsercao_DAO {

    public static void main(String[] args) {
        
        Produto mesa = new Produto("Mesa", "Mesa");

        try (Connection conexao = new ConnectionPool().getConnection()) {

            ProdutosDAO produtosDAO = new ProdutosDAO(conexao);
            produtosDAO.salva(mesa);
            
            List<Produto> lista_produtos = produtosDAO.lista();
            
            System.out.println("\n\nFORMA TRADICIONAL\n\n");
            for (Produto produto : lista_produtos) {
                System.out.println(produto);
            }
            
            System.out.println("\n\nFORMA 1 COM STREAM JAVA 8\n\n");
            lista_produtos.stream().forEach((produto) -> {
                System.out.println(produto);
            });
            
            System.out.println("\n\nFORMA 2 JAVA 8\n\n");
            lista_produtos.forEach(System.out:: println);
        } catch (SQLException e) {
            System.out.println("erro na sintaxe SQL ...");
        }
    }
}
