/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UsuarioDao;
import java.awt.Component;
import java.awt.Frame;
import model.Usuario;
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
    Component componentePai;
    
    private Usuario usuario;
    
    public void setComponentePai(Component componentePai) {
        this.componentePai = componentePai;
    }
    
    public void exibir(){
        System.out.println(componentePai);
    }
    
    public UsuarioController(){
        usuarioNegocio = new UsuarioBusinnesObject();
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
                        usuarioNegocio.MENSAGEM_SOMENTE_USUARIO_MASTER, 
                        usuarioNegocio.TITULO_MENSAGEM_SOMENTE_USUARIO_MASTER, 
                        usuarioNegocio.ATENCAO);
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
                            if(!Mensagens.confirmar(componentePai,
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
        return new UsuarioDao().excluir(id);
    }
    
    /**
     *
     * @param id id do usuário a ser excluído
     * @return true se foi confirmada a exclusão, false se não
     */
    public boolean confirmarExclusao(int id){
        usuario = listarPorId(id);
        return Mensagens.confirmar(componentePai,
                usuarioNegocio.mensagemExcluirUsuário(usuario),
                usuarioNegocio.TITULO_EXCLUIR,
                usuarioNegocio.QUESTAO);
    }
    
    /**
     *Este método mostra uma mensagem de sucesso ao excluir
     */
    public void exibirSucessoExclusao(){
        Mensagens.mensagem(componentePai,
                usuarioNegocio.mensagemSucessoExcluirUsuario(usuario),
                usuarioNegocio.TITULO_SUCESSO_EXCLUSAO,
                usuarioNegocio.SUCESSO);
    }
    
    /**
     *Este método mostra uma mensagem de erro ao excluir
     */
    public void exibirErroExclusao(){
        Mensagens.mensagem(componentePai,
                usuarioNegocio.mensagemErroExcluirUsuario(usuario), 
                usuarioNegocio.TITULO_ERRO_EXCLUSAO,
                usuarioNegocio.ERRO);
    }
    
    /**
     *
     * @param usuario dados de usuário que serão utilizados para popular o formulário
     * @return se as informações inseridas no formulario de forma correta, retorna elas, se não, null 
     */
    public Usuario abrirFormEditar(Usuario usuario) {
        System.out.println(componentePai);
        FormEditar formularioEditar = new FormEditar((Frame) componentePai, true);
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
    
    /**
     *Este método é responsável por editar os dados de usuário no banco de dados 
     * @param id id do usuário a ser editado
     * @return true se foi editado com sucesso, false se não
     */
    public boolean editar(int id){
        Usuario usuarioEditar;
        Usuario usuarioSelecionado = listarPorId(id);
        usuarioEditar = abrirFormEditar(usuarioSelecionado);//pega os dados do formulário
        if(usuarioEditar!=null){//se não, coloca o cpf e o id e insere no banco
            usuarioEditar.setCpf(usuarioSelecionado.getCpf());//pega o cpf do usuário selecionado
            usuarioEditar.setId(usuarioSelecionado.getId());//peda o id do usuário selecionado
            return new UsuarioDao().editar(usuarioEditar);//salva no banco de dados
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
     * Este método é responsável por abrir o formulário de cpf e retornar o cpf inserido
     * @return Uma String contendo o cpf fornecido ou null quando o cpf fornecido não é valido ou não fornecido
     */
    private String abrirFormCpf(){
        FormCpf formularioCpf = new FormCpf((Frame) componentePai, true);
        formularioCpf.setLocationRelativeTo(null);
        formularioCpf.setVisible(true);
        String cpf = usuarioNegocio.removerCaracteresInvalidosCpf(formularioCpf.getTxtCpf().getText());
        if(usuarioNegocio.validarCpf(cpf)){
            return cpf;
        }
        return null;
    }
    
    /**
     * Este método é responsável por abrir o formulário de cadastro de usuário e retornar os dados inseridos
     * @return dados de usuário caso forem válidos
     */
    private Usuario abrirFormCadastro(){
        FormCriar formularioUsuario = new FormCriar((Frame) componentePai, true);
        formularioUsuario.setLocationRelativeTo(null);
        usuario = null;
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
    
    /**
     *Este método é responsável por cadastrar um novo usuário
     * @return true se feito com sucesso, false se não
     */
    public boolean cadastrar(){
        String cpf = abrirFormCpf();
        if(cpf != null){//se retornou um cpf
            usuario = new UsuarioDao().listarPorCpf(cpf);
            if(usuario != null){//verifica no banco se existe um usuário com o mesmo cpf
                editar(usuario.getId());
            }
            else{
                usuario = abrirFormCadastro();
                if(usuario!=null){//se não retornar nulo, coloca o cpf e insere no banco
                    usuario.setCpf(cpf);
                    return new UsuarioDao().criar(usuario);//salva no banco de dados
                }
            }
        }
        return false;
    }
    
    /**
     *Este método valida o nome de acordo com as regras de negócio
     * @param nome nome do usuário
     * @return true se válido, se não, false
     */
    public boolean validarNome(String nome){
        return usuarioNegocio.validarNome(nome);
    }
    
    /**
     *Este método valida o nome de usuario de acordo com as regras de negócio
     * @param usuario nome de usuário
     * @return true se válido, se não, false
     */
    public boolean validarUsuario(String usuario){
        return usuarioNegocio.validarUsuario(usuario);
    }
    
    /**
     *Este método valida a senha de acordo com as regras de negócio
     * @param senha senha do usuário
     * @return true se válido, se não, false
     */
    public boolean validarSenha(char [] senha){
        return usuarioNegocio.validarSenha(senha);
    }
    
    /**
     *Este método valida e confirma as duas senhas de acordo com as regras de negócio
     * @param senha senha do usuário
     * @param confirmaSenha confirmação da senha
     * @return true se válido, se não, false
     */
    public boolean verificarSenha(char [] senha, char [] confirmaSenha){
        return usuarioNegocio.verificarSenha(senha, confirmaSenha);
    }
    
    /**
     *Este método é responsável por fazer a validação aritmética do cpf
     * @param cpf cpf a ser validado
     * @return true se válido, se não, false
     */
    public boolean validarCpf(String cpf){
       return usuarioNegocio.validarCpf(cpf);
    }
}
