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
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leona
 */
public class PropriedadesController {
    private Properties propriedades; 
    private File arquivo;
    FileInputStream arquivoEntrada;
    
    public PropriedadesController(String caminho) {
        this.propriedades = new Properties();
        arquivo = new File(caminho);
        boolean arquivoExistente = true;
        if(!arquivo.exists()){
            arquivoExistente = false;
            try {
                arquivo.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(PropriedadesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        lerArquivoPropriedades();
        if(!arquivoExistente){
            criarPropriedades();
        }
    }
    
    private void lerArquivoPropriedades(){
        try {
            arquivoEntrada = new FileInputStream(arquivo);
            propriedades.load(arquivoEntrada);
            arquivoEntrada.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PropriedadesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PropriedadesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String ler(String chave){
        lerArquivoPropriedades();
        return propriedades.getProperty(chave);
    }
    
    
    private void criarPropriedades(){
        lerArquivoPropriedades();
        escrever("confirmar.fechar", "true");
    }
    
    public void escrever(String chave, String valor){
        propriedades.setProperty(chave, valor);
        
        try {
            FileOutputStream arquivoSaida = new FileOutputStream(arquivo);
            propriedades.store(arquivoSaida, "UTF-8");
            //arquivoSaida.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PropriedadesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PropriedadesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
      PropriedadesController p =  new PropriedadesController(System.getProperty("user.dir")+"\\configuracoes.ini");
      //p.criarPropriedades();
        System.out.println(p.ler("confirmar.fechar"));
    }
}

