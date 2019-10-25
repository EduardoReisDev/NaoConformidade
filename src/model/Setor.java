package model;

/**
 *
 * @author Eduardo
 */
public class Setor {
    private int id;
    private String Codigo;
    private String Nome;
    private String Responsavel;
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

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getResponsavel() {
        return Responsavel;
    }

    public void setResponsavel(String Responsavel) {
        this.Responsavel = Responsavel;
    }

    
}
