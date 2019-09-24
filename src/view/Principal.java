/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DataAccessObject.UsuarioDataObject;
import DataAccessObject.UsuarioValueObject;

/**
 *
 * @author leona
 */
public class Principal {
    public void insereUsuario(){
        UsuarioValueObject usuario = new UsuarioValueObject();
        usuario.setNome("Leonardo");
        usuario.setEmail("leonardojr410@gmail.com");
        usuario.setSenha("123");
        
        System.out.println(new UsuarioDataObject().salvar(usuario));
    }
    public void listaUsuarios(){
        new UsuarioDataObject().listarTodos(usuario->{
            System.out.println(usuario.getId());
            System.out.println(usuario.getNome());
            System.out.println(usuario.getEmail());
            System.out.println(usuario.getSenha());
        });
    }
    public static void main(String[] args) {
        new Principal().listaUsuarios();
    }
}
