/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author leona
 */
public class Mensagens {

    /**
     *
     * @param pai componente pai da mensagem
     * @param mensagem mensagem a ser exibida na caixa de diálogo
     * @param titulo título da janela
     * @param tipoDeMensagem tipo da mensagem
     * @return true se o usuário clicar em sim, false se clicar em não
     * 
     */
    public static boolean confirmar(Component pai, String mensagem, String titulo, int tipoDeMensagem){
        return JOptionPane.showConfirmDialog(pai, mensagem, titulo, JOptionPane.YES_OPTION ,tipoDeMensagem) == 0;
    }
    
    /**
     *
     * @param pai componente pai da mensagem
     * @param mensagem mensagem a ser exibida na caixa de diálogo
     * @param titulo título da janela
     * @param tipoDeMensagem tipo da mensagem
     */
    public static void mensagem(Component pai, String mensagem, String titulo, int tipoDeMensagem){
        JOptionPane.showMessageDialog(pai, mensagem, titulo, tipoDeMensagem);
    }
}
