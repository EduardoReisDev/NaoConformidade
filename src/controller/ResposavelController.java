/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ResponsavelDao;
import java.awt.Component;
import java.awt.Frame;
import java.util.function.Consumer;
import model.Responsavel;
import view.responsaveis.CadastrarResponsavel;
import view.responsaveis.FormResponsavel;

/**
 *
 * @author leona
 */
public class ResposavelController {
    private Component componentePai; 
    
    public void listarTodos(Consumer <?super Responsavel> responsavel){
        new ResponsavelDao().listarTodos(responsavel::accept);
    }
    public void listarPorNome(Consumer <?super Responsavel> responsavel, String nome){
        new ResponsavelDao().listarPorNome(responsavel::accept, nome);
    }
    public Responsavel listarPorId(int id){
        return new ResponsavelDao().listarPorId(id);
    }
    
    public int getLastId(){
        return new ResponsavelDao().getLastId();
    }
    
    private Responsavel abrirFormCadastroResponsavel(){
        CadastrarResponsavel formCadastro = new CadastrarResponsavel((Frame) componentePai, true);
        formCadastro.setLocationRelativeTo(null);
        formCadastro.getTxtCodigo().setText(String.format("%010d", getLastId()+1));
        formCadastro.setVisible(true);
        if(formCadastro.isValido()){
            return new Responsavel(
                    0, 
                    formCadastro.getTxtCpf().getText(), 
                    formCadastro.getTxtNome().getText()
            );
        }
        return null;
    }
    
    public boolean cadastrarResponsavel(){
        abrirFormCadastroResponsavel();
        return false;
    }
    public boolean excluirResponsavel(int id){
        return new ResponsavelDao().excluir(id);
    }
    
}