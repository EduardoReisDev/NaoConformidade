/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author leona
 */
public class Responsavel extends Pessoa{

    public Responsavel() {
        
    }
    
    public Responsavel(int id, String cpf, String nome){
        super(id, cpf, nome);
    }
    
    public Responsavel(int id){
        super(id);
    }
    
    @Override
    public String toString(){
        return super.toString();
    }
    
}
