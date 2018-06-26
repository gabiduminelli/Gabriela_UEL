package GUIs;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.*;
import java.io.File;
import javax.swing.text.JTextComponent;
import Auxiliar.*;
import DAOs.*;
import Entidades.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import myUtil.JanelaPesquisar;

public class GUIAluno extends JFrame {

    public static void main(String[] args) {
        new GUIAluno();
    }
    private Container cp;
    private JLabel labelAviso = new JLabel("Avisos");
    private JLabel labelTitulo = new JLabel("RA Aluno: ");
    private JLabel lbRaAluno = new JLabel("RA Aluno");
    private JTextField fdRaAluno = new JTextField(10);

    private JSpinner spinnerdataIngresso = new JSpinner(new SpinnerDateModel());
    private final JSpinner.DateEditor spinnerEditordataIngresso = new JSpinner.DateEditor(spinnerdataIngresso, "dd/MM/yyyy");
    private JLabel lbDataIngresso = new JLabel("data ingresso");
    private JLabel lbPessoaCpf = new JLabel("CPF");
    private List<String> stringpessoaCpf = new ArrayList<>();
    private JComboBox comboPessoaCpf = new JComboBox();

    private JPanel painelNortes = new JPanel(new GridLayout(2, 1));
    private JPanel painelNorteCima = new JPanel();
    private JPanel painelNorteBaixo = new JPanel();
    private JPanel painelCentralFora = new JPanel(new BorderLayout());
    private JPanel painelCentral = new JPanel();
    private JPanel painelSul = new JPanel();
    private JLabel labelBranco = new JLabel();

    JButton btInserir = new JButton(new ImageIcon(getClass().getResource("/icones/add.png")));
    JButton btSalvar = new JButton(new ImageIcon(getClass().getResource("/icones/confirmar.png")));
    JButton btRemover = new JButton(new ImageIcon(getClass().getResource("/icones/deletar.png")));
    JButton btAtualizar = new JButton(new ImageIcon(getClass().getResource("/icones/att.png")));
    JButton btBuscar = new JButton(new ImageIcon(getClass().getResource("/icones/search.png")));
    JButton btCancelar = new JButton(new ImageIcon(getClass().getResource("/icones/cancelar.png")));
    JButton btListar = new JButton(new ImageIcon(getClass().getResource("/icones/listar.png")));

    DAOAluno controle = new DAOAluno();
    Pessoa pessoa = new Pessoa();

    public GUIAluno(Container cp, Boolean acao) throws HeadlessException {
        this.cp = cp;
        this.acao = acao;
    }
    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    Boolean acao;
    Font fonte = new Font("Courier New", Font.BOLD, 20);
    Font fonteL = new Font("Courier New", Font.PLAIN, 14);

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    JTextComponent editor = (JTextComponent) comboPessoaCpf.getEditor().getEditorComponent();

    Aluno aluno = new Aluno();

    public GUIAluno() {
        setSize(725, 340);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Cadastro de Alunos");

        painelCentral.setLayout(new GridLayout(3, 2));

        painelCentral.add(lbDataIngresso);
        painelCentral.add(spinnerdataIngresso);
        spinnerdataIngresso.setEditor(spinnerEditordataIngresso);
        spinnerdataIngresso.setEnabled(false);
        List<String> combo = new ArrayList<>();
        /////////////aula
        combo = new DAOPessoa().listInOrderNomeStrings("id");
        ////////////////
        for (int x = 0; x < combo.size(); x++) {
            /////////////
            String[] aux = combo.get(x).split(";");
            stringpessoaCpf.add(aux[0] + "-" + aux[1]);
            ////////////////
        }
        comboPessoaCpf = new JComboBox(stringpessoaCpf.toArray());
        painelCentral.add(lbPessoaCpf);
        painelCentral.add(comboPessoaCpf);
        comboPessoaCpf.setEnabled(false);
        editor.setDocument(new SearchableComboBox(comboPessoaCpf));

        cp.setBackground(Color.white);
        cp.add(painelNortes, BorderLayout.NORTH);
        cp.add(painelCentralFora, BorderLayout.CENTER);
        cp.add(painelSul, BorderLayout.SOUTH);

        painelCentralFora.add(labelBranco, BorderLayout.NORTH);
        painelCentralFora.add(painelCentral, BorderLayout.SOUTH);
        painelNortes.add(painelNorteCima);
        painelNortes.add(painelNorteBaixo);
        painelNorteCima.add(labelTitulo);
        painelNorteCima.add(fdRaAluno);
        painelNorteBaixo.add(btBuscar);
        painelNorteBaixo.add(btInserir);
        painelNorteBaixo.add(btAtualizar);
        painelNorteBaixo.add(btRemover);
        painelNorteBaixo.add(btSalvar);
        painelNorteBaixo.add(btCancelar);
        painelNorteBaixo.add(btListar);
        painelNorteCima.setBackground(Color.white);
        painelNorteBaixo.setBackground(Color.white);
        painelCentralFora.setBackground(Color.white);
        painelCentral.setBackground(Color.white);
        painelSul.setBackground(Color.white);
        btInserir.setBackground(Color.WHITE);
        btSalvar.setBackground(Color.WHITE);
        btRemover.setBackground(Color.WHITE);
        btAtualizar.setBackground(Color.WHITE);
        btBuscar.setBackground(Color.WHITE);
        btCancelar.setBackground(Color.WHITE);
        btListar.setBackground(Color.WHITE);

        labelTitulo.setFont(new Font("Courier New", Font.BOLD, 20));
        fdRaAluno.setFont(new Font("Courier New", Font.PLAIN, 20));
        lbRaAluno.setFont(new Font("Courier New", Font.BOLD, 17));
        lbDataIngresso.setFont(new Font("Courier New", Font.BOLD, 17));
        lbPessoaCpf.setFont(new Font("Courier New", Font.BOLD, 17));
        fdRaAluno.setFont(new Font("Courier New", Font.PLAIN, 17));
        spinnerdataIngresso.setFont(new Font("Courier New", Font.PLAIN, 17));
        comboPessoaCpf.setFont(new Font("Courier New", Font.PLAIN, 17));
        labelAviso.setFont(new Font("Courier New", Font.BOLD, 20));
        btInserir.setVisible(false);
        btAtualizar.setVisible(false);
        btRemover.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);

        painelSul.add(labelAviso);

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    aluno = new Aluno();
                    int raAluno = Integer.valueOf(fdRaAluno.getText());
                    aluno.setRaAluno(Integer.valueOf(fdRaAluno.getText()));
                    aluno = controle.obter(aluno.getRaAluno());
                    labelAviso.setBackground(Color.green);
                    if (aluno != null) {
                        fdRaAluno.setText(aluno.getRaAluno() + "");
                        spinnerdataIngresso.setValue(aluno.getDataIngresso());
//                        String cpf = String.valueOf(aluno.getPessoaCpf().getCpf()).split("")[0];
//                        Integer c = Integer.valueOf(cpf);
//                        System.out.println(cpf);
//
////                        comboPessoaCpf.setSelectedIndex();
                            comboPessoaCpf.setSelectedItem((String)(aluno.getPessoaCpf().getCpf()+
                                    "-"+aluno.getPessoaCpf().getNomePessoa()));
                        //comboPessoaCpf.setSelectedIndex(aluno.getPessoaCpf().getCpf());

//                        String cpf = String.valueOf(comboPessoaCpf.getSelectedItem()).split("-")[0];
//                        aluno.setPessoaCpf(new DAOPessoa().obter(Integer.valueOf(cpf)));
                        btAtualizar.setVisible(true);
                        btRemover.setVisible(true);
                        btInserir.setVisible(false);
                        btListar.setVisible(false);
                        labelAviso.setText("Encontrado na lista!");
                        labelAviso.setBackground(Color.green);
                    } else {
                        spinnerdataIngresso.setEnabled(false);
                        spinnerdataIngresso.setValue(new Date());
                        comboPessoaCpf.setEnabled(false);
                        comboPessoaCpf.setSelectedIndex(0);
                        labelAviso.setText("Não encontrado!");
                        labelAviso.setBackground(Color.red);
                        btInserir.setVisible(true);
                        btAtualizar.setVisible(false);
                        btRemover.setVisible(false);
                        btListar.setVisible(false);
                    }
                } catch (Exception erro) {
                    labelAviso.setText("Preencha os campos corretamente!");
                    labelAviso.setBackground(Color.red);
                }
            }
        }
        );

        btInserir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acao = true;
                fdRaAluno.setEnabled(false);
                spinnerdataIngresso.setEnabled(true);
                comboPessoaCpf.setEnabled(true);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btInserir.setVisible(false);
                btListar.setVisible(false);
            }
        }
        );

        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acao) { //btInserir
                    try {
                        aluno = new Aluno();
                        aluno.setRaAluno(Integer.valueOf(fdRaAluno.getText()));
                        aluno.setDataIngresso((Date) spinnerdataIngresso.getValue());
                        String cpf = String.valueOf(comboPessoaCpf.getSelectedItem()).split("-")[0];
                        aluno.setPessoaCpf(new DAOPessoa().obter(Integer.valueOf(cpf)));
                        controle.inserir(aluno);
                        labelAviso.setText("Registro inserido com sucesso!");
                        fdRaAluno.setEnabled(true);
                        fdRaAluno.requestFocus();
                        spinnerdataIngresso.setEnabled(false);
                        comboPessoaCpf.setEnabled(false);
                        btSalvar.setVisible(false);
                        btCancelar.setVisible(false);
                        btBuscar.setVisible(true);
                        btListar.setVisible(true);
                    } catch (Exception erro) {
                        labelAviso.setText("Erro nos dados!");
                    }
                } else { //btAlterar
                    try {
                        aluno = new Aluno();
                        aluno.setRaAluno(Integer.valueOf(fdRaAluno.getText()));
                        aluno.setDataIngresso((Date) spinnerdataIngresso.getValue());
                        String cpf = String.valueOf(comboPessoaCpf.getSelectedItem()).split("-")[0];
                        aluno.setPessoaCpf(new DAOPessoa().obter(Integer.valueOf(cpf)));
                        controle.atualizar(aluno);
                        labelAviso.setText("Registro alterado com sucesso!");
                        fdRaAluno.setEnabled(true);
                        fdRaAluno.requestFocus();
                        spinnerdataIngresso.setEnabled(false);
                        comboPessoaCpf.setEnabled(false);
                        btSalvar.setVisible(false);
                        btCancelar.setVisible(false);
                        btBuscar.setVisible(true);
                        btListar.setVisible(true);
                    } catch (Exception erro) {
                        labelAviso.setText("Erro nos dados!");
                    }
                }
            }
        }
        );

        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelAviso.setText("Cancelado!");
                fdRaAluno.setEnabled(false);
                fdRaAluno.setText("");
                spinnerdataIngresso.setEnabled(false);
                spinnerdataIngresso.setValue(new Date());
                comboPessoaCpf.setEnabled(false);
                comboPessoaCpf.setSelectedIndex(0);
                fdRaAluno.setEnabled(true);
                fdRaAluno.requestFocus();
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
            }
        }
        );

        btAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acao = false;
                spinnerdataIngresso.setEnabled(true);
                comboPessoaCpf.setEnabled(true);
                fdRaAluno.setEnabled(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btRemover.setVisible(false);
                btAtualizar.setVisible(false);
                btListar.setVisible(false);
            }
        }
        );

        btRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btRemover.setVisible(false);
                btListar.setVisible(false);
                btAtualizar.setVisible(false);
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Confirma a exclusão do registro <Chave = " + aluno.getRaAluno() + " >?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    controle.remover(aluno);
                    labelAviso.setText("Removido!");
                    fdRaAluno.setText("");
                    spinnerdataIngresso.setEnabled(false);
                    spinnerdataIngresso.setValue(new Date());
                    comboPessoaCpf.setEnabled(false);
                    comboPessoaCpf.setSelectedIndex(0);
                    btListar.setVisible(true);
                } else {
                    labelAviso.setText("Remoção cancelada!");
                    btAtualizar.setVisible(true);
                    btRemover.setVisible(true);
                }
            }
        }
        );

        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AlunoGUIListagem(controle.listInOrderNomeStrings("raAluno"), cp);
            }
        }
        );
        ////////////////fk
        fdRaAluno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                Aluno aluno1 = new Aluno();
                DAOAluno daoAluno = new DAOAluno();
                List<String> listaAuxiliar = daoAluno.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5,
                            fdRaAluno.getBounds().y + fdRaAluno.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split(";");
                        fdRaAluno.setText(selectedItem.split(";")[0]);
                        Date dt;
                        try {
                            dt = sdf.parse(selectedItem.split(";")[1]);
                            spinnerdataIngresso.setValue(dt);
                        } catch (ParseException ex) {
                            System.out.println("Erro data");
                        }

                        comboPessoaCpf.setSelectedItem(selectedItem.split(";")[2]);
                        //preparar para salvar
                        aluno = daoAluno.obter(Integer.valueOf(aux[0]));

                    } else {
                        fdRaAluno.requestFocus();
                        fdRaAluno.selectAll();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Não há nenhuma materia cadastrada.");
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        }
        );
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
