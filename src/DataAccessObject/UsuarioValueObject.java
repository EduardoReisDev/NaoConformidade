package DataAccessObject;



/**
 *
 * @author Eduardo
 */
public class UsuarioValueObject {
    private int id;
    private String nome;
    private String cpf;
    private String usuario;
    private String senha;
    private boolean master;
    
    public UsuarioValueObject(){
        
    }
    
    public UsuarioValueObject(int id, String nome, String cpf, String usuario, String senha, boolean master){
        this.cpf=cpf;
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.master = master;
    }
    
    public UsuarioValueObject(String nome, String usuario, String senha, boolean master){
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.master = master;
    }
    
    public UsuarioValueObject(String usuario, String senha){
        this.usuario = usuario;
        this.senha = senha;
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean isMaster() {
        return master;
    }

    public void setMaster(boolean master) {
        this.master = master;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
