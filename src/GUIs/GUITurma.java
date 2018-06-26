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

public class GUITurma extends JFrame {
    public static void main(String[] args) {
        new GUITurma();
    }
    private Container cp;
    private JLabel labelAviso = new JLabel("Avisos");
    private JLabel labelTitulo = new JLabel("ID Turma: ");
    private JLabel lbIdTurma = new JLabel("ID Turma");
    private JTextField fdIdTurma = new JTextField(15);

    private JLabel lbCursoCodigoCurso = new JLabel("Codigo Curso");
    private List<String> stringcursoCodigoCurso = new ArrayList<>();
    private JComboBox comboCursoCodigoCurso = new JComboBox();
    private JLabel lbPeriodoIdPeriodo = new JLabel("ID Periodo");
    private List<String> stringperiodoIdPeriodo = new ArrayList<>();
    private JComboBox comboPeriodoIdPeriodo = new JComboBox();
    private JLabel lbMateriaIdMateria = new JLabel("ID Materia ");
    private List<String> stringmateriaIdMateria = new ArrayList<>();
    private JComboBox comboMateriaIdMateria = new JComboBox();
    private JLabel lbMaxAlunos = new JLabel("Maximo Alunos");
    private JTextField fdMaxAlunos = new JTextField(15);


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

    DAOTurma controle = new DAOTurma();
    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    Boolean acao;
    Font fonte = new Font("Courier New", Font.BOLD, 20);
    Font fonteL = new Font("Courier New", Font.PLAIN, 14);

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    JTextComponent editor = (JTextComponent) comboCursoCodigoCurso.getEditor().getEditorComponent();

    Turma turma = new Turma();

    public GUITurma(){
        setSize(725, 420);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Cadastro de Turmas");

        painelCentral.setLayout(new GridLayout(5, 2));
        painelCentral.add(lbMaxAlunos);
        painelCentral.add(fdMaxAlunos);

        fdMaxAlunos.setEnabled(false);

        List<String> combo = new ArrayList<>();
        combo = new DAOCurso().listInOrderNomeStrings("codigoCurso");
        for(int x = 0; x < combo.size(); x++) {
            String []aux = combo.get(x).split(";");
            stringcursoCodigoCurso.add(aux[0]+"-"+aux[1]);
        }
        comboCursoCodigoCurso = new JComboBox(stringcursoCodigoCurso.toArray());
        painelCentral.add(lbCursoCodigoCurso);
        painelCentral.add(comboCursoCodigoCurso);
        comboCursoCodigoCurso.setEnabled(false);
        editor.setDocument(new SearchableComboBox(comboCursoCodigoCurso));
        combo = new DAOPeriodo().listInOrderNomeStrings("idPeriodo");
        for(int x = 0; x < combo.size(); x++) {
            String []aux = combo.get(x).split(";");
            stringperiodoIdPeriodo.add(aux[0]+"-"+aux[1]);
        }
        comboPeriodoIdPeriodo = new JComboBox(stringperiodoIdPeriodo.toArray());
        painelCentral.add(lbPeriodoIdPeriodo);
        painelCentral.add(comboPeriodoIdPeriodo);
        comboPeriodoIdPeriodo.setEnabled(false);
        editor.setDocument(new SearchableComboBox(comboPeriodoIdPeriodo));
        combo = new DAOMateria().listInOrderNomeStrings("idMateria");
        for(int x = 0; x < combo.size(); x++) {
            String []aux = combo.get(x).split(";");
            stringmateriaIdMateria.add(aux[0]+"-"+aux[1]);
        }
        comboMateriaIdMateria = new JComboBox(stringmateriaIdMateria.toArray());
        painelCentral.add(lbMateriaIdMateria);
        painelCentral.add(comboMateriaIdMateria);
        comboMateriaIdMateria.setEnabled(false);
        editor.setDocument(new SearchableComboBox(comboMateriaIdMateria));


cp.setBackground(Color.white);
        cp.add(painelNortes, BorderLayout.NORTH);
        cp.add(painelCentralFora, BorderLayout.CENTER);
        cp.add(painelSul, BorderLayout.SOUTH);

        painelCentralFora.add(labelBranco, BorderLayout.NORTH);
        painelCentralFora.add(painelCentral, BorderLayout.SOUTH);
        painelNortes.add(painelNorteCima);
        painelNortes.add(painelNorteBaixo);
        painelNorteCima.add(labelTitulo);
        painelNorteCima.add(fdIdTurma);
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
        fdIdTurma.setFont(new Font("Courier New", Font.PLAIN, 20));
        lbIdTurma.setFont(new Font("Courier New", Font.BOLD, 17));
        lbCursoCodigoCurso.setFont(new Font("Courier New", Font.BOLD, 17));
        lbPeriodoIdPeriodo.setFont(new Font("Courier New", Font.BOLD, 17));
        lbMateriaIdMateria.setFont(new Font("Courier New", Font.BOLD, 17));
        lbMaxAlunos.setFont(new Font("Courier New", Font.BOLD, 17));
        fdIdTurma.setFont(new Font("Courier New", Font.PLAIN, 17));
        comboCursoCodigoCurso.setFont(new Font("Courier New", Font.PLAIN, 17));
        comboPeriodoIdPeriodo.setFont(new Font("Courier New", Font.PLAIN, 17));
        comboMateriaIdMateria.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdMaxAlunos.setFont(new Font("Courier New", Font.PLAIN, 17));
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
                    turma = new Turma();
                    int idTurma = Integer.valueOf(fdIdTurma.getText());
                    turma.setIdTurma(Integer.valueOf(fdIdTurma.getText()));
                    turma = controle.obter(turma.getIdTurma());
                    labelAviso.setBackground(Color.green);
                    if (turma != null) {
                        fdIdTurma.setText(turma.getIdTurma() + "");
                        comboCursoCodigoCurso.setSelectedItem((String)(turma.getCursoCodigoCurso().getCodigoCurso()+
                                "-"+turma.getCursoCodigoCurso().getNomeCurso()));
                        comboPeriodoIdPeriodo.setSelectedItem((String)(turma.getPeriodoIdPeriodo().getIdPeriodo()+
                                "-"+turma.getPeriodoIdPeriodo().getDataInicio()+"-"+turma.getPeriodoIdPeriodo().getDataFinal()));
                        comboMateriaIdMateria.setSelectedItem((String)(turma.getMateriaIdMateria().getIdMateria()+
                                "-"+turma.getMateriaIdMateria().getNomeMateria()));
                        fdMaxAlunos.setText(turma.getMaxAlunos() + "");
                        btAtualizar.setVisible(true);
                        btRemover.setVisible(true);
                        btInserir.setVisible(false);
                        btListar.setVisible(false);
                        labelAviso.setText("Encontrado na lista!");
                        labelAviso.setBackground(Color.green);
                    } else {
                        fdMaxAlunos.setEnabled(false);
                        fdMaxAlunos.setText(null);
                        comboCursoCodigoCurso.setEnabled(false);
                        comboCursoCodigoCurso.setSelectedIndex(0);
                        comboPeriodoIdPeriodo.setEnabled(false);
                        comboPeriodoIdPeriodo.setSelectedIndex(0);
                        comboMateriaIdMateria.setEnabled(false);
                        comboMateriaIdMateria.setSelectedIndex(0);
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
                fdIdTurma.setEnabled(false);
                comboCursoCodigoCurso.setEnabled(true);
                comboPeriodoIdPeriodo.setEnabled(true);
                comboMateriaIdMateria.setEnabled(true);
                fdMaxAlunos.setEnabled(true);
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
                    turma = new Turma();
                        turma.setIdTurma(Integer.valueOf(fdIdTurma.getText()));
                        String codigo= String.valueOf(comboCursoCodigoCurso.getSelectedItem()).split("-")[0];
                        String idPeriodo = String.valueOf(comboPeriodoIdPeriodo.getSelectedItem()).split("-")[0];
                        String idMateria = String.valueOf(comboMateriaIdMateria.getSelectedItem()).split("-")[0];
                        turma.setCursoCodigoCurso(new DAOCurso().obter(Integer.valueOf(codigo)));
                        turma.setPeriodoIdPeriodo(new DAOPeriodo().obter(Integer.valueOf(idPeriodo)));
                        turma.setMateriaIdMateria(new DAOMateria().obter(Integer.valueOf(idMateria)));
                        turma.setMaxAlunos(Integer.valueOf(fdMaxAlunos.getText()));
                        controle.inserir(turma);
                        labelAviso.setText("Registro inserido com sucesso!");
                        fdIdTurma.setEnabled(true);
                        fdIdTurma.requestFocus();
                        comboCursoCodigoCurso.setEnabled(false);
                        comboPeriodoIdPeriodo.setEnabled(false);
                        comboMateriaIdMateria.setEnabled(false);
                        fdMaxAlunos.setEnabled(false);
                        btSalvar.setVisible(false);
                        btCancelar.setVisible(false);
                        btBuscar.setVisible(true);
                        btListar.setVisible(true);
                    } catch (Exception erro) {
                        labelAviso.setText("Erro nos dados!");
                    }
                } else { //btAlterar
                    try {
                        turma = new Turma();
                        turma.setIdTurma(Integer.valueOf(fdIdTurma.getText()));
                        String codigo= String.valueOf(comboCursoCodigoCurso.getSelectedItem()).split("-")[0];
                        String idPeriodo = String.valueOf(comboPeriodoIdPeriodo.getSelectedItem()).split("-")[0];
                        String idMateria = String.valueOf(comboMateriaIdMateria.getSelectedItem()).split("-")[0];
                        turma.setCursoCodigoCurso(new DAOCurso().obter(Integer.valueOf(codigo)));
                        turma.setPeriodoIdPeriodo(new DAOPeriodo().obter(Integer.valueOf(idPeriodo)));
                        turma.setMateriaIdMateria(new DAOMateria().obter(Integer.valueOf(idMateria)));
                        controle.atualizar(turma);
                        labelAviso.setText("Registro alterado com sucesso!");
                        fdIdTurma.setEnabled(true);
                        fdIdTurma.requestFocus();
                        comboCursoCodigoCurso.setEnabled(false);
                        comboPeriodoIdPeriodo.setEnabled(false);
                        comboMateriaIdMateria.setEnabled(false);
                        fdMaxAlunos.setEnabled(false);
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
                fdIdTurma.setEnabled(false);
                fdIdTurma.setText("");
                comboCursoCodigoCurso.setEnabled(false);
                comboCursoCodigoCurso.setSelectedIndex(0);
                comboPeriodoIdPeriodo.setEnabled(false);
                comboPeriodoIdPeriodo.setSelectedIndex(0);
                comboMateriaIdMateria.setEnabled(false);
                comboMateriaIdMateria.setSelectedIndex(0);
                fdMaxAlunos.setEnabled(false);
                fdMaxAlunos.setText("");
                fdIdTurma.setEnabled(true);
                fdIdTurma.requestFocus();
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
                comboCursoCodigoCurso.setEnabled(true);
                comboPeriodoIdPeriodo.setEnabled(true);
                comboMateriaIdMateria.setEnabled(true);
                fdMaxAlunos.setEnabled(true);
                fdMaxAlunos.requestFocus();
                fdIdTurma.setEnabled(false);
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
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Confirma a exclusão do registro <Chave = " + turma.getIdTurma() + " >?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    controle.remover(turma);
                    labelAviso.setText("Removido!");
                    fdIdTurma.setText("");
                comboCursoCodigoCurso.setEnabled(false);
                comboCursoCodigoCurso.setSelectedIndex(0);
                comboPeriodoIdPeriodo.setEnabled(false);
                comboPeriodoIdPeriodo.setSelectedIndex(0);
                comboMateriaIdMateria.setEnabled(false);
                comboMateriaIdMateria.setSelectedIndex(0);
                    fdMaxAlunos.setText("");
                    fdMaxAlunos.setEnabled(false);
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
                new TurmaGUIListagem(controle.listInOrderNomeStrings("idTurma"), cp);
            }
        }
        );
        ////////////////fk
        fdIdTurma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                DAOTurma daoTurma = new DAOTurma();
                Turma turma = new Turma();
                List<String> listaAuxiliar = daoTurma.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5,
                            fdIdTurma.getBounds().y + fdIdTurma.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split(";");
                        fdIdTurma.setText(selectedItem.split(";")[0]);
                        fdMaxAlunos.setText(selectedItem.split(";")[4]);
                        comboCursoCodigoCurso.setSelectedItem(selectedItem.split(";")[1]);
                        comboPeriodoIdPeriodo.setSelectedItem(selectedItem.split(";")[2]);
                        comboMateriaIdMateria.setSelectedItem(selectedItem.split(";")[3]);
                        
                        
                        

                       
                        //preparar para salvar
                        turma = daoTurma.obter(Integer.valueOf(aux[0]));

                    } else {
                        fdIdTurma.requestFocus();
                        fdIdTurma.selectAll();
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
