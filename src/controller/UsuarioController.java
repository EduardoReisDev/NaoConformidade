/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UsuarioDao;
import java.awt.Frame;
import model.Usuario;
import java.util.Arrays;
import java.util.function.Consumer;
import view.Mensagens;
import view.usuario.FormCriar;
import view.usuario.FormEditar;
import view.usuario.FormLogin;
import view.usuario.FormCpf;

/**
 *
 * @author leona
 */
public class UsuarioController {
    UsuarioBusinnesObject usuarioNegocio;
    Frame formPai;

    public Frame getFormPai() {
        return formPai;
    }

    public void setFormPai(Frame formPai) {
        this.formPai = formPai;
    }
    
    public UsuarioController(){
        usuarioNegocio = new UsuarioBusinnesObject();
    }
    
    public void ListarUsuarios(Consumer<? super Usuario> resultado){
        new UsuarioDao().listarTodos(resultado::accept);
    }
    
    public Usuario listarPorId(int id){
        return new UsuarioDao().listarPorId(id);
    }
    
    public Usuario abrirFormularioLogin() {
        FormLogin formularioLogin = new FormLogin(formPai, true);
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
    public boolean loginMaster(){
        Usuario usuario = login();
        if(usuario!= null){
            if(usuario.isMaster()){
                return true;
            }
            else{
                Mensagens.mensagem(
                        formPai, 
                        usuarioNegocio.MENSAGEM_SOMENTE_USUARIO_MASTER, 
                        usuarioNegocio.TITULO_MENSAGEM_SOMENTE_USUARIO_MASTER, 
                        usuarioNegocio.ATENCAO);
            }
        }
        return false;
    }
    
    /**
     * Este método é responsável por verificar se os dados inseridos no formulário confirmam com os 
     * dados armazenados no banco de dados, possibilitando o login.
     * @return Dados de usuário caso os dados informados confirmam com os armazenados no banco de 
     * dados ou nulo, caso contrário.
     */
    public Usuario login(){
        int tentativas = 0;
        Usuario usuario = null;
        Usuario usuarioFormulario;
        if(existeUsuariosMasters()){//chama o diálogo de login
            while(usuario == null && tentativas < usuarioNegocio.TENTATIVAS_MAXIMAS_LOGIN){
                tentativas++;
                usuarioFormulario = abrirFormularioLogin();
                if(usuarioFormulario!=null){
                    usuario = new UsuarioDao().login(
                            usuarioFormulario.getUsuario(), 
                            usuarioFormulario.getSenha()
                            );
                    if(usuario == null){
                        if(usuarioNegocio.TENTATIVAS_MAXIMAS_LOGIN-tentativas > 0){
                            if(!Mensagens.confirmar(
                                    formPai,
                                    usuarioNegocio.mensagemLoginNaoEftuado(tentativas),
                                    usuarioNegocio.TITULO_MENSAGEM_LOGIN_NAO_EFETUADO,
                                    usuarioNegocio.ATENCAO)){
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
    
    public void excluir(int id){
        Usuario usuario = listarPorId(id);
        if(Mensagens.confirmar(
                formPai,
                usuarioNegocio.mensagemExcluirUsuário(usuario),
                usuarioNegocio.TITULO_EXCLUIR,
                usuarioNegocio.QUESTAO)){
            if(new UsuarioDao().excluir(id)){
                Mensagens.mensagem(
                        formPai,
                        usuarioNegocio.mensagemSucessoExcluirUsuario(usuario),
                        usuarioNegocio.TITULO_SUCESSO_EXCLUSAO,
                        usuarioNegocio.SUCESSO);
            }
            else{
                Mensagens.mensagem(
                        formPai,
                        usuarioNegocio.mensagemErroExcluirUsuario(usuario), 
                        usuarioNegocio.TITULO_ERRO_EXCLUSAO,
                        usuarioNegocio.ERRO);
            }
        }
    }
    
    public Usuario abrirFormularioEditar(Usuario usuario) {
        FormEditar formularioEditar = new FormEditar(formPai, true);
        formularioEditar.setLocationRelativeTo(null);
        formularioEditar.preencherCampos(usuario);
        formularioEditar.setVisible(true);
        if(formularioEditar.validar()){
            return new Usuario(
                    formularioEditar.getTxtNome().getText(),//nome
                    formularioEditar.getTxtUsuario().getText(),//usuário
                    String.copyValueOf(formularioEditar.getTxtSenha().getPassword()),//senha
                    formularioEditar.getCheckMaster().isSelected()//usuário master
            );   
        }
        return null;
    }  
    
    public boolean editar(int id){
        Usuario usuarioEditar = null;
        Usuario usuarioSelecionado = listarPorId(id);
            usuarioEditar = abrirFormularioEditar(usuarioSelecionado);//pega os dados do formulário
            if(usuarioEditar!=null){//se não, coloca o cpf e o id e insere no banco
                usuarioEditar.setCpf(usuarioSelecionado.getCpf());//pega o cpf do usuário selecionado
                usuarioEditar.setId(usuarioSelecionado.getId());//peda o id do usuário selecionado
                return new UsuarioDao().editar(usuarioEditar);//salva no banco de dados
            } 
        return false;
    }
  
    /**
     * Este método é responsável por verificar se existem usuários no banco de dados.
     * @return true quando existe e false quando não existe usuários armazenados.
     */
    public boolean existeUsuariosMasters(){
        return new UsuarioDao().existeUsuariosMasters();
    }
    
    /**
     * Este método é responsável por abrir o formulário de cpf e retornar o cpf inserido
     * @return Uma String contendo o cpf fornecido ou null quando o cpf fornecido não é valido ou não fornecido
     */
    private String formularioCpf(){
        FormCpf formularioCpf = new FormCpf(formPai, true);
        String cpf = null;
        formularioCpf.setLocationRelativeTo(null);
        formularioCpf.setVisible(true);
        cpf = usuarioNegocio.removerCaracteresInvalidosCpf(formularioCpf.getTxtCpf().getText());
        if(usuarioNegocio.validarCpf(cpf)){
            return cpf;
        }
        return null;
    }
    
    /**
     * Este método é responsável por abrir o formulário de cadastro de usuário e retornar os dados inseridos
     * @return dados de usuário caso forem válidos
     */
    private Usuario formularioUsuario(){
        FormCriar formularioUsuario = new FormCriar(formPai, true);
        formularioUsuario.setLocationRelativeTo(null);
        Usuario usuario = null;
        formularioUsuario.setVisible(true);
        if(formularioUsuario.validar()){
            usuario = new Usuario(
                    formularioUsuario.getTxtNome().getText(),//nome
                    formularioUsuario.getTxtUsuario().getText(),//usuário
                    String.copyValueOf(formularioUsuario.getTxtSenha().getPassword()),//senha
                    formularioUsuario.getCheckMaster().isSelected()//usuário master
            );   
        }
        return usuario;
    }
    
    public boolean cadastrarUsuario(){
        String cpf = formularioCpf();
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
    
    public boolean validarCpf(String cpf){
       return usuarioNegocio.validarCpf(cpf);
    }
}
