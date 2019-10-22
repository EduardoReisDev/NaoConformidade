/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Arrays;
import java.util.InputMismatchException;
import javax.swing.JOptionPane;
import model.Usuario;

/**
 *
 * @author leona
 */
public class Resources {
    /**
    *Tipo de mensagem questionamento
    */
    public int QUESTAO;

    /**
     *Tipo de mensagem sucesso
     */
    public int SUCESSO;
    
    /**
     *Tipo de mensagem erro
     */
    
    public final int ERRO;
    
    /**
     *Tipo de mensagem atenção
     */
    public final int ATENCAO;
    
    /**
     *Mensagem a ser exibida no título da caixa de diálogo de excluir usuário
     */
    public final String TITULO_EXCLUIR_USUARIO;
    /**
     *Mensagem a ser exibida no título da caixa de diálogo quando se obteve sucesso ao excluir um cliente
     */
    public final String TITULO_SUCESSO_EXCLUSAO_USUARIO;
    /**
     *Mensagem a ser exibida no título da caixa de diálogo quando se obteve erro ao excluir um cliente
     */
    public final String TITULO_ERRO_EXCLUSAO_USUARIO;
    
    /**
     *Mensagem a ser exibida no titulo da caixa de diálogo quando o login não é feito com sucesso
     */
    public final String TITULO_MENSAGEM_LOGIN_NAO_EFETUADO;
    
    /*
     *Mensagem a ser exibida no título da caixa de diálogo quando o usuário que efetuou login não é master
     */
    public final String TITULO_MENSAGEM_SOMENTE_USUARIO_MASTER;
    
    /**
     *Mensagem a ser exibida na caixa de diálogo quando o usuário que efetuou login não é master
     */
    public final String MENSAGEM_SOMENTE_USUARIO_MASTER;

    public Resources() {
        this.MENSAGEM_SOMENTE_USUARIO_MASTER = "Somente usuários masters poderão prosseguir!";
        this.TITULO_MENSAGEM_SOMENTE_USUARIO_MASTER = "Usuário não master";
        this.QUESTAO = JOptionPane.QUESTION_MESSAGE;
        this.ERRO = JOptionPane.ERROR_MESSAGE;
        this.SUCESSO = JOptionPane.INFORMATION_MESSAGE;
        this.ATENCAO = JOptionPane.WARNING_MESSAGE;
        this.TITULO_EXCLUIR_USUARIO = "Excluir usuário";
        this.TITULO_SUCESSO_EXCLUSAO_USUARIO = "Sucesso ao excluir";
        this.TITULO_ERRO_EXCLUSAO_USUARIO = "Erro ao excluir";
        this.TITULO_MENSAGEM_LOGIN_NAO_EFETUADO = "Dados incorretos";
    }
   
    public String removerCaracteresInvalidosCpf(String cpf){
        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");
        return cpf;
    }
    
    /**
     *
     * @param cpf CPF a ser validado
     * @return true se o CPF for válido ou false se não
     */
    public boolean validarCpf(String cpf){
        cpf = removerCaracteresInvalidosCpf(cpf);
        int soma = 0;
        int peso = 10;
        int resto;
        int digito10;
        int digito11;
        if (cpf.equals("00000000000") || cpf.equals("11111111111") ||
            cpf.equals("22222222222") || cpf.equals("33333333333") ||
            cpf.equals("44444444444") || cpf.equals("55555555555") ||
            cpf.equals("66666666666") || cpf.equals("77777777777") ||
            cpf.equals("88888888888") || cpf.equals("99999999999") ||
            (cpf.length() != 11)){
            return false;
        }
        try{
            for(int i=0; i<9; i++){
                soma+=(((int)cpf.charAt(i)-48)*peso);
                peso--;
            }
            resto = 11 - (soma%11);
            if(resto == 10 || resto == 11){
                digito10 = 0;
            }
            else{
                digito10 = resto;
            }
            //valida segundo dígito
            soma=0;
            peso=11;
            for(int i=0; i<10; i++){
                soma+=(((int)cpf.charAt(i)-48)*peso);
                peso--;
            }
            resto = 11 - (soma%11);
            if(resto == 10 || resto == 11){
                digito11 = 0;
            }
            else{
                digito11 = resto;
            }
            return(digito10 == ((int) cpf.charAt(9)-48) && digito11 == ((int) cpf.charAt(10)-48));
        }
        catch(InputMismatchException ex){
             
        }
        return false;
    }
    
}
