/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import view.DialogoCopiar;
import view.Mensagens;

/**
 *
 * @author leona
 */
public class ArquivoController {
    private DialogoCopiar dialogCopia;

    private InputStream entrada;
    private OutputStream saida;
    private File arquivoEntrada;
    private File arquivoSaida;
    
    public String obterCaminhoEntrada(boolean forcarSelecao){
        escolherArquivoOrigem(forcarSelecao);
        if(arquivoEntrada != null){
            return arquivoEntrada.getAbsolutePath();
        }
        return null;
    }
    
    public String obterCaminhoSaida(){
        escolherArquivoDestino();
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
        copiarArquivo();
    }
    
    private void copiarArquivo(){
       // new SwingWorker<Object, Object>() {
            //@Override
           // protected Object doInBackground() throws Exception {
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
                    Logger.getLogger(DadosController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException | InterruptedException ex) {
                    Logger.getLogger(DadosController.class.getName()).log(Level.SEVERE, null, ex);
                }
                //return null;
           // }
       // }.execute();
    }
    
    private void escolherArquivoOrigem(boolean forcarSelecao){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivo de banco de dados", "db"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        while(fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION){
            if(forcarSelecao){
                if(!Mensagens.confirmar(null, "Nenhum arquivo selecionado!\nTentar novamente?\n"
                        + "Se não, um novo banco de dados será criado.", 
                        "Sistema de Gerenciamento de Não Conformidade - "
                                + "Nenhum arquivo selecionado", JOptionPane.INFORMATION_MESSAGE)){
                    break;
                }
            }
            else{
                break;
            }
        }
        arquivoEntrada = fileChooser.getSelectedFile();
    }
    
    private void escolherArquivoDestino(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivo de banco de dados", "db"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setSelectedFile(new File("Banco de dados "+getDataEHora()+".db"));
        if(fileChooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION){
            fileChooser.setSelectedFile(null);
        }
        arquivoSaida = fileChooser.getSelectedFile();
    }
    
    public void limparArquivo(String caminho){
        try {
            new FileOutputStream(new File(caminho),false).close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DadosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isArquivoSaidaExistente(){
        return arquivoSaida.exists();
    }
    
    public String getDataEHora(){
        Calendar calendario = Calendar.getInstance();
        return ""+calendario.get(Calendar.DAY_OF_MONTH)+"-"
                +(calendario.get(Calendar.MONTH)+1)+"-"
                +calendario.get(Calendar.YEAR)+"-"
                +calendario.get(Calendar.HOUR)+"-"
                +calendario.get(Calendar.MINUTE)+"-"
                +calendario.get(Calendar.SECOND);
    }
    
    public void criarArquivo(String caminho){
        File arquivo = new File(caminho);
        if(!arquivo.exists()){
            arquivo.getParentFile().mkdir();
            try {
                arquivo.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(DadosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
