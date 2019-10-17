/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Component;
import java.awt.Frame;
import javax.swing.JOptionPane;
import view.naoconformidade.CadastroNaoConformidade;

/**
 *
 * @author leona
 */
public class NaoConformidadeController {
    Component componentePai;

    public Component getComponentePai() {
        return componentePai;
    }

    public void setComponentePai(Component componentePai) {
        this.componentePai = componentePai;
    }
    
    
    public boolean validarDescricao(String nome){
        return nome.equals(""); 
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
    public void obrigatorio(Component componentePai){
        JOptionPane.showMessageDialog(componentePai, "Preencha este campo");
    }

    void cadastrar() {
        CadastroNaoConformidade telaCadastro = new CadastroNaoConformidade((Frame) componentePai, true);
        telaCadastro.setLocationRelativeTo(componentePai);
        telaCadastro.setVisible(true);
    }

    
    
}
