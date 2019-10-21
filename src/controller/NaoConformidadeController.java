
 package controller;

import  java.awt.Component ;
import  java.awt.Frame ;
import  javax.swing.JOptionPane ;
import  view.Mensagens ;
import  view.naoconformidade.CadastroNaoConformidade ;

    public class NaoConformidadeController {
    Component componentePai;

    public  Component  getComponentePai () {
        return componentePai;
    }

    public  void  setComponentePai ( Component  componentePai ) {
        this.componentePai = componentePai;
    }
    
    
     public boolean validarTexto( String texto ) { 
        return texto.equals("");
    }
    
    public  void  obrigatorio( Component  componentePai ) {
        Mensagens.mensagem (componentePai, " Preencha este campo! " , " ATENÇÃO! " , 2 );
    }

    void  cadastrar () {
        CadastroNaoConformidade telaCadastro =  new  CadastroNaoConformidade (( Frame ) componentePai, true );
        telaCadastro . setLocationRelativeTo (componentePai);
        telaCadastro . setVisible ( true );
    }

    
    
}