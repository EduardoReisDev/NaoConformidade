package model;

/**
 *
 * @author Eduardo
 */
public class Setor {
    private int id;
    private String Nome;
    private Responsavel responsavel;
    
    public Setor(){
        
    }
    
    public Setor(int id){
       this.id = id;
    }

    public Setor(int id, String nome, Responsavel responsavel){
        this.id = id;
        this.Nome = nome;
        this.responsavel = responsavel;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }
    
}
