/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import conexao.Conexao;
import java.awt.Component;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
    
    private ArquivoController arquivoController = new ArquivoController();
    
    public DadosController(){
        this.caminho = System.getProperty("user.dir")+"\\banco\\Banco.db";
        listaEstruturaImportado = new ArrayList<>(4);
        listaEstrutura = new ArrayList<>(4);
        listaEstrutura.add("CREATE TABLE responsavel (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + "nome VARCHAR (255) NOT NULL, "
                + "cpf VARCHAR (11) NOT NULL)");
        listaEstrutura.add("CREATE TABLE setor (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + "nome VARCHAR (255) NOT NULL, "
                + "idResponsavel INTEGER NOT NULL REFERENCES responsavel (id) "
                + "ON DELETE RESTRICT ON UPDATE CASCADE)");
        listaEstrutura.add("CREATE TABLE usuario (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + "nome VARCHAR (100) NOT NULL, cpf VARCHAR (15) NOT NULL, usuario VARCHAR (255) NOT NULL, "
                + "senha VARCHAR (255) NOT NULL, master BOOLEAN NOT NULL)");
        listaEstrutura.add("CREATE TABLE naoConformidade (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + "descricao VARCHAR (255) NOT NULL, dataRegistro DATE NOT NULL, "
                + "dataAcontecimento DATE NOT NULL, reincidencia BOOLEAN NOT NULL, "
                + "abrangencia VARCHAR (255), origem VARCHAR (255), acaoCorrecao VARCHAR (255), "
                + "caminhoImagem VARCHAR (255), idSetor INTEGER REFERENCES setor (id) "
                + "ON DELETE RESTRICT ON UPDATE CASCADE NOT NULL, idResponsavel INTEGER REFERENCES responsavel (id) "
                + "ON DELETE RESTRICT ON UPDATE CASCADE NOT NULL)");
    }
    
    
    public void exportarBanco(){
        String caminhoArquivoExportado = arquivoController.obterCaminhoSaida();
        if(caminhoArquivoExportado != null){
            if(arquivoController.isArquivoSaidaExistente()){
                if(Mensagens.confirmar(null, "arquivo existe", "!!!", 0)){
                    arquivoController.copiarArquivo(caminho, caminhoArquivoExportado);
                    validarExportacao(caminhoArquivoExportado);
                }
            }
            else{
                arquivoController.criarArquivo(caminhoArquivoExportado);
                arquivoController.copiarArquivo(caminho, caminhoArquivoExportado);
                validarExportacao(caminhoArquivoExportado);
            }
        }
    }
    
    public void validarExportacao(String destino){
        if(isEstruturaValida(destino)){
            Mensagens.mensagem(null, "base de dados exportada com sucesso", "!!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public boolean importarBanco(boolean forcarSelecao){
        String caminhoArquivoImportado = arquivoController.obterCaminhoEntrada(forcarSelecao);
        if(caminhoArquivoImportado != null){
            while(!isEstruturaValida(caminhoArquivoImportado)){
                if(forcarSelecao){
                    if(!Mensagens.confirmar(componentePai, "O arquivo selecionado não corresponde à base de "
                            + "dados do Sistema de Gerenciamento de Não Conformidade."
                            + "\nTentar novamente?\nSe não, um novo banco de dados será criado.", 
                            "Sistema de Gerenciamento de Não Conformidade - "
                            + "Arquivo selecionado não correspondente", JOptionPane.INFORMATION_MESSAGE)){
                        break;
                    }
                }
                else{
                    if(!Mensagens.confirmar(componentePai, "O arquivo selecionado não corresponde à base de "
                            + "dados do Sistema de Gerenciamento de Não Conformidade."
                            + "\nTentar novamente?", 
                            "Sistema de Gerenciamento de Não Conformidade - "
                            + "Arquivo selecionado não correspondente", JOptionPane.INFORMATION_MESSAGE)){
                        break;
                    }
                }
                caminhoArquivoImportado = arquivoController.obterCaminhoEntrada(forcarSelecao);
            }
            if(isEstruturaValida(caminhoArquivoImportado)){//se o arquivo é válido, copia ele pro diretório do sistema
                arquivoController.copiarArquivo(caminhoArquivoImportado, caminho);
                return true;
            }
        }
        return false;
    }
    
    private void importarOuCriarBanco(){
        if(!importarBanco(true)){
            criarBanco();
        }
    }
    
    private void criarBanco(){
        arquivoController.criarArquivo(caminho);
        criarTabelas();
    }
    
    public void verificarBanco(){
        File arquivo = new File(caminho);
        if(arquivo.exists()){
            while(!isEstruturaValida()){
                importarOuCriarBanco();
            }
        }
        else{
            importarOuCriarBanco();
        }
    }
    
    public boolean isBaseDeDadosValida(){
        boolean estruturaCerta = true;
        for (String estrutura : listaEstrutura) {
            if(!listaEstruturaImportado.contains(estrutura)){
                estruturaCerta = false;
            }
        }
        return estruturaCerta;
    }
    
    private boolean isEstruturaValida(String caminho){
        Connection conexao= new Conexao().abreConexao(caminho);
        listarEstruturas(conexao);
        return isBaseDeDadosValida();
    }
    
    private boolean isEstruturaValida(){
        Connection conexao = new Conexao().abreConexao();
        listarEstruturas(conexao);
        return isBaseDeDadosValida();
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
    
    private void criarTabelas(){
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
