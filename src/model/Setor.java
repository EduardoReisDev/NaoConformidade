package model;

/**
 *
 * @author Eduardo
 */
public class Setor {
    private int id;
    private String nome;
    private Responsavel responsavel;
    
    public Setor(){
        
    }
    
    public Setor(int id){
       this.id = id;
    }

    public Setor(int id, String nome, Responsavel responsavel){
        this.id = id;
        this.nome = nome;
        this.responsavel = responsavel;
    }
    
    @Override
    public String toString(){
        return String.format("%010d", id)+";"+
                nome+";"+
                responsavel.toString();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String Nome) {
        this.nome = Nome;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }
    
}
