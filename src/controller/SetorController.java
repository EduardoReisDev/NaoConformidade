/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SetorDao;
import java.awt.Component;
import java.awt.Frame;
import java.util.function.Consumer;
import model.Setor;
import view.setor.FormCadastrar;
import view.setor.FormEditar;

/**
 *
 * @author leona
 */

public class SetorController {
    private Component componentePai;
    private Setor setor;
    private Resources rsc;
    
    FormCadastrar cadastro;
    FormEditar edicao;
    
    public SetorController() {
        this.rsc = new Resources();
    }
    
      /**
     *Este método é responsável por acessar a tela FormCadastrar de Setores
     */
    
    public void cadastrar(){
        cadastro = new FormCadastrar((Frame) componentePai, true);
        cadastro.setLocationRelativeTo(null);
        cadastro.setVisible(true);
    }
    
     /**
     *Este método é responsável por acessar a tela FormEditar de Setores
     */
    
    public void editar(){
        edicao = new FormEditar((Frame)componentePai, true);
        edicao.setLocationRelativeTo(null);
        edicao.setVisible(true);
    }
    
     /**
     *Este método é responsável por listar todos os setores existentes no banco de dados
     * @param resultado resultado da listagem
     */
    
    public void listarSetores(Consumer<? super Setor> resultado){
        new SetorDao().listarTodos(resultado::accept);
    }
    
    
    
}