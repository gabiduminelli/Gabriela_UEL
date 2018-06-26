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
import myUtil.JanelaPesquisar;


public class GUICurso extends JFrame {
    public static void main(String[] args) {
        new GUICurso();
    }
    private Container cp;
    private JLabel labelAviso = new JLabel("Avisos");
    private JLabel labelTitulo = new JLabel("Codigo Curso: ");
    private JLabel lbCodigoCurso = new JLabel("Codigo Curso");
    private JTextField fdCodigoCurso = new JTextField(10);

    private JLabel lbNomeCurso = new JLabel("Nome Curso");
    private JTextField fdNomeCurso = new JTextField(45);

    JLabel lbativo = new JLabel("Situacao");
    JPanel pnAtivo = new JPanel();
    JCheckBox checkativo = new JCheckBox("ativo");
    JCheckBox checknaoativo = new JCheckBox("nao ativo");

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

    DAOCurso controle = new DAOCurso();
    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    Boolean acao;
    Font fonte = new Font("Courier New", Font.BOLD, 20);
    Font fonteL = new Font("Courier New", Font.PLAIN, 14);

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    Curso curso = new Curso();

    public GUICurso(){
        setSize(725, 340);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Cadastro de Cursos");

        painelCentral.setLayout(new GridLayout(3, 2));
        painelCentral.add(lbNomeCurso);
        painelCentral.add(fdNomeCurso);

        fdNomeCurso.setEnabled(false);

        painelCentral.add(lbativo);
        pnAtivo.add(checkativo);
        checkativo.setEnabled(false);
        pnAtivo.add(checknaoativo);
        checknaoativo.setEnabled(false);
        painelCentral.add(pnAtivo);
        List<String> combo = new ArrayList<>();


cp.setBackground(Color.white);
        cp.add(painelNortes, BorderLayout.NORTH);
        cp.add(painelCentralFora, BorderLayout.CENTER);
        cp.add(painelSul, BorderLayout.SOUTH);

        painelCentralFora.add(labelBranco, BorderLayout.NORTH);
        painelCentralFora.add(painelCentral, BorderLayout.SOUTH);
        painelNortes.add(painelNorteCima);
        painelNortes.add(painelNorteBaixo);
        painelNorteCima.add(labelTitulo);
        painelNorteCima.add(fdCodigoCurso);
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
        pnAtivo.setBackground(Color.WHITE);
        checkativo.setBackground(Color.WHITE);
        checknaoativo.setBackground(Color.WHITE);

        labelTitulo.setFont(new Font("Courier New", Font.BOLD, 20));
        fdCodigoCurso.setFont(new Font("Courier New", Font.PLAIN, 20));
        lbCodigoCurso.setFont(new Font("Courier New", Font.BOLD, 17));
        lbNomeCurso.setFont(new Font("Courier New", Font.BOLD, 17));
        lbativo.setFont(new Font("Courier New", Font.BOLD, 17));
        fdCodigoCurso.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdNomeCurso.setFont(new Font("Courier New", Font.PLAIN, 17));
        checkativo.setFont(new Font("Courier New", Font.PLAIN, 17));
        checknaoativo.setFont(new Font("Courier New", Font.PLAIN, 17));
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
                    curso = new Curso();
                    int codigoCurso = Integer.valueOf(fdCodigoCurso.getText());
                    curso.setCodigoCurso(Integer.valueOf(fdCodigoCurso.getText()));
                    curso = controle.obter(curso.getCodigoCurso());
                    labelAviso.setBackground(Color.green);
                    if (curso != null) {
                        fdCodigoCurso.setText(curso.getCodigoCurso() + "");
                        fdNomeCurso.setText(curso.getNomeCurso() + "");
                        if (curso.getAtivo()) {
                            checkativo.setSelected(true);
                            checknaoativo.setSelected(false);
                        } else {
                            checknaoativo.setSelected(true);
                            checkativo.setSelected(false);
                        }
                        btAtualizar.setVisible(true);
                        btRemover.setVisible(true);
                        btInserir.setVisible(false);
                        btListar.setVisible(false);
                        labelAviso.setText("Encontrado na lista!");
                        labelAviso.setBackground(Color.green);
                    } else {
                        fdNomeCurso.setEnabled(false);
                        fdNomeCurso.setText(null);
                        checkativo.setEnabled(false);
                        checknaoativo.setEnabled(false);
                        checkativo.setSelected(false);
                        checknaoativo.setSelected(false);
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
                fdCodigoCurso.setEnabled(false);
                fdNomeCurso.setEnabled(true);
                checkativo.setEnabled(true);
                checknaoativo.setEnabled(true);
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
                    curso = new Curso();
                        curso.setCodigoCurso(Integer.valueOf(fdCodigoCurso.getText()));
                        curso.setNomeCurso(fdNomeCurso.getText());
                        Boolean respAtivo = null;
                        if (checkativo.isSelected()) {
                            respAtivo = true;
                        } else if (checknaoativo.isSelected()) {
                            respAtivo = false;
                        } else {
                            int a = 1 / 0;
                        }
                        curso.setAtivo(respAtivo);
                        controle.inserir(curso);
                        labelAviso.setText("Registro inserido com sucesso!");
                        fdCodigoCurso.setEnabled(true);
                        fdCodigoCurso.requestFocus();
                        fdNomeCurso.setEnabled(false);
                        checkativo.setEnabled(false);
                        checknaoativo.setEnabled(false);
                        btSalvar.setVisible(false);
                        btCancelar.setVisible(false);
                        btBuscar.setVisible(true);
                        btListar.setVisible(true);
                    } catch (Exception erro) {
                        labelAviso.setText("Erro nos dados!");
                    }
                } else { //btAlterar
                    try {
                        curso = new Curso();
                        curso.setCodigoCurso(Integer.valueOf(fdCodigoCurso.getText()));
                        curso.setNomeCurso(fdNomeCurso.getText());
                        Boolean respAtivo = null;
                        if (checkativo.isSelected()) {
                            respAtivo = true; 
                        } else {
                            respAtivo = false;
                        }
                        curso.setAtivo(respAtivo);
                        controle.atualizar(curso);
                        labelAviso.setText("Registro alterado com sucesso!");
                        fdCodigoCurso.setEnabled(true);
                        fdCodigoCurso.requestFocus();
                        fdNomeCurso.setEnabled(false);
                        checkativo.setEnabled(false);
                        checknaoativo.setEnabled(false);
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
                fdCodigoCurso.setEnabled(false);
                fdCodigoCurso.setText("");
                fdNomeCurso.setEnabled(false);
                fdNomeCurso.setText("");
                checkativo.setEnabled(false);
                checkativo.setSelected(false);
                checknaoativo.setEnabled(false);
                checknaoativo.setSelected(false);
                fdCodigoCurso.setEnabled(true);
                fdCodigoCurso.requestFocus();
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
                fdNomeCurso.setEnabled(true);
                checkativo.setEnabled(true);
                checknaoativo.setEnabled(true);
                fdNomeCurso.requestFocus();
                fdCodigoCurso.setEnabled(false);
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
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Confirma a exclusão do registro <Chave = " + curso.getCodigoCurso() + " >?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    controle.remover(curso);
                    labelAviso.setText("Removido!");
                    fdCodigoCurso.setText("");
                    fdNomeCurso.setText("");
                    fdNomeCurso.setEnabled(false);
                    checkativo.setEnabled(false);
                    checkativo.setSelected(false);
                    checknaoativo.setEnabled(false);
                    checknaoativo.setSelected(false);
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
                new CursoGUIListagem(controle.listInOrderNomeStrings("codigoCurso"), cp);
            }
        }
        );
         fdCodigoCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                Curso curso = new Curso();
                DAOCurso daoCurso = new DAOCurso();
                List<String> listaAuxiliar = daoCurso.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5,
                            fdCodigoCurso.getBounds().y + fdCodigoCurso.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                       String[] aux = selectedItem.split(";");
                        fdCodigoCurso.setText(selectedItem.split(";")[0]);
                        fdNomeCurso.setText(selectedItem.split(";")[1]);
                        if ((selectedItem.split(";")[2]).equals(true)) {
                            checkativo.setSelected(true);
                            checknaoativo.setSelected(false);
                        } else {
                            checknaoativo.setSelected(true);
                            checkativo.setSelected(false);
                        }

                        //preparar para salvar
                       curso = daoCurso.obter(Integer.valueOf(aux[0]));

                    } else {
                        fdCodigoCurso.requestFocus();
                        fdCodigoCurso.selectAll();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Não há nenhum produto cadastrado.");
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
