package app;

import javax.swing.*;
import app.repositorio.H2RepositorioTarefa;
import app.repositorio.RepositorioTarefa;
import app.servico.ServicoListaTarefas;
import app.ui.InterfaceListaTarefas;

public class AplicativoListaTarefas {
    public static void main(String[] args) {
        RepositorioTarefa repositorio = new H2RepositorioTarefa(); 
        ServicoListaTarefas servico = new ServicoListaTarefas(repositorio); 
        InterfaceListaTarefas interfaceListaTarefas = new InterfaceListaTarefas(servico); 

        SwingUtilities.invokeLater(() -> {
            JFrame frame = interfaceListaTarefas.getFrame();
            frame.pack();
            frame.setLocationRelativeTo(null); 
            frame.setVisible(true);
        });
    }
}
