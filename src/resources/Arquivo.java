/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.awt.Component;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import view.DialogoCopiar;
import view.Mensagens;

/**
 *
 * @author leona
 */
public class Arquivo {
    
    private InputStream entrada;
    private OutputStream saida;
    private File arquivoEntrada;
    private File arquivoSaida;
    private OutputStreamWriter arquivoEscritor;
    private final JFileChooser fileChooser;
    private Component componentePai;
    
    public Arquivo() {
        this.fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    }
    
    public Arquivo(Component compontePai) {
        this.componentePai=compontePai;
        this.fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    }
    
    public String obterCaminhoEntrada(){
        if(arquivoEntrada != null){
            return arquivoEntrada.getAbsolutePath();
        }
        return null;
    }
    
    public String obterCaminhoSaida(){
        if(arquivoSaida != null){
            return arquivoSaida.getAbsolutePath();
        }
        return null;
    }
    
    private float calcularProgresso(long total, long concluido){
        return (float) (concluido*100)/total;
    }
    
    public void copiarArquivo(String caminhoOrigem, String caminhoDestino){
        arquivoEntrada = new File(caminhoOrigem);
        arquivoSaida = new File(caminhoDestino);
        DialogoCopiar dialogCopia;
        dialogCopia = new DialogoCopiar();
        dialogCopia.setLocationRelativeTo(null);
        dialogCopia.setVisible(true);
        try {
            if(!arquivoSaida.exists()){
                arquivoSaida.getParentFile().mkdirs();
                arquivoSaida.createNewFile();
            }
            entrada = new FileInputStream(arquivoEntrada);
            saida = new FileOutputStream(arquivoSaida);
            byte[] buf = new byte[1024];
            int len;
            while ((len = entrada.read(buf)) > 0) {
                saida.write(buf, 0, len);
                dialogCopia.setPorcentagem(calcularProgresso(arquivoEntrada.length(), arquivoSaida.length()));
            }
            Thread.sleep(500);
            entrada.close();
            saida.close();
            dialogCopia.dispose();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void escolherArquivoOrigem(String tipo, String descricao){
        arquivoEntrada = null;
        fileChooser.setFileFilter(new FileNameExtensionFilter(descricao, tipo));
        fileChooser.setAcceptAllFileFilterUsed(false);
        if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            arquivoEntrada = fileChooser.getSelectedFile();
        }
    }
    
    public void escolherArquivoDestino(String tipo, String descricao, String nome){
        arquivoSaida = null;
        fileChooser.setFileFilter(new FileNameExtensionFilter(descricao, tipo));
        fileChooser.setSelectedFile(new File(nome));
        fileChooser.setAcceptAllFileFilterUsed(false);
        if(fileChooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION){
            fileChooser.setSelectedFile(null);
        }
        arquivoSaida = fileChooser.getSelectedFile();
    }
    
    public boolean isArquivoSaidaExistente(){
        return arquivoSaida.exists();
    }
    
    
    public void criarArquivo(String caminho){
        File arquivo = new File(caminho);
        if(!arquivo.exists()){
            arquivo.getParentFile().mkdir();
            try {
                arquivo.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void abrirArquivoParaEscrita(){
        if(!arquivoSaida.getParentFile().exists()){
            arquivoSaida.getParentFile().mkdirs();
        }
        try {
            arquivoEscritor = new OutputStreamWriter(new FileOutputStream(arquivoSaida), "iso-8859-1");
        } catch (IOException ex) {
            Logger.getLogger(Arquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void escreverNoArquivoSaida(String str){
        try {
            arquivoEscritor.write(str);
        } catch (IOException ex) {
            Logger.getLogger(Arquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void fecharArquivo(){
        try {
            arquivoEscritor.close();
        } catch (IOException ex) {
            Logger.getLogger(Arquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
