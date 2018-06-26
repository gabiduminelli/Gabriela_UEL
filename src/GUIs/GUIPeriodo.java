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


public class GUIPeriodo extends JFrame {
    public static void main(String[] args) {
        new GUIPeriodo();
    }
    private Container cp;
    private JLabel labelAviso = new JLabel("Avisos");
    private JLabel labelTitulo = new JLabel("ID Periodo: ");
    private JLabel lbIdPeriodo = new JLabel("ID Periodo");
    private JTextField fdIdPeriodo = new JTextField(15);

    private JSpinner spinnerdataInicio = new JSpinner(new SpinnerDateModel());
    private final JSpinner.DateEditor spinnerEditordataInicio = new JSpinner.DateEditor(spinnerdataInicio, "dd/MM/yyyy");
    private JLabel lbDataInicio = new JLabel("Data Inicio");
    private JSpinner spinnerdataFinal = new JSpinner(new SpinnerDateModel());
    private final JSpinner.DateEditor spinnerEditordataFinal = new JSpinner.DateEditor(spinnerdataFinal, "dd/MM/yyyy");
    private JLabel lbDataFinal = new JLabel("Data Final");

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

    DAOPeriodo controle = new DAOPeriodo();
    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    Boolean acao;
    Font fonte = new Font("Courier New", Font.BOLD, 20);
    Font fonteL = new Font("Courier New", Font.PLAIN, 14);

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    Periodo periodo = new Periodo();

    public GUIPeriodo(){
        setSize(725, 340);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Cadastro de Periodos");

        painelCentral.setLayout(new GridLayout(3, 2));


        painelCentral.add(lbDataInicio);
        painelCentral.add(spinnerdataInicio);
        spinnerdataInicio.setEditor(spinnerEditordataInicio);
        spinnerdataInicio.setEnabled(false);
        painelCentral.add(lbDataFinal);
        painelCentral.add(spinnerdataFinal);
        spinnerdataFinal.setEditor(spinnerEditordataFinal);
        spinnerdataFinal.setEnabled(false);
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
        painelNorteCima.add(fdIdPeriodo);
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
        fdIdPeriodo.setFont(new Font("Courier New", Font.PLAIN, 20));
        lbIdPeriodo.setFont(new Font("Courier New", Font.BOLD, 17));
        lbDataInicio.setFont(new Font("Courier New", Font.BOLD, 17));
        lbDataFinal.setFont(new Font("Courier New", Font.BOLD, 17));
        fdIdPeriodo.setFont(new Font("Courier New", Font.PLAIN, 17));
        spinnerdataInicio.setFont(new Font("Courier New", Font.PLAIN, 17));
        spinnerdataFinal.setFont(new Font("Courier New", Font.PLAIN, 17));
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
                    periodo = new Periodo();
                    int idPeriodo = Integer.valueOf(fdIdPeriodo.getText());
                    periodo.setIdPeriodo(Integer.valueOf(fdIdPeriodo.getText()));
                    periodo = controle.obter(periodo.getIdPeriodo());
                    labelAviso.setBackground(Color.green);
                    if (periodo != null) {
                        fdIdPeriodo.setText(periodo.getIdPeriodo() + "");
                        spinnerdataInicio.setValue(periodo.getDataInicio());
                        spinnerdataFinal.setValue(periodo.getDataFinal());
                        btAtualizar.setVisible(true);
                        btRemover.setVisible(true);
                        btInserir.setVisible(false);
                        btListar.setVisible(false);
                        labelAviso.setText("Encontrado na lista!");
                        labelAviso.setBackground(Color.green);
                    } else {
                        spinnerdataInicio.setEnabled(false);
                        spinnerdataInicio.setValue(new Date());
                        spinnerdataFinal.setEnabled(false);
                        spinnerdataFinal.setValue(new Date());
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
                fdIdPeriodo.setEnabled(false);
                spinnerdataInicio.setEnabled(true);
                spinnerdataFinal.setEnabled(true);
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
                    periodo = new Periodo();
                        periodo.setIdPeriodo(Integer.valueOf(fdIdPeriodo.getText()));
                        periodo.setDataInicio((Date) spinnerdataInicio.getValue());
                        periodo.setDataFinal((Date) spinnerdataFinal.getValue());
                        controle.inserir(periodo);
                        labelAviso.setText("Registro inserido com sucesso!");
                        fdIdPeriodo.setEnabled(true);
                        fdIdPeriodo.requestFocus();
                        spinnerdataInicio.setEnabled(false);
                        spinnerdataFinal.setEnabled(false);
                        btSalvar.setVisible(false);
                        btCancelar.setVisible(false);
                        btBuscar.setVisible(true);
                        btListar.setVisible(true);
                    } catch (Exception erro) {
                        labelAviso.setText("Erro nos dados!");
                    }
                } else { //btAlterar
                    try {
                        periodo = new Periodo();
                        periodo.setIdPeriodo(Integer.valueOf(fdIdPeriodo.getText()));
                        periodo.setDataInicio((Date) spinnerdataInicio.getValue());
                        periodo.setDataFinal((Date) spinnerdataFinal.getValue());
                        controle.atualizar(periodo);
                        labelAviso.setText("Registro alterado com sucesso!");
                        fdIdPeriodo.setEnabled(true);
                        fdIdPeriodo.requestFocus();
                        spinnerdataInicio.setEnabled(false);
                        spinnerdataFinal.setEnabled(false);
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
                fdIdPeriodo.setEnabled(false);
                fdIdPeriodo.setText("");
                spinnerdataInicio.setEnabled(false);
                spinnerdataInicio.setValue(new Date());
                spinnerdataFinal.setEnabled(false);
                spinnerdataFinal.setValue(new Date());
                fdIdPeriodo.setEnabled(true);
                fdIdPeriodo.requestFocus();
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
                spinnerdataFinal.setEnabled(true);
                fdIdPeriodo.setEnabled(false);
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
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Confirma a exclusão do registro <Chave = " + periodo.getIdPeriodo() + " >?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    controle.remover(periodo);
                    labelAviso.setText("Removido!");
                    fdIdPeriodo.setText("");
                    spinnerdataInicio.setEnabled(false);
                    spinnerdataInicio.setValue(new Date());
                    spinnerdataFinal.setEnabled(false);
                    spinnerdataFinal.setValue(new Date());
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
                new PeriodoGUIListagem(controle.listInOrderNomeStrings("idPeriodo"), cp);
            }
        }
        );
         ////////////////fk
        fdIdPeriodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                Periodo periodo = new Periodo();
                DAOPeriodo daoPeriodo = new DAOPeriodo();
                List<String> listaAuxiliar = daoPeriodo.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5,
                            fdIdPeriodo.getBounds().y + fdIdPeriodo.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split(";");
                        fdIdPeriodo.setText(selectedItem.split(";")[0]);
                        Date dti;
                        Date dtf;
                        try {
                            dti = sdf.parse(selectedItem.split(";")[1]);
                            spinnerdataInicio.setValue(dti);
                        } catch (ParseException ex) {
                            System.out.println("Erro data");
                        }
                        try {
                            dtf = sdf.parse(selectedItem.split(";")[2]);
                            spinnerdataFinal.setValue(dtf);
                        } catch (ParseException ex) {
                            System.out.println("Erro data");
                        }

                        
                        //preparar para salvar
                        periodo = daoPeriodo.obter(Integer.valueOf(aux[0]));

                    } else {
                        fdIdPeriodo.requestFocus();
                        fdIdPeriodo.selectAll();
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
