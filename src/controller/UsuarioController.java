/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DataAccessObject.UsuarioDataObject;
import DataAccessObject.UsuarioValueObject;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.function.Consumer;
import javax.swing.JOptionPane;
import view.CadastrarUsuario;
import view.Login;
import view.FormCpf;

/**
 *
 * @author leona
 */
public class UsuarioController {
    public static void ListarUsuarios(Consumer<? super UsuarioValueObject> resultado){
        new UsuarioDataObject().listarTodos(resultado::accept);
    }
    
    public static UsuarioValueObject ListarPorId(int id){
        return new UsuarioDataObject().listarPorId(id);
    }
    
    public static boolean excluir(int id){
        return new UsuarioDataObject().excluir(id);
    }
    
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
        int tentativasMaximas = 3;
        UsuarioValueObject usuario = null;
        UsuarioValueObject usuarioFormulario;
        if(existeUsuarios()){//chama o diálogo de login
            while(usuario == null && tentativas < tentativasMaximas){
                tentativas++;
                usuarioFormulario = Login.inicio();
                if(usuarioFormulario!=null){
                    usuario = new UsuarioDataObject().login(
                                                    usuarioFormulario.getUsuario(), 
                                                    usuarioFormulario.getSenha()
                                                    );
                    if(usuario == null){
                        if(JOptionPane.showConfirmDialog(null, "Os dados informados não correspondem a nenhum usuário salvo."
                                                        + "\nDeseja tentar novamente?\n"+
                                                        (tentativasMaximas-tentativas) + " tentativas restentes.",
                                                        "Credenciais não inseridas", 
                                                        JOptionPane.YES_OPTION, 
                                                        JOptionPane.WARNING_MESSAGE) == 1){
                            break;
                        }
                    }
                }
                else{
                    if(tentativasMaximas-tentativas == 0){
                        if(JOptionPane.showConfirmDialog(null, "Nenhum dado recebido.\nDeseja tentar novamente?\n"+
                                                        (tentativasMaximas-tentativas) + " tentativas restentes.",
                                                        "Credenciais não inseridas", 
                                                        JOptionPane.YES_OPTION, 
                                                        JOptionPane.WARNING_MESSAGE) == 1){
                            break;
                        }
                    }
                }
            }
        }
        else{//chama o diálogo de cadastro de usuários
            cadastrarUsuario();
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
        String cpf = "";
        while(cpf.isEmpty()){//se o formulário do cpf retornou o cpf
            cpf = FormCpf.inicio();
            if(cpf.isEmpty()){//se o formulário não retornou nada
                if(JOptionPane.showConfirmDialog(null, "CPF não inserido.\nDeseja tentar novamente?", "CPF não inserido", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE) == 1){
                    break;
                }
            }
            else{//se retornou um cpf
                if(new UsuarioDataObject().existeCpf(cpf)){//verifica no banco se existe um usuário com o mesmo cpf
                    System.out.println("editar");
                }
                else{
                    UsuarioValueObject usuario = null;
                    while(usuario == null){
                        usuario = CadastrarUsuario.inicio();//pega o restante dos dados do formulário
                        if(usuario==null){  //se o formulário retorna nulo mostra uma mensagem
                            if(JOptionPane.showConfirmDialog(null, "Dados de usuário não inseridos.\nDeseja tentar novamente?", "Dados de usuário não inseridos", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE) == 1){
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
        return Arrays.equals(senha, confirmaSenha);
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
