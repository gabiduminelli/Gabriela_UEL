package Menu;

import Entidades.*;
import GUIs.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import Auxiliar.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
public class MenuPessoaAluno extends JFrame {
    public static void main(String[] args) {
        MenuPessoaAluno menuPessoaAluno = new MenuPessoaAluno();
    }
    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JLabel lbTitulo = new JLabel("Seja Bem Vindo! - Cadastros UEL");
    Font fonte = new Font("Monotype Corsiva", Font.BOLD, 30);

    JLabel labelComImagemDeTamanhoDiferente = new JLabel();

    JMenuBar menuBar = new JMenuBar();
    JMenu menuCadastros = new JMenu("Cadastros");
    JMenuItem cadPessoa = new JMenuItem("Pessoa");
    JMenuItem cadAluno = new JMenuItem("Aluno");
    JMenuItem cadProfessor = new JMenuItem("Professor");
    JMenuItem cadCurso = new JMenuItem("Curso");
    JMenuItem cadMateria = new JMenuItem("Materia");
    JMenuItem cadTitulacao = new JMenuItem("Titulaçao");
    JMenuItem cadPeriodo = new JMenuItem("Periodo");
    JMenuItem cadTurma = new JMenuItem("Turma");

    Point p;

    public MenuPessoaAluno() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //setSize(dimensao);
        setTitle(lbTitulo.getText());

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        pnNorte.add(lbTitulo);
        lbTitulo.setFont(fonte);
        pnNorte.setBackground(Color.LIGHT_GRAY);

        cadPessoa.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new GUIPessoa();
            }
        });
         cadAluno.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new GUIAluno();
            }
        });
         cadProfessor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               new GUIProfessor();
            }
        });
         cadCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new GUICurso();
            }
        });
         cadMateria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new GUIMateria();
            }
        });
         cadTitulacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new GUITitulacao();
            }
        });
         cadPeriodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new GUIPeriodo();
            }
        });
         cadTurma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new GUITurma();
            }
        });
        ImageIcon imgcentro = new ImageIcon(getClass().getResource("/icones/logo_uel.jpg"));
        JLabel lbimg = new JLabel(imgcentro);
        pnCentro.add(labelComImagemDeTamanhoDiferente);
        pnCentro.setBackground(Color.BLACK);

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        pnCentro.add(lbimg);

        setJMenuBar(menuBar);
        menuBar.add(menuCadastros);

        menuCadastros.add(cadPessoa);
        menuCadastros.add(cadAluno);
        menuCadastros.add(cadProfessor);
        menuCadastros.add(cadCurso);
        menuCadastros.add(cadMateria);
        menuCadastros.add(cadTitulacao);
        menuCadastros.add(cadPeriodo);
        menuCadastros.add(cadTurma);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Sai do sistema  
                System.exit(0);
            }
        });

        
        pack();
        p = new CentroDoMonitorMaior().getCentroMonitorMaior(this);
        setLocation(p);
        setVisible(true);
    }
}

