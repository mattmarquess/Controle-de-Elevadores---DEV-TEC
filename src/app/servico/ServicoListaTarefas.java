package app.servico;

import java.util.List;
import app.Tarefa;
import app.repositorio.RepositorioTarefa;

public class ServicoListaTarefas {
    private RepositorioTarefa repositorioTarefa;

    public ServicoListaTarefas(RepositorioTarefa repositorioTarefa) {
        this.repositorioTarefa = repositorioTarefa;
    }

    public List<Tarefa> obterTodasTarefas() {
        return repositorioTarefa.obterTodas();
    }

    public void adicionarTarefa(Tarefa tarefa) {
        repositorioTarefa.adicionar(tarefa);
    }

    public void excluirTarefa(Tarefa tarefa) {
        repositorioTarefa.remover(tarefa.getId());
    }
}
