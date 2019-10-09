/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DataAccessObject.UsuarioBusinessObject;
import DataAccessObject.UsuarioDataObject;
import DataAccessObject.UsuarioValueObject;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.function.Consumer;
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
    
    public static void ListarUsuarios(Consumer<? super UsuarioValueObject> resultado){
        new UsuarioDataObject().listarTodos(resultado::accept);
    }
    
    public static UsuarioValueObject listarPorId(int id){
        return new UsuarioDataObject().listarPorId(id);
    }
    
    public static void excluir(int id){
        UsuarioValueObject usuario = listarPorId(id);
        if(Mensagens.confirmar(
                null,
                UsuarioBusinessObject.mensagemExcluirUsuário(usuario),
                UsuarioBusinessObject.TITULO_EXCLUIR,
                UsuarioBusinessObject.QUESTAO)){
            if(new UsuarioDataObject().excluir(id)){
                Mensagens.mensagem(
                        null,
                        UsuarioBusinessObject.mensagemSucessoExcluirUsuario(usuario),
                        UsuarioBusinessObject.TITULO_SUCESSO_EXCLUSAO,
                        UsuarioBusinessObject.SUCESSO);
            }
            else{
                Mensagens.mensagem(
                        null,
                        UsuarioBusinessObject.mensagemErroExcluirUsuario(usuario), 
                        UsuarioBusinessObject.TITULO_ERRO_EXCLUSAO,
                        UsuarioBusinessObject.ERRO);
            }
        }
    }
    
    public static boolean editar(int id){
        UsuarioValueObject usuarioEditar = null;
        UsuarioValueObject usuarioSelecionado = listarPorId(id);
        while(usuarioEditar == null){
            usuarioEditar = Editar.inicio(usuarioSelecionado);//pega os dados do formulário
            if(usuarioEditar==null){//se o formulário retorna nulo mostra uma mensagem
                if(!Mensagens.confirmar(
                        null, 
                        UsuarioBusinessObject.MENSAGEM_FORMULARIO_NAO_PREENCHIDO,
                        UsuarioBusinessObject.TITULO_MENSAGEM_FORMULARIO_NAO_PREENCHIDO,
                        UsuarioBusinessObject.ERRO)){
                    break;
                }
            } 
            else{//se não, coloca o cpf e insere no banco
                usuarioEditar.setCpf(usuarioSelecionado.getCpf());//pega o cpf do usuário selecionado
                usuarioEditar.setId(usuarioSelecionado.getId());//peda o id do usuário selecionado
                return new UsuarioDataObject().editar(usuarioEditar);//salva no banco de dados
            }
        }
        return false;
    }
    
    /**
     *Este método é responsável por fazer o login e verificar se o usuário logado é master
     * @return true se o usuário logado for master, se não, retorna false
     */
    public static boolean usuarioMaster(){
        return login().isMaster();
    }
    
    /**
     * Este método é responsável por verificar se os dados inseridos no formulário confirmam com os 
     * dados armazenados no banco de dados, possibilitando o login.
     * @return Dados de usuário caso os dados informados confirmam com os armazenados no banco de 
     * dados ou nulo, caso contrário.
     */
    public static UsuarioValueObject login(){
        int tentativas = 0;
        UsuarioValueObject usuario = null;
        UsuarioValueObject usuarioFormulario;
        if(existeUsuarios()){//chama o diálogo de login
            while(usuario == null && tentativas < UsuarioBusinessObject.TENTATIVAS_MAXIMAS_LOGIN){
                tentativas++;
                usuarioFormulario = Login.inicio();
                if(usuarioFormulario!=null){
                    usuario = new UsuarioDataObject().login(
                                                    usuarioFormulario.getUsuario(), 
                                                    usuarioFormulario.getSenha()
                                                    );
                    if(usuario == null){
                        if(UsuarioBusinessObject.TENTATIVAS_MAXIMAS_LOGIN-tentativas > 0){
                            if(!Mensagens.confirmar(
                                    null,
                                    UsuarioBusinessObject.mensagemLoginNaoEftuado(tentativas),
                                    UsuarioBusinessObject.TITULO_MENSAGEM_LOGIN_NAO_EFETUADO,
                                    UsuarioBusinessObject.ATENCAO)){
                                break;
                            }
                        }
                    }
                }
                else{
                    if(UsuarioBusinessObject.TENTATIVAS_MAXIMAS_LOGIN-tentativas > 0){
                        if(!Mensagens.confirmar(
                                null,
                                UsuarioBusinessObject.mensagemCredenciaisNaoFornecidas(tentativas), 
                                UsuarioBusinessObject.TITULO_MENSAGEM_CREDENCIAIS_NAO_FORNECIDAS,
                                UsuarioBusinessObject.ATENCAO)){
                            break;
                        }
                    }
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
    
    /**
     * Este método é responsável por verificar se existem usuários no banco de dados.
     * @return true quando existe e false quando não existe usuários armazenados.
     */
    public static boolean existeUsuarios(){
        return new UsuarioDataObject().existeUsuarios();
    }
    
    
    public static boolean cadastrarUsuario(){
        String cpf = null;
        while(cpf == null){//se o formulário do cpf retornou o cpf
            cpf = InserirCpf.inicio();
            if(cpf == null){//se o formulário não retornou nada
                if(!Mensagens.confirmar(
                        null, 
                        UsuarioBusinessObject.MENSAGEM_CPF_NAO_FORNECIDO, 
                        UsuarioBusinessObject.TITULO_MENSAGEM_CPF_NAO_FORNECIDO,
                        UsuarioBusinessObject.ATENCAO)){
                    break;
                }
            }
            else{//se retornou um cpf
                UsuarioValueObject usuario = new UsuarioDataObject().listarPorCpf(cpf);
                if(usuario != null){//verifica no banco se existe um usuário com o mesmo cpf
                    System.out.println(usuario.getId());
                    editar(usuario.getId());
                }
                else{usuario = null;
                    while(usuario == null){
                        usuario = Criar.inicio();//pega o restante dos dados do formulário
                        if(usuario==null){  //se o formulário retorna nulo mostra uma mensagem
                            if(!Mensagens.confirmar(
                                    null, 
                                    UsuarioBusinessObject.MENSAGEM_FORMULARIO_NAO_PREENCHIDO,
                                    UsuarioBusinessObject.TITULO_MENSAGEM_FORMULARIO_NAO_PREENCHIDO,
                                    UsuarioBusinessObject.ERRO)){
                                break;
                            }
                        } 
                        else{//se não, coloca o cpf e insere no banco
                            usuario.setCpf(cpf);
                            return new UsuarioDataObject().salvar(usuario);//salva no banco de dados
                        }
                    }
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
    
    public static boolean validaCpf(String cpf){
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
