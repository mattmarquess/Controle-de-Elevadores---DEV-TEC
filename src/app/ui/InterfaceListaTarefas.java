package app.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.Tarefa;
import app.servico.ServicoListaTarefas;

public class InterfaceListaTarefas {
    private ServicoListaTarefas servicoListaTarefas;

    // Componentes da interface
    private JFrame frame;
    private JTabbedPane tabbedPane; // Adicionando um JTabbedPane
    private JPanel panelListaTarefas; // Painel para a lista de tarefas
    private JList<Tarefa> listaTarefas;
    private DefaultListModel<Tarefa> modeloLista;
    private JTextField campoDescricao;
    private JButton botaoAdicionar;
    private JButton botaoExcluir; // Botão para excluir tarefa

    public InterfaceListaTarefas(ServicoListaTarefas servicoListaTarefas) {
        this.servicoListaTarefas = servicoListaTarefas;

        // Inicializar a interface
        inicializarUI();

        // Carregar dados iniciais
        carregarTarefas();
    }

    private void inicializarUI() {
        frame = new JFrame("Lista de Tarefas");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tabbedPane = new JTabbedPane();
        panelListaTarefas = new JPanel(new BorderLayout());

        modeloLista = new DefaultListModel<>();
        listaTarefas = new JList<>(modeloLista);
        listaTarefas.setBackground(Color.WHITE);
        listaTarefas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaTarefas.setCellRenderer(new TarefaCellRenderer());
        JScrollPane scrollPane = new JScrollPane(listaTarefas);

        campoDescricao = new JTextField(20);
        campoDescricao.setPreferredSize(new Dimension(200, 30));

        botaoAdicionar = new JButton("Adicionar");
        botaoAdicionar.setBackground(Color.GREEN);
        botaoAdicionar.setForeground(Color.WHITE);

        botaoExcluir = new JButton("Excluir");
        botaoExcluir.setBackground(Color.RED);
        botaoExcluir.setForeground(Color.WHITE);

        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarTarefa();
            }
        });

        botaoExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirTarefa();
            }
        });

        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotoes.setBackground(Color.LIGHT_GRAY);
        panelBotoes.add(campoDescricao);
        panelBotoes.add(botaoAdicionar);
        panelBotoes.add(botaoExcluir);

        panelListaTarefas.add(scrollPane, BorderLayout.CENTER);
        panelListaTarefas.add(panelBotoes, BorderLayout.SOUTH);

        tabbedPane.addTab("Lista de Tarefas", panelListaTarefas); // Adicionando a aba com a lista de tarefas
        tabbedPane.setSelectedIndex(0); // Selecionando a primeira aba ao iniciar

        frame.getContentPane().add(tabbedPane);
    }

    private void carregarTarefas() {
        modeloLista.clear();
        for (Tarefa tarefa : servicoListaTarefas.obterTodasTarefas()) {
            modeloLista.addElement(tarefa);
        }
    }

    private void adicionarTarefa() {
        String descricao = campoDescricao.getText();
        if (!descricao.isEmpty()) {
            Tarefa novaTarefa = new Tarefa();
            novaTarefa.setDescricao(descricao);
            servicoListaTarefas.adicionarTarefa(novaTarefa);
            campoDescricao.setText("");
            carregarTarefas();
        }
    }

    private void excluirTarefa() {
        Tarefa tarefaSelecionada = listaTarefas.getSelectedValue();
        if (tarefaSelecionada != null) {
            servicoListaTarefas.excluirTarefa(tarefaSelecionada);
            carregarTarefas();
        }
    }

    private class TarefaCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (isSelected) {
                label.setBackground(Color.LIGHT_GRAY);
            } else {
                label.setBackground(Color.WHITE);
            }
            return label;
        }
    }

    public JFrame getFrame() {
        return frame;
    }
}
