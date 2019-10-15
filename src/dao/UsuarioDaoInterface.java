/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
/**
 *
 * @author leona
 */
public interface UsuarioDaoInterface {
    public boolean existeUsuariosMasters();
    public model.Usuario listarPorCpf(String cpf);
    public model.Usuario login (String usuario, String senha);
}
