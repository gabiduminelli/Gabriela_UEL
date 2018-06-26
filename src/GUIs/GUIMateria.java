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


public class GUIMateria extends JFrame {
    public static void main(String[] args) {
        new GUIMateria();
    }
    private Container cp;
    private JLabel labelAviso = new JLabel("Avisos");
    private JLabel labelTitulo = new JLabel("ID Materia: ");
    private JLabel lbIdMateria = new JLabel("ID Materia");
    private JTextField fdIdMateria = new JTextField(15);

    private JLabel lbNomeMateria = new JLabel("Nome Materia");
    private JTextField fdNomeMateria = new JTextField(45);


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

    DAOMateria controle = new DAOMateria();
    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    Boolean acao;
    Font fonte = new Font("Courier New", Font.BOLD, 20);
    Font fonteL = new Font("Courier New", Font.PLAIN, 14);

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    Materia materia = new Materia();

    public GUIMateria(){
        setSize(725, 340);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Cadastro de Materias");

        painelCentral.setLayout(new GridLayout(2, 2));
        painelCentral.add(lbNomeMateria);
        painelCentral.add(fdNomeMateria);

        fdNomeMateria.setEnabled(false);

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
        painelNorteCima.add(fdIdMateria);
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
        fdIdMateria.setFont(new Font("Courier New", Font.PLAIN, 20));
        lbIdMateria.setFont(new Font("Courier New", Font.BOLD, 17));
        lbNomeMateria.setFont(new Font("Courier New", Font.BOLD, 17));
        fdIdMateria.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdNomeMateria.setFont(new Font("Courier New", Font.PLAIN, 17));
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
                    materia = new Materia();
                    int idMateria = Integer.valueOf(fdIdMateria.getText());
                    materia.setIdMateria(Integer.valueOf(fdIdMateria.getText()));
                    materia = controle.obter(materia.getIdMateria());
                    labelAviso.setBackground(Color.green);
                    if (materia != null) {
                        fdIdMateria.setText(materia.getIdMateria() + "");
                        fdNomeMateria.setText(materia.getNomeMateria() + "");
                        btAtualizar.setVisible(true);
                        btRemover.setVisible(true);
                        btInserir.setVisible(false);
                        btListar.setVisible(false);
                        labelAviso.setText("Encontrado na lista!");
                        labelAviso.setBackground(Color.green);
                    } else {
                        fdNomeMateria.setEnabled(false);
                        fdNomeMateria.setText(null);
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
                fdIdMateria.setEnabled(false);
                fdNomeMateria.setEnabled(true);
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
                    materia = new Materia();
                        materia.setIdMateria(Integer.valueOf(fdIdMateria.getText()));
                        materia.setNomeMateria(fdNomeMateria.getText());
                        controle.inserir(materia);
                        labelAviso.setText("Registro inserido com sucesso!");
                        fdIdMateria.setEnabled(true);
                        fdIdMateria.requestFocus();
                        fdNomeMateria.setEnabled(false);
                        btSalvar.setVisible(false);
                        btCancelar.setVisible(false);
                        btBuscar.setVisible(true);
                        btListar.setVisible(true);
                    } catch (Exception erro) {
                        labelAviso.setText("Erro nos dados!");
                    }
                } else { //btAlterar
                    try {
                        materia = new Materia();
                        materia.setIdMateria(Integer.valueOf(fdIdMateria.getText()));
                        materia.setNomeMateria(fdNomeMateria.getText());
                        controle.atualizar(materia);
                        labelAviso.setText("Registro alterado com sucesso!");
                        fdIdMateria.setEnabled(true);
                        fdIdMateria.requestFocus();
                        fdNomeMateria.setEnabled(false);
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
                fdIdMateria.setEnabled(false);
                fdIdMateria.setText("");
                fdNomeMateria.setEnabled(false);
                fdNomeMateria.setText("");
                fdIdMateria.setEnabled(true);
                fdIdMateria.requestFocus();
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
                fdNomeMateria.setEnabled(true);
                fdNomeMateria.requestFocus();
                fdIdMateria.setEnabled(false);
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
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Confirma a exclusão do registro <Chave = " + materia.getIdMateria() + " >?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    controle.remover(materia);
                    labelAviso.setText("Removido!");
                    fdIdMateria.setText("");
                    fdNomeMateria.setText("");
                    fdNomeMateria.setEnabled(false);
                    btListar.setVisible(true);
                } else {
                    labelAviso.setText("Remoção cancelada!");
                    btAtualizar.setVisible(true);
                    btRemover.setVisible(true);
                }
            }
        }
        );
        //////////////////fk
        fdIdMateria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                Materia materia = new Materia();
                DAOMateria daoMateria = new DAOMateria();
                List<String> listaAuxiliar = daoMateria.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5,
                            fdIdMateria.getBounds().y + fdIdMateria.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                       String[] aux = selectedItem.split(";");
                        fdIdMateria.setText(selectedItem.split(";")[0]);
                        fdNomeMateria.setText(selectedItem.split(";")[1]);
                        

                        //preparar para salvar
                       materia = daoMateria.obter(Integer.valueOf(aux[0]));

                    } else {
                        fdIdMateria.requestFocus();
                        fdIdMateria.selectAll();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Não há nenhuma materia cadastrada.");
                }
            }
        });

        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MateriaGUIListagem(controle.listInOrderNomeStrings("idMateria"), cp);
            }
        }
        );

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
