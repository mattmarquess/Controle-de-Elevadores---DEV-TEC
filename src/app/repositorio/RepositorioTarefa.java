package app.repositorio;

import java.util.List;
import app.Tarefa;

public interface RepositorioTarefa {
    List<Tarefa> obterTodas();

    Tarefa obterPorId(int id);

    void adicionar(Tarefa tarefa);

    void remover(int id);
}
