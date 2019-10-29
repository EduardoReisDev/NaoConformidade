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
public abstract class Pessoa {
    private int id;
    private String cpf;
    private String nome;

    public Pessoa(){
        
    }
    
    public Pessoa(String nome){
        this.nome = nome;
    }
    
    public Pessoa(int id){
        this.id = id;
    }
    
    public Pessoa(int id, String cpf, String nome){
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
    }
    
    @Override
    public String toString(){
        return String.format("%010d", id)+";"+
                nome+";"+
                cpf;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
