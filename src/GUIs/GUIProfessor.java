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
import myUtil.JanelaPesquisar;

public class GUIProfessor extends JFrame {
    public static void main(String[] args) {
        new GUIProfessor();
    }
    private Container cp;
    private JLabel labelAviso = new JLabel("Avisos");
    private JLabel labelTitulo = new JLabel("RaProfessor: ");
    private JLabel lbRaProfessor = new JLabel("RaProfessor");
    private JTextField fdRaProfessor = new JTextField(10);

    private JSpinner spinnerdataInicio = new JSpinner(new SpinnerDateModel());
    private final JSpinner.DateEditor spinnerEditordataInicio = new JSpinner.DateEditor(spinnerdataInicio, "dd/MM/yyyy");
    private JLabel lbDataInicio = new JLabel("Data Inicio");
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

    DAOProfessor controle = new DAOProfessor();
    
    Boolean acao;
    Font fonte = new Font("Courier New", Font.BOLD, 20);
    Font fonteL = new Font("Courier New", Font.PLAIN, 14);

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    JTextComponent editor = (JTextComponent) comboPessoaCpf.getEditor().getEditorComponent();

    Professor professor = new Professor();

    public GUIProfessor(){
        setSize(725, 340);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Cadastro de Professors");

        painelCentral.setLayout(new GridLayout(3, 2));


        painelCentral.add(lbDataInicio);
        painelCentral.add(spinnerdataInicio);
        spinnerdataInicio.setEditor(spinnerEditordataInicio);
        spinnerdataInicio.setEnabled(false);
        List<String> combo = new ArrayList<>();
        ///////////////
        combo = new DAOPessoa().listInOrderNomeStrings("cpf");
        /////////////
        for(int x = 0; x < combo.size(); x++) {
            /////////////////////
            String []aux = combo.get(x).split(";");
            stringpessoaCpf.add(aux[0]+"-"+aux[1]);
            //////////////////
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
        painelNorteCima.add(fdRaProfessor);
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
        fdRaProfessor.setFont(new Font("Courier New", Font.PLAIN, 20));
        lbRaProfessor.setFont(new Font("Courier New", Font.BOLD, 17));
        lbDataInicio.setFont(new Font("Courier New", Font.BOLD, 17));
        lbPessoaCpf.setFont(new Font("Courier New", Font.BOLD, 17));
        fdRaProfessor.setFont(new Font("Courier New", Font.PLAIN, 17));
        spinnerdataInicio.setFont(new Font("Courier New", Font.PLAIN, 17));
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
                try{
                    professor = new Professor();
                    int raProfessor = Integer.valueOf(fdRaProfessor.getText());
                    professor.setRaProfessor(Integer.valueOf(fdRaProfessor.getText()));
                    professor = controle.obter(professor.getRaProfessor());
                    labelAviso.setBackground(Color.green);
                    if (professor != null) {
                        fdRaProfessor.setText(professor.getRaProfessor() + "");
                        spinnerdataInicio.setValue(professor.getDataIngressoProfessor());
                        //////////////
                        
                        comboPessoaCpf.setSelectedItem((String)(professor.getPessoaCpf().getCpf()+
                                    "-"+professor.getPessoaCpf().getNomePessoa()));
                        btAtualizar.setVisible(true);
                        btRemover.setVisible(true);
                        btInserir.setVisible(false);
                        btListar.setVisible(false);
                        labelAviso.setText("Encontrado na lista!");
                        labelAviso.setBackground(Color.green);
                    } else {
                        spinnerdataInicio.setEnabled(false);
                        spinnerdataInicio.setValue(new Date());
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
                fdRaProfessor.setEnabled(false);
                spinnerdataInicio.setEnabled(true);
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
                if(acao){ //btInserir
                    try {
                    professor = new Professor();
                        professor.setRaProfessor(Integer.valueOf(fdRaProfessor.getText()));
                        professor.setDataIngressoProfessor((Date) spinnerdataInicio.getValue());
                        ////////////////////////
                        String cpf = String.valueOf(comboPessoaCpf.getSelectedItem()).split("-")[0];
                        professor.setPessoaCpf(new DAOPessoa().obter(Integer.valueOf(cpf)));
                      //////////////////////////////
                        controle.inserir(professor);
                        labelAviso.setText("Registro inserido com sucesso!");
                        fdRaProfessor.setEnabled(true);
                        fdRaProfessor.requestFocus();
                        spinnerdataInicio.setEnabled(false);
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
                        professor = new Professor();
                        professor.setRaProfessor(Integer.valueOf(fdRaProfessor.getText()));
                        professor.setDataIngressoProfessor((Date) spinnerdataInicio.getValue());
                        ////////////////
                        String cpf = String.valueOf(comboPessoaCpf.getSelectedItem()).split("-")[0];
                        professor.setPessoaCpf(new DAOPessoa().obter(Integer.valueOf(cpf)));
                        //////////////////
                        controle.atualizar(professor);
                        labelAviso.setText("Registro alterado com sucesso!");
                        fdRaProfessor.setEnabled(true);
                        fdRaProfessor.requestFocus();
                        spinnerdataInicio.setEnabled(false);
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
                fdRaProfessor.setEnabled(false);
                fdRaProfessor.setText("");
                spinnerdataInicio.setEnabled(false);
                spinnerdataInicio.setValue(new Date());
                comboPessoaCpf.setEnabled(false);
                comboPessoaCpf.setSelectedIndex(0);
                fdRaProfessor.setEnabled(true);
                fdRaProfessor.requestFocus();
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
                spinnerdataInicio.setEnabled(true);
                comboPessoaCpf.setEnabled(true);
                fdRaProfessor.setEnabled(false);
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
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Confirma a exclusão do registro <Chave = " + professor.getRaProfessor() + " >?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    controle.remover(professor);
                    labelAviso.setText("Removido!");
                    fdRaProfessor.setText("");
                    spinnerdataInicio.setEnabled(false);
                    spinnerdataInicio.setValue(new Date());
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
                new ProfessorGUIListagem(controle.listInOrderNomeStrings("raProfessor"), cp);
            }
        }
        );
        ////////////////fk
        fdRaProfessor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                Professor professor = new Professor();
                DAOProfessor daoProfessor = new DAOProfessor();
                List<String> listaAuxiliar = daoProfessor.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5,
                            fdRaProfessor.getBounds().y + fdRaProfessor.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split(";");
                        fdRaProfessor.setText(selectedItem.split(";")[0]);
                        Date dt;
                        try {
                            dt = sdf.parse(selectedItem.split(";")[1]);
                            spinnerdataInicio.setValue(dt);
                        } catch (ParseException ex) {
                            System.out.println("Erro data");
                        }

                        comboPessoaCpf.setSelectedItem(selectedItem.split(";")[2]);
                        //preparar para salvar
                        professor = daoProfessor.obter(Integer.valueOf(aux[0]));

                    } else {
                        fdRaProfessor.requestFocus();
                        fdRaProfessor.selectAll();
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
