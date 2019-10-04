/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DataAccessObject.UsuarioDataObject;
import DataAccessObject.UsuarioValueObject;
import java.util.InputMismatchException;
import java.util.function.Consumer;
import view.CadastroUsuario;
import view.Login;
import view.Principal;
import view.VerificaCpf;

/**
 *
 * @author leona
 */
public class UsuarioController {
    private CadastroUsuario telaCadastroUsuario = new CadastroUsuario(null, true);
    private VerificaCpf telaVerificaCpf = new VerificaCpf(null, true);
    private Login telaLogin = new Login(null, true);
    private UsuarioValueObject usuario;
    
    public static void ListarUsuarios(Consumer<? super UsuarioValueObject> resultado){
        new UsuarioDataObject().listarTodos(resultado::accept);
    }
    
    public boolean login(){
        telaLogin.setLocationRelativeTo(null);
        boolean loginEfetuado = false;
        int tentativas = 0;
        int tentativasMaximas = 3;
        if(existeUsuarios()){//chama o diálogo de login
            while(!loginEfetuado && tentativas < tentativasMaximas){
                telaLogin.setVisible(true);
                usuario = new UsuarioDataObject().login(
                                                telaLogin.getTxtUsuario().getText(), 
                                                String.valueOf(telaLogin.getTxtSenha().getPassword()));
                loginEfetuado = usuario!=null;
                tentativas++;
            }
        }
        else{//chama o diálogo de cadastro de usuários
            cadastrarUsuario();
        }
        return loginEfetuado;
    }
    
    public boolean existeUsuarios(){
        return new UsuarioDataObject().existeUsuarios();
    }
    
    public void cadastrarUsuario(){
        telaVerificaCpf.setLocationRelativeTo(null);
        telaVerificaCpf.setVisible(true);
        if(new UsuarioDataObject().existeCpf(telaVerificaCpf.getTxtCpf().getText())){
            System.out.println("editar");
        }
        else{
            telaCadastroUsuario.setLocationRelativeTo(null);
            telaCadastroUsuario.setVisible(true);
            usuario = new UsuarioValueObject();
            usuario.setCpf(telaVerificaCpf.getTxtCpf().getText());
            usuario.setNome(telaCadastroUsuario.getTxtNome().getText());
            usuario.setUsuario(telaCadastroUsuario.getTxtUsuario().getText());
            usuario.setSenha(String.copyValueOf(telaCadastroUsuario.getTxtSenha().getPassword()));
            usuario.setMaster(telaCadastroUsuario.getCheckMaster().isSelected());
            new UsuarioDataObject().salvar(usuario);
        }
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
