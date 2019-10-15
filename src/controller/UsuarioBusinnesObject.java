/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import javax.swing.JOptionPane;
import model.Usuario;

/**
 *
 * @author leona
 */
public class UsuarioBusinnesObject {

    public int QUESTAO;
/**
     *Tipo de mensagem questionamento
     */
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
     *Tentativas máximas de tentar fazer login
     */
    public final int TENTATIVAS_MAXIMAS_LOGIN;
    /**
     *Mensagem a ser exibida no título da caixa de diálogo de excluir usuário
     */
    public final String TITULO_EXCLUIR;
    /**
     *Mensagem a ser exibida no título da caixa de diálogo quando se obteve sucesso ao excluir um cliente
     */
    public final String TITULO_SUCESSO_EXCLUSAO;
    /**
     *Mensagem a ser exibida no título da caixa de diálogo quando se obteve erro ao excluir um cliente
     */
    public final String TITULO_ERRO_EXCLUSAO;
    /**
     *Mensagem a ser exibida na caixa de diálogo quando um formulário de cadastro retorna nulo ou com dados incompletos
     */
    public final String MENSAGEM_FORMULARIO_NAO_PREENCHIDO;
    /**
     *Mensagem a ser exibida no titulo da caixa de diálogo quando um formulário de cadastro retorna nulo 
     * ou com dados incompletos
     */
    public final String TITULO_MENSAGEM_FORMULARIO_NAO_PREENCHIDO;
    /**
     *Mensagem a ser exibida no titulo da caixa de diálogo quando o login não é feito com sucesso
     */
    public final String TITULO_MENSAGEM_LOGIN_NAO_EFETUADO;
    /**
     *Mensagem a ser exibida no titulo da caixa de diálogo quando nehuma credencial é fornecida
     */
    public final String TITULO_MENSAGEM_CREDENCIAIS_NAO_FORNECIDAS;
    /**
     *Mensagem a ser exibida no titulo da caixa de diálogo quando nehum cpf é fornecido
     */
    public final String TITULO_MENSAGEM_CPF_NAO_FORNECIDO;
    /**
     *Mensagem a ser exibida na caixa de diálogo quando nehum cpf é fornecido
     */
    public final String MENSAGEM_CPF_NAO_FORNECIDO;
    /**
     *Mensagem a ser exibida no título da caixa de diálogo quando o usuário que efetuou login não é master
     */
    public final String TITULO_MENSAGEM_SOMENTE_USUARIO_MASTER;
    /**
     *Mensagem a ser exibida na caixa de diálogo quando o usuário que efetuou login não é master
     */
    public final String MENSAGEM_SOMENTE_USUARIO_MASTER;

    public UsuarioBusinnesObject() {
        this.MENSAGEM_SOMENTE_USUARIO_MASTER = "Somente usuários masters poderão prosseguir!";
        this.TITULO_MENSAGEM_SOMENTE_USUARIO_MASTER = "Usuário não master";
        this.MENSAGEM_CPF_NAO_FORNECIDO = "CPF não inserido.\nDeseja tentar novamente?";
        this.QUESTAO = JOptionPane.QUESTION_MESSAGE;
        this.ERRO = JOptionPane.ERROR_MESSAGE;
        this.SUCESSO = JOptionPane.INFORMATION_MESSAGE;
        this.ATENCAO = JOptionPane.WARNING_MESSAGE;
        this.TENTATIVAS_MAXIMAS_LOGIN = 3;
        this.TITULO_EXCLUIR = "Excluir usuário";
        this.TITULO_SUCESSO_EXCLUSAO = "Sucesso ao excluir";
        this.TITULO_ERRO_EXCLUSAO = "Erro ao excluir";
        this.MENSAGEM_FORMULARIO_NAO_PREENCHIDO = "Dados de usuário não inseridos.\nDeseja tentar novamente?";
        this.TITULO_MENSAGEM_FORMULARIO_NAO_PREENCHIDO = "Dados de usuário não inseridos";
        this.TITULO_MENSAGEM_CPF_NAO_FORNECIDO = "CPF não inserido";
        this.TITULO_MENSAGEM_CREDENCIAIS_NAO_FORNECIDAS = "Credenciais não inseridas";
        this.TITULO_MENSAGEM_LOGIN_NAO_EFETUADO = "Dados incorretos";
    }
    
    /**
     *
     * @param usuario Usuário selecionado
     * @return Uma mensagem contendo as informações do usuário
     */
    public String mensagemExcluirUsuário(Usuario usuario){
        return "Tem certeza que deseja excluir o usuário " + usuario.getNome() + "?";
    }
    
    /**
     *
     * @param usuario Usuário excluído
     * @return Uma mensagem de sucesso
     */
    public String mensagemSucessoExcluirUsuario(Usuario usuario){
        return "Sucesso ao excluir o usuário " + usuario.getNome() + "!";
    }
    
    /**
     *
     * @param usuario Usuário excluído
     * @return Uma mensagem de erro
     */
    public String mensagemErroExcluirUsuario(Usuario usuario){
        return "Erro ao excluir o usuário " + usuario.getNome() + "!";
    }
    
    /**
     *
     * @param tentativas tentativas feitas até então
     * @return Uma mensagem de erro
     */
    public String mensagemLoginNaoEftuado(int tentativas){
        return "Os dados informados não correspondem a nenhum usuário salvo."
                + "\nDeseja tentar novamente?\n"+(TENTATIVAS_MAXIMAS_LOGIN-tentativas) + " tentativas restentes.";
    }
    
    /**
    *
    * @param tentativas tentativas feitas até então
    * @return Uma mensagem de erro
    */
    public String mensagemCredenciaisNaoFornecidas(int tentativas){
        return "Nenhum dado recebido.\nDeseja tentar novamente?\n" +(TENTATIVAS_MAXIMAS_LOGIN-tentativas) + " tentativas restentes.";
    }
    
    public boolean validarNome(String nome){
        return nome.length()>2;
    }
    
    public boolean validarUsuario(String usuario){
        return usuario.length()>2;
    }
    
    public boolean validarSenha(char [] senha){
        return senha.length > 3;
    }
    
    public boolean verificarSenha(char [] senha, char [] confirmaSenha){
        return Arrays.equals(senha, confirmaSenha) && confirmaSenha.length>3;
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
    
    
    /// teste com etapas...
    public static ArrayList<Etapas> obterEtapas(Acao acao){
        ArrayList<Etapas> etapas = new ArrayList<>();
        switch (acao){
            case ABRE_FORMULARIO_USUARIOS : {
                etapas.add(Etapas.LOGIN_MASTER);
                etapas.add(Etapas.ABRIR_FORMULARIO_USUARIOS);
            }
        }
        return etapas;
    }
}
