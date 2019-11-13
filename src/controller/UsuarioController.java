/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import resources.Resources;
import dao.UsuarioDao;
import java.awt.Component;
import java.awt.Frame;
import java.util.Arrays;
import model.Usuario;
import java.util.function.Consumer;
import view.Mensagens;
import view.usuario.FormCadastrarUsuario;
import view.usuario.FormEditarUsuario;
import view.usuario.FormLogin;
import view.usuario.VerificarUsuario;

/**
 *
 * @author leona
 */
public class UsuarioController {
    /**
     *Tentativas máximas de tentar fazer login
     */
    public final int TENTATIVAS_MAXIMAS_LOGIN;
    private Component componentePai;
    private Usuario usuario;
    private final UsuarioDao usuarioDao;
    private FormLogin formularioLogin;
    private FormEditarUsuario formularioEditar;
    private FormCadastrarUsuario formCadastro;
    private VerificarUsuario verificarUsuario;
    
    public void setComponentePai(Component componentePai) {
        this.componentePai = componentePai;
    }
    
    public UsuarioController(){
        this.TENTATIVAS_MAXIMAS_LOGIN = 3;
        this.usuarioDao = new UsuarioDao();
    }
    
    /**
     *Este método é responsável por listar todos os usuários existentes no banco de dados
     * @param resultado resultado da listagem
     */
    public void listarUsuarios(Consumer<? super Usuario> resultado){
        usuarioDao.listarTodos(resultado::accept);
    }
    
    /**
     *Este método é responsável por consultar o nome do usuário no banco de dados
     * @param resultado resulatado da listagem
     * @param nome nome do usuário a ser consultado
     */
    public void listarUsuariosPorNome(Consumer<? super Usuario> resultado, String nome){
        usuarioDao.lerPorNome(resultado::accept, nome);
    }
    
    /**
     *Este método faz a listagem a partir do id de usuário
     * @param id id do usuário a ser consultado;
     * @return dados de usuario, ou null se não for encontrado nenhum usuário
     */
    public Usuario listarPorId(int id){
        return usuarioDao.listarPorId(id);
    }
    
    public int getLastId(){
        return usuarioDao.getLastId();
    }
    
    public boolean verificarSeExisteCpf(String cpf) {
        return usuarioDao.listarPorCpf(Resources.removerCaracteresInvalidosCpf(cpf)) != null;
    }
    
    /**
     *Este método é responsável por abrir o formulário de login
     * @return dados de login caso eles sejam inseridos
     */
    public Usuario abrirFormularioLogin() {
        formularioLogin = new FormLogin((Frame) componentePai, true,this);
        formularioLogin.setLocationRelativeTo(componentePai);
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
                        "Somente usuários masters poderão prosseguir!", 
                        "Somente usuários masters", 
                        Resources.ATENCAO);
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
                                    "Login não efetuado",
                                    Resources.ATENCAO)){
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
            abrirFormCadastro();
            return login();
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
            if(usuarioDao.excluir(id)){
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
                "Excluir Usuário",
                Resources.QUESTAO);
    }
    
    /**
     *Este método mostra uma mensagem de sucesso ao excluir
     */
    public void exibirSucessoExclusao(){
        Mensagens.mensagem(componentePai,
                mensagemSucessoExcluirUsuario(usuario),
                "Sucesso ao excluir",
                Resources.SUCESSO);
    }
    
    /**
     *Este método mostra uma mensagem de erro ao excluir
     */
    public void exibirErroExclusao(){
        Mensagens.mensagem(componentePai,
                mensagemErroExcluirUsuario(usuario), 
                "Erro ao excluir",
                Resources.ERRO);
    }
    
    /**
     *
     * @param id id do usuário a ser editado
     */
    public void abrirFormEditar(int id) {
        formularioEditar = new FormEditarUsuario((Frame) componentePai, true, this);
        formularioEditar.setLocationRelativeTo(null);
        formularioEditar.preencherCampos(listarPorId(id));
        formularioEditar.setVisible(true);
    }  
    
    /**
     *Este método é responsável por editar os dados de usuário no banco de dados 
     * @param usuario usuário a ser editado
     * @return true se foi editado com sucesso, false se não
     */
    public boolean editar(Usuario usuario){
        if(usuario!=null){
           return usuarioDao.editar(usuario);//salva no banco de dados
        } 
        return false;
    }
  
    /**
     * Este método é responsável por verificar se existem usuários masters no banco de dados.
     * @return true quando existe e false quando não existe usuários masters armazenados.
     */
    public boolean verificarExistenciaDeUsuariosMasters(){
        return usuarioDao.existeUsuariosMasters();
    }
    
    /**
     * Este método é responsável por abrir o formulário de cadastro de usuário
     */
    public void abrirFormCadastro(){
        formCadastro = new FormCadastrarUsuario((Frame) componentePai, true, this);
        formCadastro.setLocationRelativeTo(null);
        formCadastro.setVisible(true);
    }
    
    /**
     *Este método é responsável por cadastrar um novo usuário
     * @param usuario usuário a ser cadastrado
     * @return true se feito com sucesso, false se não
     */
    public boolean cadastrar(Usuario usuario){
        if(usuario!=null){//se não retornar nulo, insere no banco
            return usuarioDao.criar(usuario);//salva no banco de dados
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
       return Resources.validarCpf(cpf);
    }

    public void abrirFormVerificarUsuario() {
       verificarUsuario = new VerificarUsuario((Frame) componentePai, true, this);
       verificarUsuario.setLocationRelativeTo(componentePai);
       verificarUsuario.setVisible(true);
    }
    
    public void verificarUsuario(Usuario usuario){
        usuario = usuarioDao.loginPorNomeECpf(usuario);
        if(usuario!=null){
            verificarUsuario.dispose();
            abrirFormEditar(usuario.getId());
        }
        else{
            System.out.println("gi");
        }
    }
    
}
