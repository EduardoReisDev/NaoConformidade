/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ResponsavelDao;
import java.util.function.Consumer;
import model.Responsavel;

/**
 *
 * @author leona
 */
public class ResposavelController {
    public void listarTodos(Consumer <?super Responsavel> responsavel){
        new ResponsavelDao().listarTodos(responsavel::accept);
    }
    public void listarPorNome(Consumer <?super Responsavel> responsavel, String nome){
        new ResponsavelDao().listarPorNome(responsavel::accept, nome);
    }
    public Responsavel listarPorId(int id){
        return new ResponsavelDao().listarPorId(id);
    }
    
}