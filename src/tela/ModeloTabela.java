/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Leonardo Jardim Ribeiro
 * @param <T> Tipo da classe que será utilizada para mostrar os dados na tabela
 * 
 */
public class ModeloTabela<T> extends javax.swing.table.AbstractTableModel{
    private List<T> dados;
    private final String[] nomeColunas;
    private final String[] nomeMetodos;
   
    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return nomeColunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return getValor(nomeMetodos[columnIndex], rowIndex);
    }

    @Override
    public String getColumnName(int column) {
        return nomeColunas[column]; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    /**
     *
     * @param nomeMetodos Um array contendo os nomes dos métodos "get", por exemplo: {"getNome","getIdade"}
     * @param nomeColunas Um array contendo os nomes das colunas, por exemplo: {"Nome", "Idade"}
     */
    public ModeloTabela (String [] nomeMetodos, String [] nomeColunas){
        this.nomeMetodos = nomeMetodos;
        this.nomeColunas = nomeColunas;
    }
    
    /**
     *
     * @param nomeMetodo Nome do método
     * @param index Indice da lista
     * @return
     */
    public Object getValor(String nomeMetodo, int index){        
        try {
            return dados.get(index).getClass().getMethod(nomeMetodo).invoke(dados.get(index));
        } 
        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException  ex) {
            Logger.getLogger(ModeloTabela.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     *
     * @param linha Objeto contendo os dados que serão mostrados na tabela
     */
    public void addLinha(T linha){
        dados.add(linha);
        this.fireTableDataChanged();
    }
    
    /**
     *
     * @param index Índice da linha a ser removida
     */
    public void remLinha(int index){
        dados.remove(index);
        this.fireTableRowsDeleted(index, index);
    }
    
    /**
     *
     * @param lista ArrayList contendo os dados que serão mostrados na tabela
     */
    public void addLista(ArrayList<T> lista){
        dados = lista;
        this.fireTableDataChanged();
    }
}
