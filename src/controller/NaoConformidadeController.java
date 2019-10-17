/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author leona
 */
public class NaoConformidadeController {
    public boolean validarDescricao(String nome){
        return nome.length()>0; 
    }
    public boolean validarAbrangencia(String descricao){
        return descricao.equals("");
    }
    public boolean validarOrigem(String origem){
        return origem.equals(""); 
    }
    public boolean validarAcaoCorrecao(String acaoCorrecao){
        return acaoCorrecao.equals(""); 
    }
    
}
