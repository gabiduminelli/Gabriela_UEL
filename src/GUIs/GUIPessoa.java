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

public class GUIPessoa extends JFrame {
    public static void main(String[] args) {
        new GUIPessoa();
    }
    private Container cp;
    private JLabel labelAviso = new JLabel("Avisos");
    private JLabel labelTitulo = new JLabel("Cpf: ");
    private JLabel lbCpf = new JLabel("Cpf");
    private JTextField fdCpf = new JTextField(15);

    private JLabel lbNomePessoa = new JLabel("Nome");
    private JTextField fdNomePessoa = new JTextField(45);

    private JLabel lbIdadePessoa = new JLabel("Idade");
    private JTextField fdIdadePessoa = new JTextField(3);

    private JLabel lbEmail = new JLabel("email");
    private JTextField fdEmail = new JTextField(45);

    JPanel painelImagem = new JPanel(new GridLayout(1, 1));
    Image img;
    Image imagemAux;
    String origem;
    String destino = "src/fotos/";
    String semImagem = "src/fotos/0.png";
    String escolherImagem = "src/fotos/0a.png";
    JLabel labelFoto = new JLabel("");
    Boolean uploadFoto = false;

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

    DAOPessoa controle = new DAOPessoa();
    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    Boolean acao;
    Font fonte = new Font("Courier New", Font.BOLD, 20);
    Font fonteL = new Font("Courier New", Font.PLAIN, 14);

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    Pessoa pessoa = new Pessoa();

    public GUIPessoa(){
        setSize(725, 380);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Cadastro de Pessoas");

        try {
            origem = "/fotos/0.png";
            ImageIcon icone = new ImageIcon(getClass().getResource(origem));
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
            labelFoto.setIcon(icone);

        } catch (Exception erro) {
            System.out.println("erro ao carregar a imagem");
        }

        painelCentral.setLayout(new GridLayout(4, 2));
        painelCentral.add(lbNomePessoa);
        painelCentral.add(fdNomePessoa);
        painelCentral.add(lbIdadePessoa);
        painelCentral.add(fdIdadePessoa);
        painelCentral.add(lbEmail);
        painelCentral.add(fdEmail);

        fdNomePessoa.setEnabled(false);
        fdIdadePessoa.setEnabled(false);
        fdEmail.setEnabled(false);

        List<String> combo = new ArrayList<>();


cp.setBackground(Color.white);
        painelImagem.setBackground(Color.white);
        painelImagem.add(labelFoto);
        cp.add(painelNortes, BorderLayout.NORTH);
        cp.add(painelCentralFora, BorderLayout.WEST);
        cp.add(painelImagem, BorderLayout.EAST);
        cp.add(painelSul, BorderLayout.SOUTH);

        painelCentralFora.add(labelBranco, BorderLayout.NORTH);
        painelCentralFora.add(painelCentral, BorderLayout.SOUTH);
        painelNortes.add(painelNorteCima);
        painelNortes.add(painelNorteBaixo);
        painelNorteCima.add(labelTitulo);
        painelNorteCima.add(fdCpf);
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
        fdCpf.setFont(new Font("Courier New", Font.PLAIN, 20));
        lbCpf.setFont(new Font("Courier New", Font.BOLD, 17));
        lbNomePessoa.setFont(new Font("Courier New", Font.BOLD, 17));
        lbIdadePessoa.setFont(new Font("Courier New", Font.BOLD, 17));
        lbEmail.setFont(new Font("Courier New", Font.BOLD, 17));
        fdCpf.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdNomePessoa.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdIdadePessoa.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdEmail.setFont(new Font("Courier New", Font.PLAIN, 17));
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
            uploadFoto = false;
                try{
                    pessoa = new Pessoa();
                    int cpf = Integer.valueOf(fdCpf.getText());
                    pessoa.setCpf(Integer.valueOf(fdCpf.getText()));
                    pessoa = controle.obter(pessoa.getCpf());
                    labelAviso.setBackground(Color.green);
                    if (pessoa != null) {
                        fdCpf.setText(pessoa.getCpf() + "");
                        fdNomePessoa.setText(pessoa.getNomePessoa() + "");
                        fdIdadePessoa.setText(pessoa.getIdadePessoa() + "");
                        fdEmail.setText(pessoa.getEmail() + "");
                        btAtualizar.setVisible(true);
                        btRemover.setVisible(true);
                        btInserir.setVisible(false);
                        btListar.setVisible(false);
                        labelAviso.setText("Encontrado na lista!");
                        labelAviso.setBackground(Color.green);
                        try {
                            String aux = String.valueOf(pessoa.getCpf()).trim();
                            origem = "/fotos/" + aux + ".png";
                            ImageIcon icone = new ImageIcon(getClass().getResource(origem));
                            imagemAux = icone.getImage();
                            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));

                            labelFoto.setIcon(icone);

                        } catch (Exception erro) {
                            origem = "/fotos/0.png";
                            ImageIcon icone = new ImageIcon(getClass().getResource(origem));
                            imagemAux = icone.getImage();
                            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
                            labelFoto.setIcon(icone);
                        }
                    } else {
        try {
            origem = "/fotos/0.png";
            ImageIcon icone = new ImageIcon(getClass().getResource(origem));
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
            labelFoto.setIcon(icone);

        } catch (Exception erro) {
            System.out.println("erro ao carregar a imagem");
        }
                        fdNomePessoa.setEnabled(false);
                        fdNomePessoa.setText(null);
                        fdIdadePessoa.setEnabled(false);
                        fdIdadePessoa.setText(null);
                        fdEmail.setEnabled(false);
                        fdEmail.setText(null);
                        labelAviso.setText("Não encontrado!");
                        labelAviso.setBackground(Color.red);
                        btInserir.setVisible(true);
                        btAtualizar.setVisible(false);
                        btRemover.setVisible(false);
                        btListar.setVisible(false);
        try {
            origem = "/fotos/0.png";
            ImageIcon icone = new ImageIcon(getClass().getResource(origem));
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
            labelFoto.setIcon(icone);

        } catch (Exception erro) {
            System.out.println("erro ao carregar a imagem");
        }
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
        try {
            origem = "/fotos/0a.png";
            ImageIcon icone = new ImageIcon(getClass().getResource(origem));
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
            labelFoto.setIcon(icone);

        } catch (Exception erro) {
            System.out.println("erro ao carregar a imagem");
        }
                fdCpf.setEnabled(false);
                fdNomePessoa.setEnabled(true);
                fdIdadePessoa.setEnabled(true);
                fdEmail.setEnabled(true);
            uploadFoto = true;
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
            uploadFoto = false;
                if(acao){ //btInserir
                    try {
                    pessoa = new Pessoa();
                        pessoa.setCpf(Integer.valueOf(fdCpf.getText()));
                        pessoa.setNomePessoa(fdNomePessoa.getText());
                        pessoa.setIdadePessoa(Integer.valueOf(fdIdadePessoa.getText()));
                        pessoa.setEmail(fdEmail.getText());
                        controle.inserir(pessoa);
                        labelAviso.setText("Registro inserido com sucesso!");
                        fdCpf.setEnabled(true);
                        fdCpf.requestFocus();
                        fdNomePessoa.setEnabled(false);
                        fdIdadePessoa.setEnabled(false);
                        fdEmail.setEnabled(false);
                        btSalvar.setVisible(false);
                        btCancelar.setVisible(false);
                        btBuscar.setVisible(true);
                        btListar.setVisible(true);
                    } catch (Exception erro) {
                        labelAviso.setText("Erro nos dados!");
                    }
                } else { //btAlterar
                    try {
                        pessoa = new Pessoa();
                        pessoa.setCpf(Integer.valueOf(fdCpf.getText()));
                        pessoa.setNomePessoa(fdNomePessoa.getText());
                        pessoa.setIdadePessoa(Integer.valueOf(fdIdadePessoa.getText()));
                        pessoa.setEmail(fdEmail.getText());
                        controle.atualizar(pessoa);
                        labelAviso.setText("Registro alterado com sucesso!");
                        fdCpf.setEnabled(true);
                        fdCpf.requestFocus();
                        fdNomePessoa.setEnabled(false);
                        fdIdadePessoa.setEnabled(false);
                        fdEmail.setEnabled(false);
                        btSalvar.setVisible(false);
                        btCancelar.setVisible(false);
                        btBuscar.setVisible(true);
                        btListar.setVisible(true);
                    } catch (Exception erro) {
                        labelAviso.setText("Erro nos dados!");
                    }
                }
                destino = destino + pessoa.getCpf() + ".png";
                CopiaImagem.copiar(origem, destino);
                destino = "src/fotos/";
            }
        }
    );

        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        try {
            origem = "/fotos/0.png";
            ImageIcon icone = new ImageIcon(getClass().getResource(origem));
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
            labelFoto.setIcon(icone);

        } catch (Exception erro) {
            System.out.println("erro ao carregar a imagem");
        }
                labelAviso.setText("Cancelado!");
                fdCpf.setEnabled(false);
                fdCpf.setText("");
                fdNomePessoa.setEnabled(false);
                fdNomePessoa.setText("");
                fdIdadePessoa.setEnabled(false);
                fdIdadePessoa.setText("");
                fdEmail.setEnabled(false);
                fdEmail.setText("");
                fdCpf.setEnabled(true);
                fdCpf.requestFocus();
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
            uploadFoto = true;
                acao = false;
                fdNomePessoa.setEnabled(true);
                fdIdadePessoa.setEnabled(true);
                fdEmail.setEnabled(true);
                fdNomePessoa.requestFocus();
                fdCpf.setEnabled(false);
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
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Confirma a exclusão do registro <Chave = " + pessoa.getCpf() + " >?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    controle.remover(pessoa);
                    labelAviso.setText("Removido!");
                    fdCpf.setText("");
                    fdNomePessoa.setText("");
                    fdNomePessoa.setEnabled(false);
                    fdIdadePessoa.setText("");
                    fdIdadePessoa.setEnabled(false);
                    fdEmail.setText("");
                    fdEmail.setEnabled(false);
String aux = String.valueOf(pessoa.getCpf()).trim();
                    origem = "src/fotos/" + aux + ".png";
                    System.out.println(origem);
                    try {
                        File f = new File(origem);
                        if (f.exists()) {
                            f.delete();
                        }
                    } catch (Exception erro) {
                        System.out.println("Erro");
                    }
                    btListar.setVisible(true);
                } else {
                    labelAviso.setText("Remoção cancelada!");
                    btAtualizar.setVisible(true);
                    btRemover.setVisible(true);
                }
            }
        }
        );

 labelFoto.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (uploadFoto) {
                    JFileChooser fc = new JFileChooser();
                    fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    if (fc.showOpenDialog(cp) == JFileChooser.APPROVE_OPTION) {
                        File img = fc.getSelectedFile();
                        origem = fc.getSelectedFile().getAbsolutePath();
                        try {
                            ImageIcon icone = new javax.swing.ImageIcon(img.getAbsolutePath());
                            Image imagemAux;
                            imagemAux = icone.getImage();
                            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
                            labelFoto.setIcon(icone);

                        } catch (Exception ex) {
                            System.out.println("Erro: " + ex.getMessage());
                        }
                    }

                }

            }
        });
 fdCpf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                Pessoa pessoa = new Pessoa();
                DAOPessoa daoPessoa = new DAOPessoa();
                List<String> listaAuxiliar = daoPessoa.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5,
                            fdCpf.getBounds().y + fdCpf.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                       String[] aux = selectedItem.split(";");
                        fdCpf.setText(selectedItem.split(";")[0]);
                        fdNomePessoa.setText(selectedItem.split(";")[1]);
                        fdIdadePessoa.setText(selectedItem.split(";")[2]);
                        fdEmail.setText(selectedItem.split(";")[3]);

                        //preparar para salvar
                       pessoa = daoPessoa.obter(Integer.valueOf(aux[0]));

                    } else {
                        fdCpf.requestFocus();
                        fdCpf.selectAll();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Não há nenhum produto cadastrado.");
                }
            }
        });
           

        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PessoaGUIListagem(controle.listInOrderNomeStrings("cpf"),cp);
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
