/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import conexao.Conexao;
import java.awt.Component;
import java.awt.Frame;
import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import view.DialogoCopiar;
import view.DialogoCopiarArquivo;
import view.Mensagens;

/**
 *
 * @author leona
 */
public class DadosController {
    private final String caminho;
    private final ArrayList<String> listaEstrutura;
    private final ArrayList<String> listaEstruturaImportado;
    private Component componentePai = null;
    
    private File arquivo;
    
    public DadosController(){
        this.caminho = System.getProperty("user.dir")+"\\banco\\Banco.db";
        listaEstruturaImportado = new ArrayList<>(4);
        listaEstrutura = new ArrayList<>(4);
        listaEstrutura.add("CREATE TABLE responsavel (id INTEGER PRIMARY KEY "
                + "AUTOINCREMENT NOT NULL, nome VARCHAR (255) NOT NULL, "
                + "cpf VARCHAR (11) NOT NULL)");
        listaEstrutura.add("CREATE TABLE setor (id INTEGER NOT NULL PRIMARY KEY "
                + "AUTOINCREMENT, nome VARCHAR (255) NOT NULL, "
                + "idResponsavel INTEGER NOT NULL REFERENCES responsavel (id) "
                + "ON DELETE RESTRICT ON UPDATE CASCADE)");
        listaEstrutura.add("CREATE TABLE naoConformidade (id INTEGER NOT NULL PRIMARY "
                + "KEY AUTOINCREMENT, descricao VARCHAR (255) NOT NULL, "
                + "dataRegistro DATE NOT NULL, dataAcontecimento DATE NOT NULL, "
                + "reincidencia BOOLEAN NOT NULL, abrangencia VARCHAR (255), "
                + "origem VARCHAR (255), acaoCorrecao VARCHAR (255), "
                + "caminhoImagem VARCHAR (255), idSetor INTEGER NOT NULL "
                + "REFERENCES setor (id) ON DELETE RESTRICT ON UPDATE CASCADE, "
                + "idResponsavel INTEGER REFERENCES responsavel (id) "
                + "ON DELETE RESTRICT ON UPDATE CASCADE NOT NULL, "
                + "FOREIGN KEY (idSetor) REFERENCES setor (idsetor) "
                + "ON DELETE NO ACTION ON UPDATE NO ACTION)");
        listaEstrutura.add("CREATE TABLE usuario (id INTEGER NOT NULL PRIMARY KEY "
                + "AUTOINCREMENT, nome VARCHAR (100) NOT NULL, cpf VARCHAR (15) NOT NULL, "
                + "usuario VARCHAR (255) NOT NULL, senha VARCHAR (255) NOT NULL, "
                + "master BOOLEAN NOT NULL)");
    }
    
    private File escolherArquivoOrigem(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivo de banco de dados", "db"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        while(fileChooser.showOpenDialog(componentePai) != JFileChooser.APPROVE_OPTION){
            if(!Mensagens.confirmar(componentePai, "Nenhum arquivo selecionado!\nTentar novamente?\n"
                    + "Se não, um novo banco de dados será criado.", 
                    "Sistema de Gerenciamento de Não Conformidade - "
                            + "Nenhum arquivo selecionado", JOptionPane.INFORMATION_MESSAGE)){
                break;
            }
        }
        return fileChooser.getSelectedFile();
    }
    
    private File escolherArquivoDestino(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivo de banco de dados", "db"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setSelectedFile(new File("Banco de dados.db"));
        if(fileChooser.showSaveDialog(componentePai) != JFileChooser.APPROVE_OPTION){
            fileChooser.setSelectedFile(null);
        }
        return fileChooser.getSelectedFile();
    }
    
    public void exportarBanco(){
        File arquivoSelecionado = escolherArquivoDestino();
        if(arquivoSelecionado != null){
            if(arquivoSelecionado.exists()){
                System.out.println("arquivo existe");
            }
            else{
                System.out.println("arquivo não existe");
            }
        }
    }
    
    public void importarBanco(){
        File arquivoSelecionado = escolherArquivoOrigem();
        boolean arquivoValido;
        if(arquivoSelecionado != null){//se o aruivo selecionado não é nulo, verifica se ele é válido
            arquivoValido = verificarEstruturaTabelasASerImportada(arquivoSelecionado.getPath());
            while(!arquivoValido){
                if(!Mensagens.confirmar(componentePai, "O arquivo selecionado não corresponde à base de "
                        + "dados do Sistema de Gerenciamento de Não Conformidade."
                        + "\nTentar novamente?\nSe não, um novo banco de dados será criado.", 
                        "Sistema de Gerenciamento de Não Conformidade - "
                        + "Arquivo selecionado não correspondente", JOptionPane.INFORMATION_MESSAGE)){
                    break;
                }
                else{
                    arquivoSelecionado = escolherArquivoOrigem();
                }
                arquivoValido = verificarEstruturaTabelasASerImportada(arquivoSelecionado.getPath());
            }
            if(arquivoValido){//se o arquivo é válido, copia ele pro diretório do sistema
                copiarArquivo(arquivoSelecionado, new File(caminho));
            }
        }
        else{//se nenhum arquivo é selecionado um novo é criado
           criarBanco();
        }
    }
    
    private void criarBanco(){
        criarArquivo();
        criarTabelas();
    }
    
    private float calcularProgresso(long total, long concluido){
        return (float) (concluido*100)/total;
    }
    
    DialogoCopiar dialogCopia;

    InputStream entrada;
    OutputStream saida;
    private void copiarArquivo(File src, File dst){
        new SwingWorker<Object, Object>() {
            @Override
            protected Object doInBackground() throws Exception {
                
                    dialogCopia = new DialogoCopiar();
                    dialogCopia.setLocationRelativeTo(null);
                    dialogCopia.setVisible(true);
                    try {
                        if(!dst.exists()){
                            File arquivoAux = new File(dst.getParent());
                            arquivoAux.mkdirs();
                            dst.createNewFile();
                        }
                        entrada = new FileInputStream(src);
                        saida = new FileOutputStream(dst);
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = entrada.read(buf)) > 0) {
                            saida.write(buf, 0, len);
                           dialogCopia.setPorcentagem(calcularProgresso(src.length(), dst.length()));
                        }
                        Thread.sleep(500);
                        //dialogCopia.dispose();
                        entrada.close();
                        saida.close();
                        dialogCopia.dispose();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(DadosController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException | InterruptedException ex) {
                        Logger.getLogger(DadosController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }.execute();
    }
    
    public void verificarBanco(){
        arquivo = new File(caminho);
        if(arquivo.exists()){
            while(!verificarEstruturaTabelas()){
                importarBanco();
            }
        }
        else{
            importarBanco();
        }
    }
    
    public boolean confirmarEstruturaTabelas(){
        boolean estruturaCerta = true;
        for (String estrutura : listaEstrutura) {
            if(!listaEstruturaImportado.contains(estrutura)){
                estruturaCerta = false;
            }
        }
        return estruturaCerta;
    }
    
    public boolean verificarEstruturaTabelasASerImportada(String caminho){
        Connection conexao= new Conexao().abreConexao(caminho);
        listarEstruturas(conexao);
        return confirmarEstruturaTabelas();
    }
    
    private boolean verificarEstruturaTabelas(){
        Connection conexao = new Conexao().abreConexao();
        listarEstruturas(conexao);
        return confirmarEstruturaTabelas();
    }
    
    private void listarEstruturas(Connection conexao){
        try {
            Statement stmt = conexao.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM sqlite_master WHERE type='table'");
            while(res.next()){
                listaEstruturaImportado.add(res.getString(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void limparArquivo(File arq){
        try {
            OutputStream arquivoSaida = new FileOutputStream(arq,false);
            arquivoSaida.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DadosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void criarArquivo(){
        arquivo = new File(caminho);
        if(arquivo.exists()){
            arquivo.mkdirs();
            try {
                arquivo.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(DadosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void criarTabelas(){
        limparArquivo(new File(caminho));
        Connection conexao = new Conexao().abreConexao();
        try {
            Statement stmt = conexao.createStatement();
            for(String estrutura : listaEstrutura){
                stmt.execute(estrutura);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            Conexao.fechaConexao(conexao);
        }
    }
}
