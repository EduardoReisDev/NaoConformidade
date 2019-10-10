/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UsuarioDao;
import java.awt.Component;
import model.Usuario;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.function.Consumer;
import javax.swing.JOptionPane;
import view.Mensagens;
import view.usuario.Criar;
import view.usuario.Editar;
import view.usuario.Login;
import view.usuario.InserirCpf;

/**
 *
 * @author leona
 */
public class UsuarioController {
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
    public static final int ERRO = JOptionPane.ERROR_MESSAGE;
    
    /**
     *Tipo de mensagem atenção
     */
    public static final int ATENCAO = JOptionPane.WARNING_MESSAGE;
    
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
     *Mensagem a ser exibida no título da caixa de diálogo quando o usuário que efetuou login não é master
     */
    public static final String TITULO_MENSAGEM_SOMENTE_USUARIO_MASTER = "Usuário não master";
    
    /**
     *Mensagem a ser exibida na caixa de diálogo quando o usuário que efetuou login não é master
     */
    public static final String MENSAGEM_SOMENTE_USUARIO_MASTER = "Somente usuários masters poderão prosseguir!";
    
    
    
    /**
     *
     * @param usuario Usuário selecionado
     * @return Uma mensagem contendo as informações do usuário
     */
    public static String mensagemExcluirUsuário(Usuario usuario){
        return "Tem certeza que deseja excluir o usuário " + usuario.getNome() + "?";
    }
    
    /**
     *
     * @param usuario Usuário excluído
     * @return Uma mensagem de sucesso
     */
    public static String mensagemSucessoExcluirUsuario(Usuario usuario){
        return "Sucesso ao excluir o usuário " + usuario.getNome() + "!";
    }
    
    /**
     *
     * @param usuario Usuário excluído
     * @return Uma mensagem de erro
     */
    public static String mensagemErroExcluirUsuario(Usuario usuario){
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
    
    public static String removeCaracteresInvalidos(String cpf){
        int tamanho = cpf.length();
        if(tamanho>=0){
            for(int i = 0; i < tamanho; i++){
                if(!Character.isDigit(cpf.charAt(i))){
                    cpf = cpf.replace(String.valueOf(cpf.charAt(i)), "");
                }
                tamanho = cpf.length();
            }
            if(tamanho>=11){
                cpf = cpf.substring(0, 11);
            }
        }
        return cpf;
    }
    
    /**
     *
     * @param cpf CPF a ser validado
     * @return true se o CPF for válido ou false se não
     */
    public static boolean validarCpf(String cpf){
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
    
    
    public static void ListarUsuarios(Consumer<? super Usuario> resultado){
        new UsuarioDao().listarTodos(resultado::accept);
    }
    
    public static Usuario listarPorId(int id){
        return new UsuarioDao().listarPorId(id);
    }
    
    
    public static Usuario formularioLogin() {
        Login formularioLogin = new Login(new javax.swing.JFrame(), true);
        formularioLogin.setLocationRelativeTo(formularioLogin);
        formularioLogin.setVisible(true);
        if(!formularioLogin.getTxtUsuario().getText().isEmpty()){
            return new Usuario(
                    formularioLogin.getTxtUsuario().getText(),
                    String.valueOf(formularioLogin.getTxtSenha().getPassword())
            );
        }
        return null;
    }
    
    public static Usuario login(){
        int tentativas = 0;
        Usuario usuario = null;
        Usuario usuarioFormulario;
        if(existeUsuarios()){//chama o diálogo de login
            while(usuario == null && tentativas < TENTATIVAS_MAXIMAS_LOGIN){
                tentativas++;
                usuarioFormulario = formularioLogin();
                if(usuarioFormulario!=null){
                    usuario = new UsuarioDao().login(
                            usuarioFormulario.getUsuario(), 
                            usuarioFormulario.getSenha()
                            );
                    if(usuario == null){
                        if(TENTATIVAS_MAXIMAS_LOGIN-tentativas > 0){
                            if(!Mensagens.confirmar(
                                    null,
                                    mensagemLoginNaoEftuado(tentativas),
                                    TITULO_MENSAGEM_LOGIN_NAO_EFETUADO,
                                    ATENCAO)){
                                break;
                            }
                        }
                    }
                }
                else{
                    break;
                }
            }
        }
        else{//chama o diálogo de cadastro de usuários
            if(cadastrarUsuario()){
                return login();
            }
        }
        return usuario;
    }
    
    public static void excluir(int id){
        Usuario usuario = listarPorId(id);
        if(Mensagens.confirmar(
                null,
                mensagemExcluirUsuário(usuario),
                TITULO_EXCLUIR,
                QUESTAO)){
            if(new UsuarioDao().excluir(id)){
                Mensagens.mensagem(
                        null,
                        mensagemSucessoExcluirUsuario(usuario),
                        TITULO_SUCESSO_EXCLUSAO,
                        SUCESSO);
            }
            else{
                Mensagens.mensagem(
                        null,
                        mensagemErroExcluirUsuario(usuario), 
                        TITULO_ERRO_EXCLUSAO,
                        ERRO);
            }
        }
    }
    
    public static Usuario inicio(Usuario usuarioValueObject) {
        Editar dialog = new Editar(new javax.swing.JFrame(), true);
        dialog.setLocationRelativeTo(null);
        dialog.preencherCampos(usuarioValueObject);
        dialog.setVisible(true);
        if(dialog.validar()){
            return new Usuario(
                                        dialog.getTxtNome().getText(),//nome
                                        dialog.getTxtUsuario().getText(),//usuário
                                        String.copyValueOf(dialog.getTxtSenha().getPassword()),//senha
                                        dialog.getCheckMaster().isSelected()//usuário master
            );   
        }
        return null;
    }  
    
    public static boolean editar(int id){
        Usuario usuarioEditar = null;
        Usuario usuarioSelecionado = listarPorId(id);
        while(usuarioEditar == null){
            usuarioEditar = inicio(usuarioSelecionado);//pega os dados do formulário
            if(usuarioEditar==null){//se o formulário retorna nulo mostra uma mensagem
                if(!Mensagens.confirmar(
                        null, 
                        MENSAGEM_FORMULARIO_NAO_PREENCHIDO,
                        TITULO_MENSAGEM_FORMULARIO_NAO_PREENCHIDO,
                        ERRO)){
                    break;
                }
            } 
            else{//se não, coloca o cpf e insere no banco
                usuarioEditar.setCpf(usuarioSelecionado.getCpf());//pega o cpf do usuário selecionado
                usuarioEditar.setId(usuarioSelecionado.getId());//peda o id do usuário selecionado
                return new UsuarioDao().editar(usuarioEditar);//salva no banco de dados
            }
        }
        return false;
    }
    
    public static void mensagemSomenteMaster(Component pai){
        Mensagens.mensagem(
                pai, 
                MENSAGEM_SOMENTE_USUARIO_MASTER, 
                TITULO_MENSAGEM_SOMENTE_USUARIO_MASTER, 
                ATENCAO);
    }
    
    /**
     *Este método é responsável por fazer o login e verificar se o usuário logado é master
     * @return true se o usuário logado for master, se não, retorna false
     */
    public static boolean usuarioMaster(){
        Usuario usuario =  login();
        if(usuario!=null){
            return usuario.isMaster();
        }
        else{
            return false;
        }
    }
    
    /**
     * Este método é responsável por verificar se os dados inseridos no formulário confirmam com os 
     * dados armazenados no banco de dados, possibilitando o login.
     * @return Dados de usuário caso os dados informados confirmam com os armazenados no banco de 
     * dados ou nulo, caso contrário.
     */
    
    
    /**
     * Este método é responsável por verificar se existem usuários no banco de dados.
     * @return true quando existe e false quando não existe usuários armazenados.
     */
    public static boolean existeUsuarios(){
        return new UsuarioDao().existeUsuarios();
    }
    
    /**
     * Este método é responsável por abrir o formulário de cpf e retornar o cpf inserido
     * @return Uma String contendo o cpf fornecido ou null quando o cpf fornecido não é valido ou não fornecido
     */
    private static String formularioCPF(){
        InserirCpf formularioCpf = new InserirCpf(new javax.swing.JFrame(), true);
        String cpf = null;
        formularioCpf.setLocationRelativeTo(null);
        while(cpf==null || cpf.length() == 0){
            formularioCpf.setVisible(true);
            cpf = formularioCpf.getTxtCpf().getText();
            if(validarCpf(cpf)){
                return cpf;
            }
            else{
                if(!Mensagens.confirmar(
                        null, 
                        MENSAGEM_CPF_NAO_FORNECIDO, 
                        TITULO_MENSAGEM_CPF_NAO_FORNECIDO,
                        ATENCAO)){
                    break;
                }
            }
        }
        return null;
    }
    
    /**
     * Este método é responsável por abrir o formulário de cpf e retornar o cpf inserido
     * @return Uma String contendo o cpf fornecido ou null quando o cpf fornecido não é valido ou não fornecido
     */
    private static Usuario formularioUsuario(){
        Criar formularioUsuario = new Criar(new javax.swing.JFrame(), true);
        formularioUsuario.setLocationRelativeTo(null);
        Usuario usuario = null;
        while(usuario == null){
            formularioUsuario.setVisible(true);
            if(formularioUsuario.validar()){
                usuario = new Usuario(
                        formularioUsuario.getTxtNome().getText(),//nome
                        formularioUsuario.getTxtUsuario().getText(),//usuário
                        String.copyValueOf(formularioUsuario.getTxtSenha().getPassword()),//senha
                        formularioUsuario.getCheckMaster().isSelected()//usuário master
                );   
            }
            else{
                if(!Mensagens.confirmar(
                        null, 
                        MENSAGEM_FORMULARIO_NAO_PREENCHIDO,
                        TITULO_MENSAGEM_FORMULARIO_NAO_PREENCHIDO,
                        ERRO)){
                    break;
                }
            }
        }
        return usuario;
    }
    
    public static boolean cadastrarUsuario(){
        String cpf = formularioCPF();
        if(cpf != null){//se retornou um cpf
            Usuario usuario = new UsuarioDao().listarPorCpf(cpf);
            if(usuario != null){//verifica no banco se existe um usuário com o mesmo cpf
                editar(usuario.getId());
            }
            else{
                usuario = formularioUsuario();
                if(usuario!=null){//se não retornar nulo, coloca o cpf e insere no banco
                    usuario.setCpf(cpf);
                    return new UsuarioDao().salvar(usuario);//salva no banco de dados
                }
            }
        }
        return false;
    }
    
    public static boolean validarNome(String nome){
        return nome.length()>2;
    }
    
    public static boolean validarUsuario(String usuario){
        return usuario.length()>2;
    }
    
    public static boolean validarSenha(char [] senha){
        return senha.length > 3;
    }
    
    public static boolean verificarSenha(char [] senha, char [] confirmaSenha){
        return Arrays.equals(senha, confirmaSenha) && confirmaSenha.length>3;
    }
    
    public static String removerCaracteresInvalidos(String cpf){
        return removeCaracteresInvalidos(cpf);
    }
    
}
