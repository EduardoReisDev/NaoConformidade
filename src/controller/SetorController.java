/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Component;
import java.awt.Frame;
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
    
    public void cadastrar(){
        cadastro = new FormCadastrar((Frame) componentePai, true);
        cadastro.setLocationRelativeTo(null);
        cadastro.setVisible(true);
    }
    
    public void editar(){
        edicao = new FormEditar((Frame)componentePai, true);
        edicao.setLocationRelativeTo(null);
        edicao.setVisible(true);
    }
    
}
