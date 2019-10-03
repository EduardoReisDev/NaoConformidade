/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DataAccessObject.UsuarioDataObject;
import java.util.InputMismatchException;

/**
 *
 * @author leona
 */
public class UsuarioController {
    public boolean login(String usuario, String senha){
        return null != new UsuarioDataObject().login(usuario, senha);
    }
    
    public boolean existeUsuarios(){
        return new UsuarioDataObject().existeUsuarios();
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
