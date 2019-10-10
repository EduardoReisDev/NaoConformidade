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
public interface Usuario {
    
    public boolean criar(model.Usuario dados);
    public model.Usuario listarPorId(int id);
    public boolean editar(model.Usuario dados);
    public boolean excluir(int id);
    public void listarTodos();
    public boolean existeUsuarios();
    public model.Usuario listarPorCpf(String cpf);
    public model.Usuario login (String usuario, String senha);
}
