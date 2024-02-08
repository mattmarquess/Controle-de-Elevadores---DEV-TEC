package app.repositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import app.Tarefa;

public class H2RepositorioTarefa implements RepositorioTarefa {
    private static final String URL = "jdbc:h2:./todo-list-db";
    private static final String USUARIO = "sa";
    private static final String SENHA = "";

    public H2RepositorioTarefa() {
        criarTabelaSeNaoExistir();
    }

    private void criarTabelaSeNaoExistir() {
        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
             Statement stmt = conexao.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS tarefas (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "descricao VARCHAR(255) NOT NULL," +
                    "concluida BOOLEAN NOT NULL)";

            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Tarefa> obterTodas() {
        List<Tarefa> tarefas = new ArrayList<>();
        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
             Statement stmt = conexao.createStatement()) {

            ResultSet resultado = stmt.executeQuery("SELECT * FROM tarefas");

            while (resultado.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(resultado.getInt("id"));
                tarefa.setDescricao(resultado.getString("descricao"));

                tarefas.add(tarefa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tarefas;
    }

    @Override
    public Tarefa obterPorId(int id) {
        // Implemente aqui a obtenção de uma tarefa por ID, se necessário
        return null;
    }

    @Override
    public void adicionar(Tarefa tarefa) {
        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement pstmt = conexao.prepareStatement("INSERT INTO tarefas (descricao, concluida) VALUES (?, ?)")) {

            pstmt.setString(1, tarefa.getDescricao());
            pstmt.setBoolean(2, tarefa.isConcluida());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void remover(int id) {
        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement pstmt = conexao.prepareStatement("DELETE FROM tarefas WHERE id = ?")) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
