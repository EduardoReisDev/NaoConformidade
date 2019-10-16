package model;



/**
 *
 * @author Eduardo
 */
public class Usuario extends Pessoa{
    private String usuario;
    private String senha;
    private boolean master;
    
    public Usuario(){
        
    }
    
    public Usuario(int id, String nome, String cpf, String usuario, String senha, boolean master){
        super(id, cpf, nome);
        this.usuario = usuario;
        this.senha = senha;
        this.master = master;
    }
    
    public Usuario(String nome, String usuario, String senha, boolean master){
        this.usuario = usuario;
        this.senha = senha;
        this.master = master;
    }
    
    public Usuario(String usuario, String senha){
        this.usuario = usuario;
        this.senha = senha;
    }
    
    public boolean isMaster() {
        return master;
    }

    public void setMaster(boolean master) {
        this.master = master;
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
