/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessObject;

import javax.swing.JOptionPane;

/**
 *
 * @author leona
 */
public class UsuarioBusinessObject {
    /**
     *Tipo de mensagem questionamento
     */
    public static int QUESTAO = JOptionPane.QUESTION_MESSAGE;
    /**
     *Tipo de mensagem sucesso
     */
    public static int SUCESSO = JOptionPane.INFORMATION_MESSAGE;

    /**
     *Tipo de mensagem erro
     */
    public static int ERRO = JOptionPane.ERROR_MESSAGE;
    
    /**
     *Tipo de mensagem atenção
     */
    public static int ATENCAO = JOptionPane.WARNING_MESSAGE;
    
    /**
     *Tentativas máximas de tentar fazer login
     */
    public static int TENTATIVAS_MAXIMAS_LOGIN = 3;
    /**
     *Mensagem a ser exibida no título da caixa de diálogo de excluir usuário
     */
    public static final String TITULO_EXCLUIR = "Excluir usuário";

    /**
     *Mensagem a ser exibida no título da caixa de diálogo quando se obteve sucesso ao excluir um cliente
     */
    public static final String TITULO_SUCESSO_EXCLUSAO = "Sucesso ao excluir";
    
    /**
     *Mensagem a ser exibida no título da caixa de diálogo quando se obteve erro ao excluir um cliente
     */
    public static final String TITULO_ERRO_EXCLUSAO = "Erro ao excluir";
    
    /**
     *Mensagem a ser exibida na caixa de diálogo quando um formulário de cadastro retorna nulo ou com dados incompletos
     */
    public static final String MENSAGEM_FORMULARIO_NAO_PREENCHIDO = "Dados de usuário não inseridos.\nDeseja tentar novamente?";
    
    /**
     *Mensagem a ser exibida no titulo da caixa de diálogo quando um formulário de cadastro retorna nulo 
     * ou com dados incompletos
     */
    public static final String TITULO_MENSAGEM_FORMULARIO_NAO_PREENCHIDO = "Dados de usuário não inseridos";
    
    /**
     *Mensagem a ser exibida no titulo da caixa de diálogo quando o login não é feito com sucesso
     */
    public static final String TITULO_MENSAGEM_LOGIN_NAO_EFETUADO = "Dados incorretos";
    
    /**
     *Mensagem a ser exibida no titulo da caixa de diálogo quando nehuma credencial é fornecida
     */
    public static final String TITULO_MENSAGEM_CREDENCIAIS_NAO_FORNECIDAS = "Credenciais não inseridas";
    
    /**
     *Mensagem a ser exibida no titulo da caixa de diálogo quando nehum cpf é fornecido
     */
    public static final String TITULO_MENSAGEM_CPF_NAO_FORNECIDO = "CPF não inserido";
    
    /**
     *Mensagem a ser exibida na caixa de diálogo quando nehum cpf é fornecido
     */
    public static final String MENSAGEM_CPF_NAO_FORNECIDO = "CPF não inserido.\nDeseja tentar novamente?";
    
    /**
     *
     * @param usuario Usuário selecionado
     * @return Uma mensagem contendo as informações do usuário
     */
    public static String mensagemExcluirUsuário(UsuarioValueObject usuario){
        return "Tem certeza que deseja excluir o usuário " + usuario.getNome() + "?";
    }
    
    /**
     *
     * @param usuario Usuário excluído
     * @return Uma mensagem de sucesso
     */
    public static String mensagemSucessoExcluirUsuario(UsuarioValueObject usuario){
        return "Sucesso ao excluir o usuário " + usuario.getNome() + "!";
    }
    
    
    /**
     *
     * @param usuario Usuário excluído
     * @return Uma mensagem de erro
     */
    public static String mensagemErroExcluirUsuario(UsuarioValueObject usuario){
        return "Erro ao excluir o usuário " + usuario.getNome() + "!";
    }
    
    /**
     *
     * @param tentativas tentativas feitas até então
     * @return Uma mensagem de erro
     */
    public static String mensagemLoginNaoEftuado(int tentativas){
        return "Os dados informados não correspondem a nenhum usuário salvo."
                + "\nDeseja tentar novamente?\n"+(TENTATIVAS_MAXIMAS_LOGIN-tentativas) + " tentativas restentes.";
    }
    
    /**
    *
    * @param tentativas tentativas feitas até então
    * @return Uma mensagem de erro
    */
    public static String mensagemCredenciaisNaoFornecidas(int tentativas){
        return "Nenhum dado recebido.\nDeseja tentar novamente?\n" +(TENTATIVAS_MAXIMAS_LOGIN-tentativas) + " tentativas restentes.";
    }
}