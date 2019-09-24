/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessObject;

import java.util.function.Consumer;

/**
 *
 * @author leona
 * @param <T> Tipo da classe value object
 */
public interface DataAccessObject<T> {
    public boolean salvar(T dados);
    public void listarTodos(Consumer <?super T> resultado);
    public void listarPorIntervalo();
    public T listarPorId(T value, int id);
    public void editar(T value);
    public void excluir(int id);
}
