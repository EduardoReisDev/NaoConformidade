/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UsuarioDao;
import java.awt.Component;
import java.awt.Frame;
import java.util.Arrays;
import model.Usuario;
import java.util.function.Consumer;
import view.Mensagens;
import view.usuario.FormCadastrar;
import view.usuario.FormEditar;
import view.usuario.FormLogin;

/**
 *
 * @author leona
 */
public class UsuarioController {
    /**
     *Tentativas máximas de tentar fazer login
     */
    public final int TENTATIVAS_MAXIMAS_LOGIN;
    private Resources rsc;
    private Component componentePai;
    
    private Usuario usuario;
    
    public void setComponentePai(Component componentePai) {
        this.componentePai = componentePai;
    }
    
    public UsuarioController(){
        rsc = new Resources();
        this.TENTATIVAS_MAXIMAS_LOGIN = 3;
        usuario = new Usuario();
    }
    
    /**
     *Este método é responsável por listar todos os usuários existentes no banco de dados
     * @param resultado resultado da listagem
     */
    public void listarUsuarios(Consumer<? super Usuario> resultado){
        new UsuarioDao().listarTodos(resultado::accept);
    }
    
    /**
     *Este método é responsável por consultar o nome do usuário no banco de dados
     * @param resultado resulatado da listagem
     * @param nome nome do usuário a ser consultado
     */
    public void listarUsuariosPorNome(Consumer<? super Usuario> resultado, String nome){
        new UsuarioDao().lerPorNome(resultado::accept, nome);
    }
    
    /**
     *Este método faz a listagem a partir do id de usuário
     * @param id id do usuário a ser consultado;
     * @return dados de usuario, ou null se não for encontrado nenhum usuário
     */
    public Usuario listarPorId(int id){
        return new UsuarioDao().listarPorId(id);
    }
    
    public boolean verificarExistenciaCpf(String cpf) {
        return new UsuarioDao().listarPorCpf(rsc.removerCaracteresInvalidosCpf(cpf)) != null;
    }
    
    /**
     *Este método é responsável por abrir o formulário de login
     * @return dados de login caso eles sejam inseridos
     */
    public Usuario abrirFormularioLogin() {
        FormLogin formularioLogin = new FormLogin((Frame) componentePai, true);
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
    
    /**
     *Este método é responsável por fazer o login e verificar se o usuário logado é master
     * @return true se o usuário logado for master, se não, retorna false
     */
    public Usuario loginMaster(){
        usuario = login();
        if(usuario!= null){
            if(usuario.isMaster()){
                return usuario;
            }
            else{
                Mensagens.mensagem(componentePai, 
                        rsc.MENSAGEM_SOMENTE_USUARIO_MASTER, 
                        rsc.TITULO_MENSAGEM_SOMENTE_USUARIO_MASTER, 
                        rsc.ATENCAO);
            }
        }
        return null;
    }
    
    /**
     * Este método é responsável por verificar se os dados inseridos no formulário confirmam com os 
     * dados armazenados no banco de dados, possibilitando o login.
     * @return Dados de usuário caso os dados informados confirmam com os armazenados no banco de 
     * dados ou nulo, caso contrário.
     */
    public Usuario login(){
        int tentativas = 0;
        usuario = null;
        Usuario usuarioFormulario;
        if(verificarExistenciaDeUsuariosMasters()){//chama o diálogo de login
            while(usuario == null && tentativas < TENTATIVAS_MAXIMAS_LOGIN){
                tentativas++;
                usuarioFormulario = abrirFormularioLogin();
                if(usuarioFormulario!=null){
                    usuario = new UsuarioDao().login(
                                    usuarioFormulario.getUsuario(), 
                                    usuarioFormulario.getSenha()
                            );
                    if(usuario == null){
                        if(TENTATIVAS_MAXIMAS_LOGIN-tentativas > 0){
                            if(!Mensagens.confirmar(componentePai,
                                    mensagemLoginNaoEftuado(tentativas),
                                    rsc.TITULO_MENSAGEM_LOGIN_NAO_EFETUADO,
                                    rsc.ATENCAO)){
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
            if(cadastrar()){
                return login();
            }
        }
        return usuario;
    }
    
    /**
     *Este método é responsável por excluir o usuario do banco de dados
     * @param id id do usuário a ser excluído
     * @return true se foi excluído com sucesso ou false, se não
     */
    public boolean excluir(int id){
        if(confirmarExclusao(id)){
            if(new UsuarioDao().excluir(id)){
                exibirSucessoExclusao();
                return true;
            }
            else{
                exibirErroExclusao();
                return false;
            }
        }
        return false;
    }
    
    /**
     *
     * @param id id do usuário a ser excluído
     * @return true se foi confirmada a exclusão, false se não
     */
    public boolean confirmarExclusao(int id){
        usuario = listarPorId(id);
        return Mensagens.confirmar(componentePai,
                mensagemExcluirUsuario(usuario),
                rsc.TITULO_EXCLUIR_USUARIO,
                rsc.QUESTAO);
    }
    
    /**
     *Este método mostra uma mensagem de sucesso ao excluir
     */
    public void exibirSucessoExclusao(){
        Mensagens.mensagem(componentePai,
                mensagemSucessoExcluirUsuario(usuario),
                rsc.TITULO_SUCESSO_EXCLUSAO_USUARIO,
                rsc.SUCESSO);
    }
    
    /**
     *Este método mostra uma mensagem de erro ao excluir
     */
    public void exibirErroExclusao(){
        Mensagens.mensagem(componentePai,
                mensagemErroExcluirUsuario(usuario), 
                rsc.TITULO_ERRO_EXCLUSAO_USUARIO,
                rsc.ERRO);
    }
    
    /**
     *
     * @param usuario dados de usuário que serão utilizados para popular o formulário
     * @return se as informações inseridas no formulario de forma correta, retorna elas, se não, null 
     */
    public Usuario abrirFormEditar(Usuario usuario) {
        FormEditar formularioEditar = new FormEditar((Frame) componentePai, true);
        formularioEditar.setLocationRelativeTo(null);
        formularioEditar.preencherCampos(usuario);
        formularioEditar.setVisible(true);
        if(formularioEditar.validar()){
            return new Usuario(
                    usuario.getId(),
                    formularioEditar.getTxtNome().getText(),//nome
                    usuario.getCpf(),
                    formularioEditar.getTxtUsuario().getText(),//usuário
                    String.copyValueOf(formularioEditar.getTxtSenha().getPassword()),//senha
                    formularioEditar.getCheckMaster().isSelected()//usuário master
            );   
        }
        return null;
    }  
    
    /**
     *Este método é responsável por editar os dados de usuário no banco de dados 
     * @param id id do usuário a ser editado
     * @return true se foi editado com sucesso, false se não
     */
    public boolean editar(int id){
        usuario = listarPorId(id);
        usuario = abrirFormEditar(usuario);//pega os dados do formulário
        if(usuario!=null){//se não, coloca o cpf e o id e insere no banco
           return new UsuarioDao().editar(usuario);//salva no banco de dados
        } 
        return false;
    }
  
    /**
     * Este método é responsável por verificar se existem usuários masters no banco de dados.
     * @return true quando existe e false quando não existe usuários masters armazenados.
     */
    public boolean verificarExistenciaDeUsuariosMasters(){
        return new UsuarioDao().existeUsuariosMasters();
    }
    
    /**
     * Este método é responsável por abrir o formulário de cadastro de usuário e retornar os dados inseridos
     * @return dados de usuário caso forem válidos
     */
    private Usuario abrirFormCadastro(){
        FormCadastrar formCadastro = new FormCadastrar((Frame) componentePai, true);
        formCadastro.setLocationRelativeTo(null);
        usuario = null;
        formCadastro.setVisible(true);
        if(formCadastro.validar()){
            usuario = new Usuario(
                    0,//id (não será utilizado) 
                    formCadastro.txtNome.getText(),//nome
                    rsc.removerCaracteresInvalidosCpf(formCadastro.txtCpf.getText()),//cpf
                    formCadastro.txtUsuario.getText(),//usuário
                    String.copyValueOf(formCadastro.txtSenha.getPassword()),//senha
                    formCadastro.checkMaster.isSelected()//usuário master
            );   
        }
        return usuario;
    }
    
    /**
     *Este método é responsável por cadastrar um novo usuário
     * @return true se feito com sucesso, false se não
     */
    public boolean cadastrar(){
        usuario = abrirFormCadastro();
        if(usuario!=null){//se não retornar nulo, insere no banco
            return new UsuarioDao().criar(usuario);//salva no banco de dados
        }
        return false;
    }
    
     
    /**
     *
     * @param usuario Usuário selecionado
     * @return Uma mensagem contendo as informações do usuário
     */
    public String mensagemExcluirUsuario(Usuario usuario){
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
    
    /**
     *Este método valida o nome de acordo com as regras de negócio
     * @param nome nome do usuário
     * @return true se válido, se não, false
     */
    public boolean validarNome(String nome){
        return nome.length()>2;
    }
    
    /**
     *Este método valida o nome de usuario de acordo com as regras de negócio
     * @param usuario nome de usuário
     * @return true se válido, se não, false
     */
    public boolean validarUsuario(String usuario){
        return usuario.length()>2;
    }
    
    /**
     *Este método valida a senha de acordo com as regras de negócio
     * @param senha senha do usuário
     * @return true se válido, se não, false
     */
    public boolean validarSenha(char [] senha){
        return senha.length > 3;
    }
    
    /**
     *Este método valida e confirma as duas senhas de acordo com as regras de negócio
     * @param senha senha do usuário
     * @param confirmaSenha confirmação da senha
     * @return true se válido, se não, false
     */
    public boolean verificarSenha(char [] senha, char [] confirmaSenha){
        return Arrays.equals(senha, confirmaSenha) && confirmaSenha.length>3;
    }
    
    /**
     *Este método é responsável por fazer a validação aritmética do cpf
     * @param cpf cpf a ser validado
     * @return true se válido, se não, false
     */
    public boolean validarCpf(String cpf){
       return rsc.validarCpf(cpf);
    }
}
