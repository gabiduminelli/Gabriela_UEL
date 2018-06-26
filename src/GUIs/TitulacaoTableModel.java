package GUIs;


import DAOs.DAOTitulacao;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import daos.*;
import Entidades.Titulacao;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author Gabi
 */
public class TitulacaoTableModel  extends AbstractTableModel {
    DAOTitulacao daoTitulacao = new DAOTitulacao();
    Titulacao titulacao = new Titulacao();
    private final Class classes[] = new Class[]{Integer.class, String.class};
    private final String colunas[] = new String[]{"id", "Nome"};
    private List<Titulacao> dados;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
   
    public TitulacaoTableModel(List<Titulacao> dados) {
        this.dados = dados;
    }

    public void setDados(List<Titulacao> dados) {
        this.dados = dados;
    }

    public List<Titulacao> getDados() {
        return this.dados;
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return classes[columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Titulacao s = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return s.getIdTitulacao();
            case 1:
                return s.getNomeTitulacao();
           
            default:
                throw new IndexOutOfBoundsException("Coluna Inválida!");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex==0) {
            return false;
        }
        return true;
    }

 

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

      //  mudou = true;
        Titulacao s = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:              
                    s.setIdTitulacao((Integer) aValue);                
                break;
            case 1:
                s.setNomeTitulacao((String) aValue);
                break;          
            default:
                throw new IndexOutOfBoundsException("Coluna Inválida!!!");
        }
        fireTableDataChanged();
    }

    public Titulacao getValue(int rowIndex) {
        return dados.get(rowIndex);
    }

    public int indexOf(Titulacao conta) {
        return dados.indexOf(conta);
    }

    public void onAdd(Titulacao conta) {
        dados.add(conta);
        fireTableRowsInserted(indexOf(conta), indexOf(conta));
    }

    public void onRemove(int[] rowIndex) {
        int x;
        for (x = rowIndex.length - 1; x >= 0; x--) {
            dados.remove(rowIndex[x]);
            fireTableRowsDeleted(rowIndex[x], rowIndex[x]);
        }
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    

    
}

    

