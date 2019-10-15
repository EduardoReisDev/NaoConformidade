/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.function.Consumer;

/**
 *
 * @author leona
 * @param <T> Tipo da classe value object
 */
public interface Crud<T> {
    public boolean criar(T dados);
    public void lerTodos(Consumer <?super T> resultado);
    public T lerPorId(int id);
    public boolean editar(T dados);
    public boolean excluir(int id);
}
